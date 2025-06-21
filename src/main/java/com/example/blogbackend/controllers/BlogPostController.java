package com.example.blogbackend.controllers;

import com.example.blogbackend.dto.BlogPostRequest;
import com.example.blogbackend.dto.MessageResponse;
import com.example.blogbackend.models.BlogPost;
import com.example.blogbackend.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BlogPostController {
    
    @Autowired
    private BlogPostService blogPostService;
    
    // Get all posts (public)
    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        List<BlogPost> posts = blogPostService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    
    // Get post by ID (public)
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        BlogPost post = blogPostService.getPostById(id);
        return ResponseEntity.ok(post);
    }
    
    // Get posts by user (public)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BlogPost>> getPostsByUser(@PathVariable Long userId) {
        List<BlogPost> posts = blogPostService.getPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }
    
    // Create new post (authenticated)
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BlogPost> createPost(@Valid @RequestBody BlogPostRequest request,
                                               Authentication authentication) {
        BlogPost post = blogPostService.createPost(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    
    // Update post (authenticated, owner only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id,
                                               @Valid @RequestBody BlogPostRequest request,
                                               Authentication authentication) {
        BlogPost post = blogPostService.updatePost(id, request, authentication.getName());
        return ResponseEntity.ok(post);
    }
    
    // Delete post (authenticated, owner only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable Long id,
                                                      Authentication authentication) {
        blogPostService.deletePost(id, authentication.getName());
        return ResponseEntity.ok(new MessageResponse("Post deleted successfully!"));
    }
}