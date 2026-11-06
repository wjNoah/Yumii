package com.example.yumii.domain;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(long id) {
        super("Post " + id + " not found");
    }
}
