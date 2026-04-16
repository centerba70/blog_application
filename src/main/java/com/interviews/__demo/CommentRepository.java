package com.interviews.__demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<BlogPostComment, Long> {
}
