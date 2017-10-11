package com.ebsco.training.bookmiddle.controller;

import com.ebsco.training.bookmiddle.dto.BookDto;
import com.ebsco.training.bookmiddle.service.BookService;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private BookController controller;

    @MockBean
    BookService bookService;

    @Mock
    BookDto bookDto;

    @Before
    public void setup() {
        // initialize all the @Mock objects
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll_EntitiesPresent() {

        // Given
        List<BookDto> expectedBooks = Lists.newArrayList(
                new BookDto("978-1-4028-9462-6", "Grapes of Wrath", "John Steinbeck", "Fiction"),
                new BookDto("673-1-4029-8465-3", "One Flew Over the Coockoo's Nest", "Ken Kesay", "Fiction"),
                new BookDto("453-1-7834-3243-2", "Green Eggs and Ham", "Dr. Seuss", "Childrens"));
        when(bookService.getBooks()).thenReturn(expectedBooks);

        // When
        ResponseEntity<List<BookDto>> actualBooks = controller.getBooks();

        // Then
        assertThat(actualBooks, notNullValue());
        assertThat(actualBooks.getBody(), notNullValue());
        assertThat(actualBooks.getBody(), hasSize(3));
    }

    @Test
    public void getBooks_NoEntitiesPresent() {

        // Given
        when(bookService.getBooks()).thenReturn(Lists.newArrayList());

        // When
        ResponseEntity<List<BookDto>> actualBooks = controller.getBooks();

        // Then
        assertThat(actualBooks, notNullValue());
        assertThat(actualBooks.getBody(), notNullValue());
        assertThat(actualBooks.getBody(), is(empty()));
    }

    @Test
    public void deleteBook_EntityFound() {

        // Given
        Optional<BookDto> expectedBook = Optional.of(new BookDto("978-1-4028-9462-6", "Grapes of Wrath", "John Steinbeck", "Fiction"));
        when(bookService.deleteBook(anyString())).thenReturn(expectedBook);

        // When
        ResponseEntity<Void> response = controller.deleteBook("978-1-4028-9462-6");

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void deleteBook_EntityNotFound() {

        // Given
        Optional<BookDto> expectedBook = Optional.ofNullable(null);
        when(bookService.deleteBook(anyString())).thenReturn(expectedBook);

        // When
        ResponseEntity<Void> response = controller.deleteBook("978-1-4028-9462-6");

        // Then
        assertThat(response, notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void createBook_create() {
        // Given a book DTO

        // When
        ResponseEntity<BookDto> resp = controller.createBook(bookDto);

        // Then
        assertThat(resp.getStatusCode().is2xxSuccessful(), is(true));
    }

    @Test
    public void updateBook_notFound() {
        // Given a book DTO
        Optional<BookDto> opt = Optional.ofNullable(bookDto);
        when(bookService.updateBook(anyString(),anyString(),anyString(),anyString())).thenReturn(opt);

        // When
        ResponseEntity<BookDto> resp = controller.update("id", bookDto);

        // Then
        assertThat(resp.getStatusCode().is2xxSuccessful(), is(true));
        assertThat(resp.getBody(), is(bookDto));
    }

    @Test
    public void updateBook_success() {
        // Given a book DTO
        Optional<BookDto> opt = Optional.empty();
        when(bookService.updateBook(anyString(),anyString(),anyString(),anyString())).thenReturn(opt);

        // When
        ResponseEntity<BookDto> resp = controller.update("id", bookDto);

        // Then
        assertThat(resp.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}