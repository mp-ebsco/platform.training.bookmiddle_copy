package com.ebsco.training.bookmiddle.dao;

import com.ebsco.training.bookmiddle.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDaoInterface {
    List<BookDto> getBooks();

    Optional<BookDto> getBookById(String id);

    Optional<BookDto> deleteBook(String id);

    Optional<BookDto> updateBook(String id, String title, String author, String genre);

    BookDto createBook(String title, String author, String genre);
}
