package com.sparta.blog.controller;

import com.sparta.blog.models.Post;
import com.sparta.blog.repository.PostRepository;
import com.sparta.blog.models.PostRequestDto;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    //Create
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        Post post = new Post(requestDto, username);
        postRepository.save(post);
        return post;
    }

    //Read
    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    //Read only One
    @GetMapping("/api/posts/{postId}")
    public Post getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    //Update
    @PostMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    //Delete
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }
}
