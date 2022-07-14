package com.sparta.blog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reply extends TimeStamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    public Reply(ReplyRequestDto requestDto, String username) {
        this.postId = requestDto.getPostId();
        this.username = username;
        this.contents = requestDto.getContents();
    }

    public void update(ReplyRequestDto requestDto) {
        contents = requestDto.getContents();
    }
}
