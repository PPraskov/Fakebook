package com.example.demo.services;

import com.example.demo.domain.models.service.PostServiceModel;
import com.example.demo.domain.models.view.PostViewModel;

import java.util.List;

public interface PostService {

    public List<PostServiceModel> getAllPosts();

    void addPost(PostServiceModel model);
}
