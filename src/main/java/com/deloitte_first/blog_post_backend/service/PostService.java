package com.deloitte_first.blog_post_backend.service;

import com.deloitte_first.blog_post_backend.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post savePost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
