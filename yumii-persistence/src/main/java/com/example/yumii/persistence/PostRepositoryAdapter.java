package com.example.yumii.persistence;

import com.example.yumii.domain.Post;
import com.example.yumii.domain.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
class PostRepositoryAdapter implements PostRepository {

    private final PostJpaRepository jpaRepository;

    PostRepositoryAdapter(PostJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Post> findAll() {
        return jpaRepository.findAll().stream().map(PostEntity::toDomain).toList();
    }

    @Override
    public Optional<Post> findById(long id) {
        return jpaRepository.findById(id).map(PostEntity::toDomain);
    }

    @Override
    public Post save(Post post) {
        return jpaRepository.save(PostEntity.fromDomain(post)).toDomain();
    }
}
