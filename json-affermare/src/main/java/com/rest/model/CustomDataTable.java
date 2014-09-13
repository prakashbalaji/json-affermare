package com.rest.model;

import cucumber.api.DataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}