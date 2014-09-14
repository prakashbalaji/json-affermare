package com.rest.model;

public class Book {
    private final String isbn;
    private final String name;
    private final Author author;

    public Book(String isbn, String name, Author author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }
}
