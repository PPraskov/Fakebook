package com.example.demo.web.controllers;


import com.example.demo.domain.models.binding.PostBindingModel;
import com.example.demo.domain.models.service.PostServiceModel;
import com.example.demo.domain.models.view.PostViewModel;
import com.example.demo.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addConfirm(ModelAndView modelAndView, @ModelAttribute(value = "model") PostBindingModel model, Principal principal) {
        PostServiceModel postServiceModel = this.modelMapper.map(model, PostServiceModel.class);
        postServiceModel.setPublisher(principal.getName());
        this.postService.addPost(postServiceModel);

        return super.view("/home");
    }
}
