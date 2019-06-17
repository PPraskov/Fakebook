package com.example.demo.web.validation.user;


import com.example.demo.domain.models.binding.UserRegisterBindingModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.web.validation.ValidationConstraints;
import com.example.demo.web.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterBindingModel bindingModel = (UserRegisterBindingModel) o;

        if (this.userRepository.findByUsername(bindingModel.getUsername()).isPresent()){

            errors.rejectValue("username",
                    String.format(ValidationConstraints.USERNAME_EXISTS,  bindingModel.getUsername()),
                    String.format(ValidationConstraints.USERNAME_EXISTS,  bindingModel.getUsername()));
        }

        if (bindingModel.getUsername().length() < 4){

            errors.rejectValue("username",
                    String.format(ValidationConstraints.USERNAME_TOO_SHORT,  bindingModel.getUsername()),
                    String.format(ValidationConstraints.USERNAME_TOO_SHORT,  bindingModel.getUsername())
            );
        }

        if (bindingModel.getPassword().length() < 4){

            errors.rejectValue("password",
                    ValidationConstraints.PASSWORDS_TOO_SHORT,
                    ValidationConstraints.PASSWORDS_TOO_SHORT
            );
        }

        if ( !bindingModel.getPassword().equals(bindingModel.getConfirmPassword())){

            errors.rejectValue("confirmPassword",
                    ValidationConstraints.PASSWORDS_DONT_MATCH,
                    ValidationConstraints.PASSWORDS_DONT_MATCH
            );
        }


        if (this.userRepository.findByEmail(bindingModel.getEmail()).isPresent()){

            errors.rejectValue("email",
                    String.format(ValidationConstraints.EMAIL_EXISTS,  bindingModel.getEmail()),
                    String.format(ValidationConstraints.EMAIL_EXISTS,  bindingModel.getEmail())
                    );
        }

        if (!bindingModel.getEmail().contains("@")){

            errors.rejectValue("email",
                    ValidationConstraints.ILLEGAL_EMAIL,
                    ValidationConstraints.ILLEGAL_EMAIL
                    );
        }

    }
}
