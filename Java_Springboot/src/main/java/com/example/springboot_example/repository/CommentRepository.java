package com.example.springboot_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_example.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
