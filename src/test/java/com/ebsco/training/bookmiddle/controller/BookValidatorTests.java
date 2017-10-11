package com.ebsco.training.bookmiddle.controller;

import com.ebsco.training.bookmiddle.util.BookValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

import java.awt.print.Book;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookValidatorTests {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validator_throws(){
        exception.expect(ValidationException.class);
        BookValidator.validate("a", null);
        exception.expect(ValidationException.class);
        BookValidator.validate("a", "");
    }

    @Test
    public void validator_validates(){
        BookValidator.validate("a", "b","c","d");
    }

    @Test
    public void validator_constructs(){
        BookValidator v = new BookValidator();
    }
}
