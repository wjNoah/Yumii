package com.example.yumii.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
}
