package com.deloitte_first.blog_post_backend.repository;

import com.deloitte_first.blog_post_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}