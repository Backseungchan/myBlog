package com.sparta.blog.models;

import lombok.Getter;

@Getter
public class ReplyRequestDto {
    private Long postId;
    private String contents;

    @Override
    public String toString() {
        return "ReplyRequestDto{" +
                "postId='" + postId + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
