package com.example.yumii.domain;

import java.time.Instant;
import java.util.Objects;

public record Post(Long id, String title, String content, Instant createdAt) {

    public Post {
        Objects.requireNonNull(title, "title must not be null");
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
    }

    public static Post create(String title, String content) {
        return new Post(null, title, content, Instant.now());
    }
}
