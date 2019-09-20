package com.lambdaschool.bookstore.services;


import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BookService {
    ArrayList<Book> findAll(Pageable pageable);
    Book update(Book book, long id);
    void delete(long id);
    void addBookAuthorCombo(long bookid, long authorid);
}