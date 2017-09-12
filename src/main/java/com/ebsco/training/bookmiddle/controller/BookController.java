package com.ebsco.training.bookmiddle.controller;

import com.ebsco.training.bookmiddle.dto.BookDto;
import com.ebsco.training.bookmiddle.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@Api("v1 - book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get All Books")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = true, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = BookDto.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 401, message = "UNAUTHENTICATED"),
            @ApiResponse(code = 403, message = "UNAUTHORIZED"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<List<BookDto>> getBooks() {
        return new ResponseEntity(bookService.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Get Book by ID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = true, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", responseContainer = "List", response = BookDto.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 401, message = "UNAUTHENTICATED"),
            @ApiResponse(code = 403, message = "UNAUTHORIZED"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") @ApiParam(value = "Unique identifier", example = "9") String id) {
        Optional<BookDto> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return new ResponseEntity<BookDto>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<BookDto>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create Book")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = true, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 401, message = "UNAUTHENTICATED"),
            @ApiResponse(code = 403, message = "UNAUTHORIZED"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<BookDto> createBook(@RequestBody(required = true) BookDto book) {
        return new ResponseEntity(bookService.createBook(book.getTitle(), book.getAuthor(), book.getGenre()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ApiOperation(value = "Update a pre-existing book by ID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = true, dataType = "string", paramType = "header")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 401, message = "UNAUTHENTICATED"),
            @ApiResponse(code = 403, message = "UNAUTHORIZED"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<BookDto> update(@PathVariable("id") @ApiParam(value = "Unique identifier", example = "9") String id, @RequestBody(required = true) BookDto book) {
        Optional<BookDto> result = bookService.updateBook(id, book.getTitle(), book.getAuthor(), book.getGenre());
        if (result.isPresent()) {
            return new ResponseEntity<BookDto>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ApiOperation(value = "Delete a pre-existing book by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 401, message = "UNAUTHENTICATED"),
            @ApiResponse(code = 403, message = "UNAUTHORIZED"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<Void> deleteBook(
            @PathVariable(name = "id", required = true) @ApiParam(value = "Unique identifier", example = "4") String id) {

        Optional<BookDto> deletedBook = bookService.deleteBook(id);
        return new ResponseEntity(deletedBook.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}