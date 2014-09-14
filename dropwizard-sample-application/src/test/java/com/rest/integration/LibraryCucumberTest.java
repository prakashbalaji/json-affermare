package com.rest.integration;


import com.rest.application.LibraryApplication;
import com.rest.configuration.LibraryConfiguration;
import cucumber.api.junit.Cucumber;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
          glue = {"com.rest.steps", "com.rest.request", "com.rest.verification" })
public class LibraryCucumberTest {


    @ClassRule
    public static final DropwizardAppRule<LibraryConfiguration> RULE =
            new DropwizardAppRule<LibraryConfiguration>(LibraryApplication.class, "/Users/prakash/projects/gruppopam/DropwizardREST/configuration.yml");



}
