package me.hopedev.topggwebhooks.utils;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RequestManager {


    private final HttpExchange exchange;


    public RequestManager(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public final InputStream getRequestStream() {
        ArrayList<String> data = new ArrayList<>();
        return this.exchange.getRequestBody();
    }

    public final String parseToString() {
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr =  new InputStreamReader(this.getRequestStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        br.lines().forEach(sb::append);
        return sb.toString();
    }

    public final JSONObject asJson() {
        return new JSONObject(this.parseToString());
    }
}