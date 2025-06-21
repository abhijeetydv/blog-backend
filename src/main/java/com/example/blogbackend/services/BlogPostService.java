package com.example.blogbackend.services;

import com.example.blogbackend.dto.BlogPostRequest;
import com.example.blogbackend.models.BlogPost;
import com.example.blogbackend.models.User;
import com.example.blogbackend.repositories.BlogPostRepository;
import com.example.blogbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogPostService {
    
    @Autowired
    private BlogPostRepository blogPostRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // Get all posts
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }
    
    // Get post by ID
    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }
    
    // Get posts by user
    public List<BlogPost> getPostsByUser(Long userId) {
        return blogPostRepository.findByAuthorId(userId);
    }
    
    // Create new post
    public BlogPost createPost(BlogPostRequest request, String username) {
        User author = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
            
        BlogPost post = new BlogPost();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(author);
        
        return blogPostRepository.save(post);
    }
    
    // Update post
    public BlogPost updatePost(Long id, BlogPostRequest request, String username) {
        BlogPost post = getPostById(id);
        
        // Check if user is the author
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to update this post");
        }
        
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        
        return blogPostRepository.save(post);
    }
    
    // Delete post
    public void deletePost(Long id, String username) {
        BlogPost post = getPostById(id);
        
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized to delete this post");
        }
        
        blogPostRepository.delete(post);
    }
}