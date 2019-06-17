package com.example.demo.domain.models.binding;

public class PostBindingModel {

    private String title;
    private String content;

    public PostBindingModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
