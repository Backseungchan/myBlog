package com.sparta.blog.service;

import com.sparta.blog.models.Post;
import com.sparta.blog.repository.PostRepository;
import com.sparta.blog.models.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        post.update(requestDto);
        return id;
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new NullPointerException("해당 게시글이 존재하지 않습니다."));
    }
}
