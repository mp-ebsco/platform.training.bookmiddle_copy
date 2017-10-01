package com.ebsco.training.bookmiddle.dao;

import com.ebsco.training.bookmiddle.dto.BookDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDao {

    private Map<String, BookDto> booksById = new HashMap();
    private Integer idCounter = 1;

    public List<BookDto> getBooks() {
        return new ArrayList(booksById.values());
    }

    public Optional<BookDto> getBookById(String id) {
        return Optional.ofNullable(booksById.get(id));
    }

    public Optional<BookDto> deleteBook(String id) {
        return Optional.ofNullable(booksById.remove(id));
    }

    @HystrixCommand(fallbackMethod = "createBookFallback")
    //@HystrixProperty(name = "hystrix.command.default.execution.timeout.enabled", value = "false")
    public BookDto createBook(String title, String author, String genre) {
        // Throw and exception to simulate circuit-breaker activity
        if (author.equals("Rush Limbaugh")) {
            throw new RuntimeException("Author selection error");
        }
        String id = String.valueOf(idCounter++);
        booksById.put(id, new BookDto(id, title, author, genre));
        return booksById.get(id);
    }

    @SuppressWarnings("unused")
    private BookDto createBookFallback(String title, String author, String genre) {
        return new BookDto("999", "How to Be a Better Person", "Seb Hunter", "Self Help");
    }

    public Optional<BookDto> updateBook(String id, String title, String author, String genre) {
        BookDto result = booksById.get(id);
        if (result != null) {
            result.setTitle(title);
            result.setAuthor(author);
            result.setGenre(genre);
        }
        return Optional.ofNullable(result);
    }

    @PostConstruct
    void createGreetings() {
        createBook("A Farewell to Arms", "Ernest Hemingway", "Fiction");
        createBook("Cryptonomicon", "Neal Stephenson", "Fiction");
        createBook("Genghis Khan and the Making of the Modern World", "Jack Weatherford", "History");
        createBook("Goodnight Moon", "Margaret Wise Brown", "Childrens");
    }
}