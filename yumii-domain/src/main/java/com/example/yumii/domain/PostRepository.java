package com.example.yumii.domain;

import java.util.List;
import java.util.Optional;

/** Port: implemented by the persistence module. */
public interface PostRepository {

    List<Post> findAll();

    Optional<Post> findById(long id);

    Post save(Post post);
}
