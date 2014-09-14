package com.rest.application;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.rest.configuration.LibraryConfiguration;
import com.rest.resource.AuthorResource;
import com.rest.resource.BookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LibraryApplication extends Application<LibraryConfiguration> {
    @Override
    public void initialize(Bootstrap<LibraryConfiguration> bootstrap) {

    }

    @Override
    public void run(LibraryConfiguration configuration, Environment environment) throws Exception {
        final AuthorResource authorResource = new AuthorResource();
        final BookResource bookResource = new BookResource();
        environment.jersey().register(authorResource);
        environment.jersey().register(bookResource);
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

    }

    public static void main(String[] args) throws Exception {
        new LibraryApplication().run(args);
    }
}
