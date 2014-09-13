package com.rest.model;

import cucumber.api.DataTable;
import junitx.framework.ListAssert;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rest.model.JsonProcessor.getActualObject;

public class CustomDataTable {

    private final List<Map<String, String>> maps;

    public CustomDataTable(DataTable dataTable) {
        maps = removeIgnoredValues(dataTable.asMaps());
    }

    private List<Map<String, String>> removeIgnoredValues(List<Map<String, String>> maps) {
        List<Map<String, String>> toBeReturned = new ArrayList<>();
        for (Map<String, String> map : maps) {
            HashMap<String, String> newMap = new HashMap<>(map);
            map.keySet().stream().filter(key -> map.get(key).equals("-")).forEach(newMap::remove);
            toBeReturned.add(newMap);
        }
        return toBeReturned;
    }

    public List<Map<String, String>> asMaps() {
        return maps;
    }

    public Map<String, String> asMap() {
        return maps.get(0);
    }

    public void matches(List<JSONObject> actualJsonObjects) {
        List<Map<String, String>> resultTable = populateResultTable(actualJsonObjects);
        ListAssert.assertEquals(maps, resultTable);
    }

    private List<Map<String, String>> populateResultTable(List<JSONObject> jsonObjects) {
        List<Map<String, String>> resultTable = new ArrayList<>();

        for (JSONObject jsonObject : jsonObjects) {
            Map<String, String> resultMap = new HashMap<>();
            for (String key : this.getKeys()) {
                Object actual;
                actual = getActualObject(jsonObject, key);
                resultMap.put(key, actual.toString());
            }
            resultTable.add(resultMap);
        }

        return resultTable;
    }

    private  List<String> getKeys() {
        ArrayList<String> keys = new ArrayList<String>();
        for (Map<String, String> map : maps) {
            keys.addAll(map.keySet());
        }
        return keys;
    }


}