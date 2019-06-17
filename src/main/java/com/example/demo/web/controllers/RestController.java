package com.example.demo.web.controllers;


import com.example.demo.domain.models.service.PostServiceModel;
import com.example.demo.domain.models.view.PostViewModel;
import com.example.demo.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/fetch")
public class RestController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/allposts")
    @PreAuthorize("isAuthenticated()")
    public List<PostViewModel> allPosts() {
        List<PostServiceModel> serviceModels = this.postService.getAllPosts();
        List<PostViewModel> models = serviceModels.stream()
                .map(e -> {
                    PostViewModel model = this.modelMapper.map(e, PostViewModel.class);
                    return model;
                }).collect(Collectors.toList());
        return models;
    }


}
