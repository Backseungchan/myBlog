package com.sparta.blog.controller;

import com.sparta.blog.exception.RestApiException;
import com.sparta.blog.models.SignupRequestDto;
import com.sparta.blog.models.User;
import com.sparta.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @ResponseBody
    @PostMapping("/user/signup")
    public ResponseEntity registerUser(@RequestBody SignupRequestDto requestDto) {
        try {
            User user = userService.registerUser(requestDto);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            RestApiException restApiException = new RestApiException();
            restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
            restApiException.setErrorMessage(ex.getMessage());
            return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
        }

    }
}