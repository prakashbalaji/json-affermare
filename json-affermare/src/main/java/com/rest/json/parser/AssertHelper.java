package com.rest.json.parser;

import com.rest.model.CustomDataTable;
import com.rest.model.JsonProcessor;
import cucumber.api.DataTable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;

import static com.jayway.jsonassert.JsonAssert.emptyCollection;
import static com.jayway.jsonassert.JsonAssert.with;
import static com.rest.response.ResponseStorage.response;
import static junitx.framework.Assert.fail;
import static junitx.framework.ComparableAssert.assertEquals;

public class AssertHelper {

    public static void assertCollection(String selector, DataTable table) throws Exception {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> jsonObjects = new JsonProcessor().selectJsonObjects(selector, json);
        new CustomDataTable(table).matches(jsonObjects);
    }

    public static void assertCollectionWithFilter(String selector, DataTable table, String key, String value) throws Exception {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> jsonObjects = new JsonProcessor().filterAndSelectJsonObjects(selector, json, key, value);
        new CustomDataTable(table).matches(jsonObjects);
    }

    public static void assertCollectionWithInFilter(String selector, DataTable table, String key, String values) throws Exception {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> jsonObjects = new JsonProcessor().inFilterAndSelectJsonObjects(selector, json, key, values);
        new CustomDataTable(table).matches(jsonObjects);
    }



    public static void assertEmpty(String selector) throws JSONException {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> resultJsonObjects = new JsonProcessor().selectJsonObjects(selector, json);
        assertEquals(0, resultJsonObjects.size());
    }

    public static void assertCount(String selector, Integer count) throws JSONException {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> resultJsonObjects = new JsonProcessor().selectJsonObjects(selector, json);
        assertEquals(count, resultJsonObjects.size());
    }

    public static void assertCollectionToBeUndefined(String selector, RowIterator rowIterator) {
        while (rowIterator.hasNext()) {
            TableCell cell = rowIterator.next();
            String key = "$." + selector + "[?(@." + cell.getKey() + " == " + cell.getValue() + ")]";
            try {
                with(response.json()).assertThat(key, emptyCollection());
            } catch (Throwable error) {
                fail("Redundant value found when expected to be not present: " + key + " => " + error);
            }
        }
    }










}
