package com.example.yumii.service;

import com.example.yumii.domain.Post;
import com.example.yumii.domain.PostNotFoundException;
import com.example.yumii.domain.PostRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public Post create(String title, String content) {
        return postRepository.save(Post.create(title, content));
    }
}
