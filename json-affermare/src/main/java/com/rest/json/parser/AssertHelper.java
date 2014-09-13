package com.rest.json.parser;

import com.rest.model.CustomDataTable;
import cucumber.api.DataTable;
import junitx.framework.ListAssert;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.emptyCollection;
import static com.jayway.jsonassert.JsonAssert.with;
import static com.rest.response.ResponseStorage.response;
import static junit.framework.Assert.fail;
import static junitx.framework.ComparableAssert.assertEquals;
import static org.apache.commons.lang.StringUtils.EMPTY;

public class AssertHelper {

    public static void assertCollection(String selector, DataTable table) throws Exception {
        List<Map<String, String>> collection = new CustomDataTable(table).asMaps();
        Object json = new JSONTokener(response.json()).nextValue();

        List<JSONObject> resultJsonObjects = getJsonCollection(selector, json);
        List<Map<String, String>> resultTable = populateResultTable(collection, resultJsonObjects);
        ListAssert.assertEquals(collection, resultTable);
    }

    public static void assertEmpty(String selector) throws JSONException {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> resultJsonObjects = getJsonCollection(selector, json);
        assertEquals(0, resultJsonObjects.size());
    }

    public static void assertCount(String selector, Integer count) throws JSONException {
        Object json = new JSONTokener(response.json()).nextValue();
        List<JSONObject> resultJsonObjects = getJsonCollection(selector, json);
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

    private static List<JSONObject> getJsonCollection(String selector, Object json) throws JSONException {
        List<JSONObject> toBeReturned = new ArrayList<JSONObject>();
        String restOfSelectors = restOfSelectors(selector);
        selector = firstSelector(selector);
        if (!selector.isEmpty()) {
            if (json instanceof JSONArray)
                for (JSONObject object : asList((JSONArray) json)) {
                    toBeReturned.addAll(getJsonCollection(restOfSelectors, object.get(selector)));
                }
            else
                toBeReturned.addAll(getJsonCollection(restOfSelectors, ((JSONObject) json).get(selector)));
        } else {
            if (json instanceof JSONArray)
                toBeReturned.addAll(asList((JSONArray) json));
            else
                toBeReturned.add((JSONObject) json);
        }

        return toBeReturned;
    }

    private static String restOfSelectors(String selector) {
        selector = formatSelector(selector);

        int indexOfDot = selector.indexOf(".");
        if (indexOfDot == -1) return EMPTY;

        return selector.substring(indexOfDot);
    }

    private static String firstSelector(String selector) {
        selector = formatSelector(selector);

        int indexOfDot = selector.indexOf(".");
        if (indexOfDot == -1) return selector;

        return selector.substring(0, indexOfDot);
    }

    private static String formatSelector(String selector) {
        if (selector.contains("root"))
            selector = selector.replace("root", "");
        if (selector.startsWith("."))
            selector = selector.substring(1);
        return selector;
    }

    private static List<JSONObject> asList(JSONArray json) throws JSONException {
        List<JSONObject> result = new ArrayList<JSONObject>();
        for (int i = 0; i < json.length(); i++) {
            result.add((JSONObject) json.get(i));
        }
        return result;
    }

    private static List<Map<String, String>> populateResultTable(List<Map<String, String>> collection, List<JSONObject> jsonObjects) {
        List<Map<String, String>> resultTable = new ArrayList<>();

        for (JSONObject jsonObject : jsonObjects) {
            Map<String, String> resultMap = new HashMap<>();
            for (String key : getKeys(collection)) {
                Object actual;
                try {
                    actual = getActualObject(jsonObject, key);
                    resultMap.put(key, actual.toString());
                } catch (JSONException e) {
                }
            }
            resultTable.add(resultMap);
        }

        return resultTable;
    }

    private static List<String> getKeys(List<Map<String, String>> collection) {
        ArrayList<String> keys = new ArrayList<String>();
        for (Map<String, String> map : collection) {
            keys.addAll(map.keySet());
        }
        return keys;
    }

    private static Object getActualObject(JSONObject jsonObject, String key) throws JSONException {
        Object actual;
        if (key.contains(".")) {
            actual = jsonObject;
            String[] keys = key.split("\\.");
            for (String nestedKey : keys) {
                actual = ((JSONObject) actual).get(nestedKey);
            }
        } else {
            actual = jsonObject.get(key);
        }
        return actual;
    }
}
