package com.example.News.controller;

import com.example.News.domain.News;
import com.example.News.dto.NewsDto;
import com.example.News.mapper.NewsMapper;
import com.example.News.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final NewsRepository newsRepository;

    private final NewsMapper mapper;

    @GetMapping("/news/new")
    public String newArticleForm(){
        return "news/new";
    }
    @PostMapping("/news/create")
    public String createNews(NewsDto.Post post){
        System.out.println(post.toString());
        News n = mapper.newPostDtoToNews(post);
        System.out.println(n);
        newsRepository.save(n);
        return "";
    }

    //엔티티를 그대로 사용하는 예시
    @PostMapping("/news/create2")
    public String createNews(News news){
        newsRepository.save(news);
        return "";
    }

}
