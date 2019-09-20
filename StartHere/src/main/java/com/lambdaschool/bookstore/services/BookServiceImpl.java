package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepos;


    @Override
    public ArrayList<Book> findAll(Pageable pageable) {
        ArrayList<Book> list = new ArrayList<>();
        bookRepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book update(Book book, long id) {
        Book currentBook = bookRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if (book.getBooktitle() != null) {
            currentBook.setBooktitle(book.getBooktitle());
        }
        if (book.getCopy() != 0) {
            currentBook.setCopy(book.getCopy());
        }
        if (book.getIsbn() != null) {
            currentBook.setIsbn(book.getIsbn());
        }
        return bookRepos.save(currentBook);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        if (bookRepos.findById(id).isPresent()) {
            bookRepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public void addBookAuthorCombo(long bookid, long authorid) {
        bookRepos.insertAuthorBookCombo(bookid, authorid);
    }

}