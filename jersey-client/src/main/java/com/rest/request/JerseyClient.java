package com.rest.request;

import com.rest.response.JerseyClientResponse;
import com.rest.response.Response;
import com.rest.response.ResponseStorage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import cucumber.api.java.en.Then;

public class JerseyClient {

    public static String BASE_URL = null;

    @Then("^I make a GET to \"([^\"]*)\"$")
    public void I_make_a_GET_to(String path) throws Throwable {
        Response response = new JerseyClientResponse(new Client().resource(BASE_URL + path).get(ClientResponse.class));
        ResponseStorage.initialize(response);
    }

    public static void initialize(String baseUrl){
        BASE_URL = baseUrl;
    }
}
