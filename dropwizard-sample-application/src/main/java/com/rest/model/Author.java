package com.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Author {
    private final Integer id;
    private final String name;

    private final List<Phone> phoneNumbers;

    public Author(Integer id, String name, List<Phone> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }
}
