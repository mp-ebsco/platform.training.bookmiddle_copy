
package com.ebsco.training.bookmiddle.integrationtest.controller;

import com.ebsco.training.bookmiddle.dao.BookDao;
import com.ebsco.training.bookmiddle.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookDao bookDao;

    @Test
    public void getBooks_OK() throws Exception {

        // Given
        List<BookDto> expectedBooks = Lists.newArrayList(
                new BookDto("978-1-4028-9462-6", "Grapes of Wrath", "John Steinbeck", "Fiction"),
                new BookDto("673-1-4029-8465-3", "One Flew Over the Coockoo's Nest", "Ken Kesay", "Fiction"),
                new BookDto("453-1-7834-3243-2", "Green Eggs and Ham", "Dr. Seuss", "Childrens"));
        given(bookDao.getBooks()).willReturn(expectedBooks);

        // When
        ResultActions resultActions = mockMvc.perform(get("/books").accept(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedBooks)));
    }

    @Test
    public void getBookById_OK() throws Exception {

        // Given
        BookDto expectedBook = new BookDto("978-1-4028-9462-6", "Grapes of Wrath", "John Steinbeck", "Fiction");
        given(bookDao.getBookById(anyString())).willReturn(Optional.of(expectedBook));

        // When
        ResultActions resultActions = mockMvc.perform(get("/books/1").accept(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedBook)));
    }

    @Test
    public void getBookById_NOTFOUND() throws Exception {

        // Given
        given(bookDao.getBookById(anyString())).willReturn(Optional.ofNullable(null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/books/1").accept(MediaType.APPLICATION_JSON));

        // Then
        resultActions
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }
}