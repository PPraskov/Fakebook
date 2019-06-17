package com.example.demo.web.controllers;


import com.example.demo.domain.models.binding.PostBindingModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {


    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index() {

        return super.view("/index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(@ModelAttribute(name = "model") PostBindingModel model) {

        return super.view("/home");
    }


}
