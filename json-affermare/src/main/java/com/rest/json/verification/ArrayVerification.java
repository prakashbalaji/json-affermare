package com.rest.json.verification;

import com.rest.json.parser.RowIterator;
import com.rest.json.parser.TableIterator;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

import static com.rest.json.parser.AssertHelper.*;

public class ArrayVerification {

    @Then("^I verify that the collection \"([^\"]*)\" contains$")
    public void I_verify_that_the_collection(String selector, DataTable table) throws Throwable {
        assertCollection(selector, table);
    }

    @Then("^I verify that the following .+ are present$")
    public void I_verify_the_root_collection(DataTable table) throws Throwable {
        assertCollection("root", table);
    }

    @Then("^I verify that the following .+ are not present$")
    public void I_verify_the_root_collection_does_not_contain(DataTable table) throws Throwable {
        TableIterator tableIterator = new TableIterator(table.asMaps());
        while (tableIterator.hasNext()) {
            RowIterator rowIterator = tableIterator.next();
            assertCollectionToBeUndefined("", rowIterator);
        }
    }

    @Then("^I verify that no .+ are present$")
    public void I_verify_that_collection_is_empty() throws Throwable {
        assertEmpty("root");
    }

    @Then("^I verify that the number of .+ are \"([^\"]*)\"$")
    public void I_verify_that_the_number_of_root_collection_are(Integer count) throws Throwable {
        assertCount("root", count);

    }

    @Then("^I verify that .+ has the following \"([^\"]*)\"$")
    public void I_verify_that_object_has_many_objects(String selector, DataTable table) throws Throwable {
        assertCollection("root."+selector, table);
    }
}
