package com.example.blogbackend.repositories;

import com.example.blogbackend.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthorId(Long authorId);
    List<BlogPost> findByAuthorUsername(String username);
    List<BlogPost> findByTitleContaining(String title);
}