package com.ebsco.training.bookmiddle.dao;

import com.ebsco.training.bookmiddle.dto.BookDto;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Use either @Profile or @ConditionalOnProperty to control which implementation Spring loads
//@Profile("local")
@ConfigurationProperties(prefix = "book.stubSettings")
@ConditionalOnProperty(value="book.useStubs", havingValue="true")
@Repository
public class BookDaoStub implements BookDaoInterface {

    private Integer idCounter = 0;
    private Integer number;
    private String staticTitle;
    private String staticAuthor;
    private String staticGenre;

    @Override
    public List<BookDto> getBooks() {

        ArrayList<BookDto> books = Lists.newArrayList(
            new BookDto("1", "Genghis Khan and the Making of the Modern World", "Jack Weatherford", "History"),
            new BookDto("2", "Goodnight Moon", "Margaret Wise Brown", "Childrens")
        );

        for (int i = 0; i < number; i++) {
            books.add(new BookDto(String.valueOf(i), staticTitle, staticAuthor, staticGenre));
        }

        return books;

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

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setStaticTitle(String staticTitle) {
        this.staticTitle = staticTitle;
    }

    public void setStaticAuthor(String staticAuthor) {
        this.staticAuthor = staticAuthor;
    }

    public void setStaticGenre(String staticGenre) {
        this.staticGenre = staticGenre;
    }

}