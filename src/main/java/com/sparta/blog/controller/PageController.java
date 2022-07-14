package com.sparta.blog.controller;

import com.sparta.blog.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("uid", userDetails.getUsername());
        return "index";
    }

    @GetMapping("/detail")
    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("uid", userDetails.getUsername());
        return "detail";
    }
}