package com.ebsco.training.bookmiddle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "A book metadata object containing unique ID and book attributes.")
public class BookDto {

    @NotNull
    @ApiModelProperty(required = true, value = "Id", example = "1")
    private String id;

    @NotNull
    @ApiModelProperty(required = true, value = "Title", example = "A Farewell to Arms")
    private String title;

    @NotNull
    @ApiModelProperty(required = true, value = "Author", example = "Ernest Hemingway")
    private String author;

    @NotNull
    @ApiModelProperty(required = false, value = "Genre", example = "Fiction")
    private String genre;

    public BookDto(String id) {
        this.id = id;
    }

    @JsonCreator
    public BookDto(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("author") String author, @JsonProperty("genre") String genre) {
        this(id);
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDto bookDto = (BookDto) o;

        if (!id.equals(bookDto.id)) return false;
        if (!title.equals(bookDto.title)) return false;
        return author.equals(bookDto.author);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
