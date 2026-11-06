package com.example.yumii.endpoint;

import com.example.yumii.domain.Post;
import java.time.Instant;

public record PostDto(Long id, String title, String content, Instant createdAt) {

    static PostDto from(Post post) {
        return new PostDto(post.id(), post.title(), post.content(), post.createdAt());
    }
}
