package com.ebsco.training.bookmiddle.dao;

import com.ebsco.training.bookmiddle.dto.BookDto;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Use either @Profile or @ConditionalOnProperty to control which implementation Spring loads
//@Profile("local")
@ConditionalOnProperty(value="book.useStubs", havingValue="true")
@Repository
public class BookDaoStub implements BookDaoInterface {

    private Integer idCounter = 0;

    @Override
    public List<BookDto> getBooks() {
        return Lists.newArrayList(
            new BookDto("1", "Genghis Khan and the Making of the Modern World", "Jack Weatherford", "History"),
            new BookDto("2", "Goodnight Moon", "Margaret Wise Brown", "Childrens")
        );
    }

    @Override
    public Optional<BookDto> getBookById(String id) {
        return Optional.of(new BookDto("1", "Genghis Khan and the Making of the Modern World", "Jack Weatherford", "History"));
    }

    @Override
    public Optional<BookDto> deleteBook(String id) {
        return Optional.of(new BookDto("1", "Genghis Khan and the Making of the Modern World", "Jack Weatherford", "History"));
    }

    @Override
    public Optional<BookDto> updateBook(String id, String title, String author, String genre) {
        return Optional.of(new BookDto("1", "Genghis Khan and the Making of the Modern World", "Jack Weatherford", "History"));
    }

    public BookDto createBook(String title, String author, String genre) {
        return new BookDto("1", title, author, genre);
    }
}