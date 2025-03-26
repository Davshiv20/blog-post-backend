package com.deloitte_first.blog_post_backend.service.impl;

import com.deloitte_first.blog_post_backend.entity.Role;
import com.deloitte_first.blog_post_backend.entity.User;
import com.deloitte_first.blog_post_backend.payload.LoginDto;
import com.deloitte_first.blog_post_backend.payload.RegisterDto;
import com.deloitte_first.blog_post_backend.repository.RoleRepository;
import com.deloitte_first.blog_post_backend.repository.UserRepository;
import com.deloitte_first.blog_post_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully!";
    }

    @Override
    public String register(RegisterDto registerDto) {
        // check for username exist in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return "Username is already taken!";
        }

        // check for email exist in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            return "Email is already taken!";
        }

        // create new user
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // assign role to user
        Set<Role> roles = new HashSet<>();
        Optional<Role> roleOptional = roleRepository.findByName("ADMIN");
        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Role ADMIN not found!"); // Handle properly
        }
        Role role = roleOptional.get();
        roles.add(role);
        user.setRoles(roles);

        // save user
        userRepository.save(user);
        return "User registered successfully!";
    }
}
