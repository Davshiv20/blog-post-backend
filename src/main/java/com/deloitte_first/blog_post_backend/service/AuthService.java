package com.deloitte_first.blog_post_backend.service;

import com.deloitte_first.blog_post_backend.payload.LoginDto;
import com.deloitte_first.blog_post_backend.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
