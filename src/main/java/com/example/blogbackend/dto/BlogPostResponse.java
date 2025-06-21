package com.example.blogbackend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BlogPostResponse {
    private Long id;
    private String title;
    private String content;
    private String authorUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}