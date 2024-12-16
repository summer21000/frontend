package com.example.News.controller;

import com.example.News.dto.NewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    @GetMapping("/news/new")
    public String newArticleForm(){
        return "news/new";
    }

}
