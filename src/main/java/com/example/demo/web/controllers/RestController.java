package com.example.demo.web.controllers;


import com.example.demo.domain.models.service.PostServiceModel;
import com.example.demo.domain.models.view.CommentViewModel;
import com.example.demo.domain.models.view.PostViewModel;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/fetch")
public class RestController {

    private final PostService postService;

    @Autowired
    public RestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/allposts")
    public List<PostViewModel> allPosts() {
        List<PostServiceModel> serviceModels = this.postService.getAllPosts();
        List<PostViewModel> models = new ArrayList<>();
        return models;
    }

//    @GetMapping("/comments/{postId}")
//    public List<CommentViewModel> getComments(@PathVariable String postId){
//
//
//    }
}
