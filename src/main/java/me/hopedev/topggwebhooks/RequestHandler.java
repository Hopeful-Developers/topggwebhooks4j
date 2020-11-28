package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.hopedev.topggwebhooks.utils.RequestManager;
import me.hopedev.topggwebhooks.utils.ResponseManager;

import java.io.IOException;

public class RequestHandler implements HttpHandler {

    private final WebhookListener listener;
    private final String authorization;

    public RequestHandler(WebhookListener listener, String authorization) {
        this.listener = listener;
        this.authorization = authorization;
    }


    @Override
    public void handle(HttpExchange httpExchange) {
        RequestManager reqManager = new RequestManager(httpExchange);
        ResponseManager manager = new ResponseManager(httpExchange);
        String authorization = httpExchange.getRequestHeaders().getFirst("authorization");
        System.out.println();
        WebhookEvent event = new WebhookEvent(reqManager.parseToString(), authorization, this.listener, this.authorization);

        this.listener.onWebhookRequest(event);

        manager.setResponseCode(200).writeResponse("Received! Thank you :)");
    }
}
