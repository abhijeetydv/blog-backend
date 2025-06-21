package com.example.blogbackend.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class BlogPostRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Content is required")
    private String content;
}