package com.sparta.blog.controller;

import com.sparta.blog.models.Reply;
import com.sparta.blog.models.ReplyRequestDto;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/api/replys")
    public Reply createReply(@RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        System.out.println("username" + username);
        System.out.println("requestDto" + requestDto.toString());
        return replyService.createReply(requestDto, username);
    }

    //Read
    @GetMapping("/api/replys/{postId}")
    public List<Reply> getReplys(@PathVariable Long postId){
        return replyService.getReplys(postId);
    }

    //Update
    @PutMapping("/api/replys/{id}")
    public Long updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto){
        return replyService.updateReply(id, requestDto);
    }

    //Delete
    @DeleteMapping("/api/replys/{id}")
    public Long deleteReply(@PathVariable Long id){
        replyService.removeReply(id);
        return id;
    }
}

