package com.example.demo.domain.models.view;

public class CommentViewModel {

    private String publisher;
    private String content;
    public CommentViewModel() {
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
