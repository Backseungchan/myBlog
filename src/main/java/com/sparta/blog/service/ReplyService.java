package com.sparta.blog.service;

import com.sparta.blog.models.Post;
import com.sparta.blog.models.Reply;
import com.sparta.blog.models.ReplyRequestDto;
import com.sparta.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    public Reply createReply(ReplyRequestDto requestDto, String username) {
        Reply reply = new Reply(requestDto,username);
        replyRepository.save(reply);
        return reply;
    }

    public List<Reply> getReplys(Long postId) {
        return replyRepository.findAllByPostId(postId);
    }

    public Long updateReply(Long id, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(id).orElseThrow(()-> new NullPointerException("해당 댓글이 없습니다.")) ;
        reply.setContents(requestDto.getContents());
        replyRepository.save(reply);
        return reply.getId();
    }

    public void removeReply(Long id) {
        replyRepository.deleteById(id);
    }
}
