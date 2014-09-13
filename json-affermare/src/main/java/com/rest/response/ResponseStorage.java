package com.rest.response;

public class ResponseStorage {

    public static Response response;

    public static void initialize(Response response) {
        ResponseStorage.response = response;
    }
}
