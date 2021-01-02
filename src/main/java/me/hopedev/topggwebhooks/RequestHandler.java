package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.hopedev.topggwebhooks.bots.BotWebhookEvent;
import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.servers.GuildWebhookEvent;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;
import me.hopedev.topggwebhooks.utils.RequestManager;
import me.hopedev.topggwebhooks.utils.ResponseManager;

public class RequestHandler implements HttpHandler {

    private final Object listenerObject;
    private final String contextAuthorization;
    private final Webhook webhookInstance;
    private final boolean debug;
    private final ListenerPack listenerPack;
    private final ContextPack contextPack;

    public RequestHandler(Webhook webhook, ListenerPack listenerPack, ContextPack contextPack) {
        this.webhookInstance = webhook;
        this.listenerPack = listenerPack;
        this.contextPack = contextPack;
        this.listenerObject = this.listenerPack.getListener();
        this.contextAuthorization = this.listenerPack.getAuthorization();
        this.debug = this.webhookInstance.isDebug();
    }

    @Override
    public void handle(HttpExchange httpExchange) {

        RequestManager reqManager = new RequestManager(httpExchange);
        ResponseManager manager = new ResponseManager(httpExchange);
        String authorization;
        try {
            authorization = httpExchange.getRequestHeaders().getFirst("Authorization");
        } catch (Exception ignored) {
            authorization = "empty";
        }
        if (this.listenerObject instanceof BotWebhookListener) {
            BotWebhookListener listener = (BotWebhookListener) this.listenerObject;

            if (this.debug) {
                System.out.println("[DEBUG]");
                System.out.println("WebhookType: GuildWebhook");
                System.out.println("RequestData: " + reqManager.getString());
                System.out.println("Context: " + this.contextPack.getContext());
                System.out.println("Associated Listener: " + this.contextPack.getListenerPack().getListener());
                System.out.println("Associated with Authorization: " + this.contextPack.getListenerPack().getAuthorization());
                System.out.println("[DEBUG]");
            }

            BotWebhookEvent event = new BotWebhookEvent(reqManager.getString(), listener, authorization, this.contextAuthorization);

            listener.onWebhookRequest(event);
        } else if (this.listenerObject instanceof GuildWebhookListener) {
            GuildWebhookListener listener = (GuildWebhookListener) this.listenerObject;

            if (this.debug) {
                System.out.println("[DEBUG]");
                System.out.println("WebhookType: GuildWebhook");
                System.out.println("RequestData: " + reqManager.getString());
                System.out.println("Context: " + this.contextPack.getContext());
                System.out.println("Associated Listener: " + this.contextPack.getListenerPack().getListener());
                System.out.println("Associated with Authorization: " + this.contextPack.getListenerPack().getAuthorization());
                System.out.println("[DEBUG]");
            }

            GuildWebhookEvent event = new GuildWebhookEvent((reqManager.getString()), listener, authorization, this.contextAuthorization);

            listener.onWebhookRequest(event);
        } else {
            System.out.println("Error handling Request");
        }


        manager.setResponseCode(200).writeResponse("Received! Thank you :)");
    }
}
