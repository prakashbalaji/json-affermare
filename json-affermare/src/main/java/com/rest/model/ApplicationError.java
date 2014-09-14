package com.rest.model;

public class ApplicationError {

    private String field;
    private String description;

    public ApplicationError(String field, String description) {
        this.field = field;
        this.description = description;
    }
}
