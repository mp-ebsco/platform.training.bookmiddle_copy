package com.ebsco.training.bookmiddle.service;

import com.ebsco.training.bookmiddle.dao.BookDao;
import com.ebsco.training.bookmiddle.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public List<BookDto> getBooks() {
        return bookDao.getBooks();
    }

    public Optional<BookDto> getBookById(String id) {
        return bookDao.getBookById(id);
    }

    public Optional<BookDto> deleteBook(String id) {
        return bookDao.deleteBook(id);
    }

    public BookDto createBook(String title, String author, String genre) {
        return bookDao.createBook(title, author, genre);
    }

    public Optional<BookDto> updateBook(String id, String title, String author, String genre) {
        return bookDao.updateBook(id, title, author, genre);
    }
}
