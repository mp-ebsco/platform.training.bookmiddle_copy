package com.ebsco.training.bookmiddle.util;

import com.google.common.base.Strings;

import javax.validation.ValidationException;

public class BookValidator {
    public static void validate(String id, String title, String author, String genre) {
        validate("id", id);
        validate(title, author, genre);

    }

    public static void validate(String title, String author, String genre) {
        validate("title", title);
        validate("author", author);
        validate("author", genre);
    }

    public static void validate(String name, String value) {
        if (Strings.isNullOrEmpty(value)) {
            throw new ValidationException(name + " must have a value");
        }
    }
}