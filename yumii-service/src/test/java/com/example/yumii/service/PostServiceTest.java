package com.example.yumii.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.yumii.domain.Post;
import com.example.yumii.domain.PostNotFoundException;
import com.example.yumii.domain.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private final InMemoryPostRepository repository = new InMemoryPostRepository();
    private final PostService service = new PostService(repository);

    @Test
    void createsAndFindsPost() {
        Post created = service.create("Hello", "World");

        assertThat(created.id()).isNotNull();
        assertThat(service.findById(created.id()).title()).isEqualTo("Hello");
    }

    @Test
    void throwsWhenPostMissing() {
        assertThatThrownBy(() -> service.findById(42)).isInstanceOf(PostNotFoundException.class);
    }

    private static class InMemoryPostRepository implements PostRepository {
        private final List<Post> posts = new ArrayList<>();

        @Override
        public List<Post> findAll() {
            return List.copyOf(posts);
        }

        @Override
        public Optional<Post> findById(long id) {
            return posts.stream().filter(p -> p.id() == id).findFirst();
        }

        @Override
        public Post save(Post post) {
            Post saved = new Post((long) posts.size() + 1, post.title(), post.content(), post.createdAt());
            posts.add(saved);
            return saved;
        }
    }
}
