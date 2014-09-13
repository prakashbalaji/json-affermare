package com.rest.parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyphen.Hyphen.filter;
import static java.util.Arrays.asList;

public class JsonParserTest {

    @Test
    public void shouldParseJsonArray() throws Exception {
        JSONObject object1 = new JSONObject(map(123, "Beck"));
        JSONObject object2 = new JSONObject(map(124, "Fowler"));
        List<JSONObject> list = asList(object1, object2);

        List<JSONObject> filtered = filter(list, d -> get(d,"id").toString().equals("123"));
        System.out.println(filtered);
    }

    private Object get(JSONObject d, String key) {
        try {
            return d.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Map map(int id, String name) {
        Map map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        return map;
    }
}
