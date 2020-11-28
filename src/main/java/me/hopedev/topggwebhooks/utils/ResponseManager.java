package me.hopedev.topggwebhooks.utils;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseManager {

    private final HttpExchange exchange;
    private int responseCode = 200;

    public ResponseManager(HttpExchange stream) {
        this.exchange = stream;
    }


    public final OutputStream getResponseStream() {
        return this.exchange.getResponseBody();
    }

    public final ResponseManager setResponseCode(int code) {
        this.responseCode = code;
        return this;
    }


    public final void writeResponse(String res) {
        try {

            // send length

            this.exchange.sendResponseHeaders(this.responseCode, res.length());

            // send response
            this.getResponseStream().write(res.getBytes());


            // close
            this.getResponseStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public final void flush(long size) {
        try {
            this.exchange.sendResponseHeaders(this.responseCode, size);

            this.getResponseStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public final void writeResponse(JSONObject object) {
        try {

            // send length
            this.exchange.sendResponseHeaders(this.responseCode, object.toString().length());

            // send response
            this.getResponseStream().write(object.toString().getBytes());

            // close
            this.getResponseStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
