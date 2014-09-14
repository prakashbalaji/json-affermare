package com.rest.model;

public class Phone {

    private String type;
    private Integer number;

    public Phone(String type, Integer number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public Integer getNumber() {
        return number;
    }
}
