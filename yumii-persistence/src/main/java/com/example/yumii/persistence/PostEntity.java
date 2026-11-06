package com.example.yumii.persistence;

import com.example.yumii.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "post")
class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 10_000)
    private String content;

    @Column(nullable = false)
    private Instant createdAt;

    protected PostEntity() {
        // for JPA
    }

    static PostEntity fromDomain(Post post) {
        PostEntity entity = new PostEntity();
        entity.id = post.id();
        entity.title = post.title();
        entity.content = post.content();
        entity.createdAt = post.createdAt();
        return entity;
    }

    Post toDomain() {
        return new Post(id, title, content, createdAt);
    }
}
