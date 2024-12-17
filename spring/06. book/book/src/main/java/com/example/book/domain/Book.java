package com.example.book.domain;

import jakarta.persistence.*;
import jakarta.transaction.Status;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String subTitle;

    @Column(length = 45)
    private String author;

    @Column(length = 45)
    private  String publisher;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status{
        BORROWED, AVAILABLE
    }
}
