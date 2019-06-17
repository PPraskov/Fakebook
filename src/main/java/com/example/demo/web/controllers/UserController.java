package com.example.demo.web.controllers;

import com.example.demo.domain.models.binding.UserRegisterBindingModel;
import com.example.demo.domain.models.service.UserRegisterServiceModel;
import com.example.demo.domain.models.view.PostViewModel;
import com.example.demo.domain.models.view.UserProfileViewModel;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.services.UserService;
import com.example.demo.web.validation.user.UserRegisterValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator registerValidator;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator registerValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.registerValidator = registerValidator;
    }


    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model) {

        return super.view("/register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {
        this.registerValidator.validate(model,bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            return super.view("/register", modelAndView);
        }
        this.userService.registerUser(this.modelMapper.map(model, UserRegisterServiceModel.class));
        return super.view("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {

        return super.view("/login");
    }


    @GetMapping("/profile/{username}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(@PathVariable String username, ModelAndView modelAndView) {
        UserProfileViewModel userModel = this.modelMapper.map(this.userService.findByUsername(username),UserProfileViewModel.class);

        modelAndView.addObject("userInfo", userModel);

        return super.view("/profile", modelAndView);
    }

    @GetMapping("/myprofile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
        UserProfileViewModel userModel = this.modelMapper.map(this.userService.findByUsername(principal.getName()),UserProfileViewModel.class);

        modelAndView.addObject("userInfo", userModel);

        return super.view("/profile", modelAndView);
    }




}
