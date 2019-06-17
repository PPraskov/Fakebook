package com.example.demo.domain.models.view;


import java.util.List;

public class PostViewModel {

    private String id;
    private String publisher;
    private String content;
    private List<CommentViewModel> comments;

    public PostViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
