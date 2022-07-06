package com.sparta.blog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Post(PostRequestDto requestDto){
        title = requestDto.getTitle();
        contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto){
        title = requestDto.getTitle();
        contents = requestDto.getContents();
    }
}
