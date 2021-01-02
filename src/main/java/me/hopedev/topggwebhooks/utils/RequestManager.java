package me.hopedev.topggwebhooks.utils;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RequestManager {


    private final HttpExchange exchange;
    private final String requestString;

    public RequestManager(HttpExchange exchange) {
        this.exchange = exchange;
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(this.getRequestStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        br.lines().forEach(sb::append);
        this.requestString = sb.toString();
    }

    public final InputStream getRequestStream() {
        ArrayList<String> data = new ArrayList<>();
        return this.exchange.getRequestBody();
    }

    public final String getString() {
        return this.requestString;
    }

    public final JSONObject asJson() {
        return new JSONObject(this.getString());
    }
}
