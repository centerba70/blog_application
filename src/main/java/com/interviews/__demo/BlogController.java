package com.interviews.__demo;

import com.interviews.__demo.json.model.BlogPostDto;
import com.interviews.__demo.json.model.CommentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class BlogController implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    public final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/welcome")
    ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("Welcome to this service!");
    }

    @PostMapping("/user/{username}")
    ResponseEntity<String> user(@PathVariable("username") String username) {
        logger.info("Received request to create new username {}", username);
        if (blogService.addUser(username)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).body(String.format("The given username %s exists already; please change it. ", username));
    }

    @PostMapping("/comment")
    ResponseEntity<String> comment(@Valid @RequestBody CommentDto comment) {
        logger.info("Received request to add new comment {}", comment);
        if (blogService.addComment(comment)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).body(String.format("The given comment %s could not be added. ", comment));
    }

    @PostMapping("/post")
    ResponseEntity<String> post(@Valid @RequestBody BlogPostDto blogPostDto) {
        logger.info("Received request to add new blog post {}", blogPostDto);
        if (blogService.addPost(blogPostDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).body(String.format("The given post %s could not be added. ", blogPostDto));
    }


}