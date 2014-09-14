package com.rest.dao;

import com.rest.model.ApplicationError;
import com.rest.model.Author;
import com.rest.model.Book;
import com.rest.model.Phone;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;

public class MockDAO {
    public static final Phone OFFICE = new Phone("office", 987654321);
    public static final Phone HOME = new Phone("home", 123456789);
    public static final Author FOWLER = new Author(123, "Fowler", asList(OFFICE, HOME));
    public static final Author BECK = new Author(124, "Beck", EMPTY_LIST);
    public static final Book TDD = new Book("abc123456", "Test driven development", BECK);
    public static final Book REFACTORING = new Book("def123456", "Refactoring", FOWLER);

    public static final ApplicationError MISSING_NAME = new ApplicationError("name", "please enter the name");
    public static final ApplicationError MISSING_PHONE = new ApplicationError("phone", "please enter the phone number");


}
