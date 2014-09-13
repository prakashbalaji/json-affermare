package com.rest.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hyphen.Hyphen.filter;
import static org.apache.commons.lang.StringUtils.EMPTY;

public class JsonProcessor {


    public JsonProcessor() {

    }

    public  List<JSONObject> selectJsonObjects(String selector, Object json) throws JSONException {
        List<JSONObject> toBeReturned = new ArrayList<>();
        String restOfSelectors = restOfSelectors(selector);
        selector = firstSelector(selector);
        if (!selector.isEmpty()) {
            if (json instanceof JSONArray)
                for (JSONObject object : asList((JSONArray) json)) {
                    toBeReturned.addAll(selectJsonObjects(restOfSelectors, object.get(selector)));
                }
            else
                toBeReturned.addAll(selectJsonObjects(restOfSelectors, ((JSONObject) json).get(selector)));
        } else {
            if (json instanceof JSONArray)
                toBeReturned.addAll(asList((JSONArray) json));
            else
                toBeReturned.add((JSONObject) json);
        }

        return toBeReturned;
    }

    public List<JSONObject> filterAndSelectJsonObjects(String selector, Object json, String key, String value) throws JSONException {
        return filter(selectJsonObjects(selector,json), d -> getValueFromJsonObject(d,key).toString().equals(value));
    }

    public List<JSONObject> inFilterAndSelectJsonObjects(String selector, Object json, String key, String values) throws JSONException {
        List<String> valuesList = Arrays.asList(values.split(","));
        return filter(selectJsonObjects(selector,json), d -> valuesList.contains(getValueFromJsonObject(d,key).toString()));
    }


    private  String restOfSelectors(String selector) {
        selector = formatSelector(selector);

        int indexOfDot = selector.indexOf(".");
        if (indexOfDot == -1) return EMPTY;

        return selector.substring(indexOfDot);
    }

    private String firstSelector(String selector) {
        selector = formatSelector(selector);

        int indexOfDot = selector.indexOf(".");
        if (indexOfDot == -1) return selector;

        return selector.substring(0, indexOfDot);
    }

    private String formatSelector(String selector) {
        if (selector.contains("root"))
            selector = selector.replace("root", "");
        if (selector.startsWith("."))
            selector = selector.substring(1);
        return selector;
    }

    private List<JSONObject> asList(JSONArray json) throws JSONException {
        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            result.add((JSONObject) json.get(i));
        }
        return result;
    }


    public static Object getActualObject(JSONObject jsonObject, String key)  {
        Object actual;
        if (key.contains(".")) {
            actual = jsonObject;
            String[] keys = key.split("\\.");
            for (String nestedKey : keys) {
                actual = getValueFromJsonObject((JSONObject) actual, nestedKey);
            }
        } else {
            actual = getValueFromJsonObject(jsonObject, key);
        }
        return actual;
    }

    private static Object getValueFromJsonObject(JSONObject actual, String key) {
        try {
            return actual.get(key);
        } catch (JSONException e) {
            throw new RuntimeException("Key not found in json " + key);
        }
    }
}
