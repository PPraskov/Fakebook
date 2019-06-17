package com.example.demo.services;

import com.example.demo.domain.entities.Post;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.models.service.CommentServiceModel;
import com.example.demo.domain.models.service.PostServiceModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserService userService, CommentRepository commentRepository, UserRepository userRepository, RoleService roleService, CommentService commentService) {
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public List<PostServiceModel> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        if (!posts.isEmpty()) {
            List<PostServiceModel> serviceModels = posts
                    .stream()
                    .map(e -> {
                        PostServiceModel model = this.modelMapper.map(e, PostServiceModel.class);
                        model.setPublisher(e.getPublisher().getUsername());
                        List<CommentServiceModel> commentServiceModels = e.getComments()
                                .stream()
                                .map(comment -> {
                                    CommentServiceModel commentServiceModel = this.modelMapper.map(comment, CommentServiceModel.class);
                                    commentServiceModel.setPublisher(comment.getPublisher().getUsername());
                                    return commentServiceModel;
                                })
                                .collect(Collectors.toList());

                        return model;
                    })
                    .collect(Collectors.toList());

            return serviceModels;
        }
        return new ArrayList<>();
    }

    @Override
    public void addPost(PostServiceModel model) {
        try {
            User user = this.userService.getUser(model.getPublisher());
            Post post = new Post();
            post.setContent(model.getContent());
            post.setPublisher(user);
            this.postRepository.saveAndFlush(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
