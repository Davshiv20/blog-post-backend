package com.deloitte_first.blog_post_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.deloitte_first.blog_post_backend")
public class BlogPostBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPostBackendApplication.class, args);
	}
}