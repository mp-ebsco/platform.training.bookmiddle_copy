package com.ebsco.training.bookmiddle.service;

import com.ebsco.training.bookmiddle.dao.BookDao;
import com.ebsco.training.bookmiddle.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    public static final String GENRE_CHILDRENS = "Childrens";

    @Value("${book.includeChildrens:true}")
    private Boolean includeChildrens;

    @Autowired
    private BookDao bookDao;

    public List<BookDto> getBooks() {
        if (includeChildrens) {
            return bookDao.getBooks();
        } else {
            return bookDao.getBooks().stream()
                    .filter(book -> !book.getGenre().equals(GENRE_CHILDRENS))
                    .collect(Collectors.toList());
        }
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
