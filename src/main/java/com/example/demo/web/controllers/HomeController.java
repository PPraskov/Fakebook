package com.example.demo.web.controllers;


import com.example.demo.domain.models.binding.PostBindingModel;
import com.example.demo.domain.models.view.PostViewModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController extends BaseController {


    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index(){

        return super.view("/index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(Principal principal,@ModelAttribute(name = "model") PostBindingModel model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model",model);

        return super.view("/home",modelAndView);
    }


}
