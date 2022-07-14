package com.sparta.blog.repository;

import com.sparta.blog.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByPostId(Long postId);
}
