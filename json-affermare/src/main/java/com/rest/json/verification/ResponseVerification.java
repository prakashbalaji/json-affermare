package com.rest.json.verification;

import com.rest.response.ResponseStorage;
import cucumber.api.java.en.Then;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResponseVerification {

    @Then("^I verify that the status is \"([^\"]*)\"$")
    public void I_verify_that_the_status_is(String responseStatus) throws Throwable {
        assertThat(ResponseStorage.response.status(), is(Response.Status.valueOf(responseStatus).getStatusCode()));
    }
}
