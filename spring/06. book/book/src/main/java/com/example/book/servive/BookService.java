package com.example.book.servive;

import com.example.book.domain.Book;
import com.example.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    //책을 등록
    public Book insertBook(Book book){
        return bookRepository.save(book);
    }

    //책 업데이트
    public Book updateBook(Long id, Book book){
        Book b = bookRepository.findById(id)
                        .orElseThrow(()->new IllegalArgumentException("존재하지 않는 책입니다."));
        b.setTitle(book.getTitle());
        b.setSubTitle(book.getSubTitle());
        b.setAuthor(book.getAuthor());
        b.setPublisher(book.getPublisher());
        b.setStatus(book.getStatus());
        return bookRepository.save(b);
    }

    //책 업데이트(Patch)
    public Book updateBook(Long id, Book.Status status){
        Book b = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));
        b.setStatus(status);
        return bookRepository.save(b);
    }

    //책 삭제
    public void deleteBook(Long id){
        Book b = bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 책입니다."));
        if(b.getStatus() == Book.Status.BORROWED){
            throw new IllegalArgumentException("대출 중인 책인 삭제할 수 없습니다.");
        }
        bookRepository.delete(b);
    }

    //책 조회(단건)
    public Book findBook(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));
    }

    //책 조회(다건)
    public List<Book> findBooks(){
        return bookRepository.findAll();
    }
}