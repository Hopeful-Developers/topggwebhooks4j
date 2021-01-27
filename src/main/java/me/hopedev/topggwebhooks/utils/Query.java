package me.hopedev.topggwebhooks.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Query {

    private final String query;


    public Query(String query) {
        this.query = query;
    }

    public final Map<String, String> getQueryMap() {
        Map<String, String> result = new HashMap<>();
        Arrays.stream(this.query.split("&")).forEach(s -> {
            String[] split2 = s.split("=");
            try {
                result.put(split2[0], split2[1]);
            } catch (Exception ignored) {
                // if some fucky-wucky happens, just add none none
                result.put("none", "none");
            }
        });


        return result;
    }

    public final String toString() {
        return this.query;
    }
}
