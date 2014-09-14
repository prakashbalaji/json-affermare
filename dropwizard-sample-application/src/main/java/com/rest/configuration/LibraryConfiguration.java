package com.rest.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class LibraryConfiguration extends Configuration {
    @NotEmpty
    private String dummy;


    @JsonProperty
    public String getDummy() {
        return dummy;
    }

    @JsonProperty
    public void setDummy(String dummy) {
        this.dummy = dummy;
    }
}
