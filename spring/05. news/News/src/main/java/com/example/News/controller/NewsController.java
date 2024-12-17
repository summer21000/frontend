package com.example.News.controller;

import com.example.News.domain.News;
import com.example.News.dto.NewsDto;
import com.example.News.mapper.NewsMapper;
import com.example.News.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsRepository newsRepository;

    private final NewsMapper mapper;

    @GetMapping("/new")
    public String newArticleForm(){
        return "news/new";
    }
    @PostMapping("/create")
    public String createNews(NewsDto.Post post){
        News news = mapper.newPostDtoToNews(post);
        newsRepository.save(news);
        return "redirect:/news/" + news.getNewsId();
    }

    @GetMapping("/{newsId}")
    public String getNews(@PathVariable("newsId") Long newsId, Model model) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(()-> new IllegalArgumentException("해당 계시글이 없습니다."));
        model.addAttribute("news", news);
        return "news/detail";
    }

    @GetMapping("/list")
    public String getNewsList(Model model,
                              @RequestParam(name = "page", defaultValue = "0")int page) {
        Pageable pageable = PageRequest.of(page, 7);
        Page<News> newsPage = newsRepository.findAll(pageable);
        model.addAttribute("newsPage", newsPage);
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", newsPage.hasNext());
        model.addAttribute("hasPrev", newsPage.hasPrevious());
        return "news/list";
    }
    //엔티티를 그대로 사용하는 예시
    @PostMapping("/create2")
    public String createNews(News news){
        newsRepository.save(news);
        return "";
    }

    @GetMapping("/{newsId}/delete")
    public String deleteNews(@PathVariable("newsId") Long newsId){
        newsRepository.deleteById(newsId);
        return "redirect:/news/list";
    }

    @GetMapping("/{newsId}/edit")
    public String editNewsForm(@PathVariable("newsId") Long newsId, Model model){
        News news = newsRepository.findById(newsId)
                .orElseThrow(()->new IllegalArgumentException("해당 뉴스가 존재하지 않습니다."));
        model.addAttribute("news", news);
        return "news/edit";
    }

}
