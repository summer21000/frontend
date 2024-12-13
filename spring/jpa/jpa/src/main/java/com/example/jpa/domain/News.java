package com.example.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class News {

    @Id
    private Long newsId;
    private String title;
    private String content;
}
