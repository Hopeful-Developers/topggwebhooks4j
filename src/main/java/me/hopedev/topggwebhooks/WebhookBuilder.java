package me.hopedev.topggwebhooks;


import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;

import java.util.ArrayList;
import java.util.HashMap;

public class WebhookBuilder {

    private int port = 6969;
    private final String context = "dblwebhook";
    private final String authorization = null;
    private final HashMap<String /*url context*/, ListenerPack /*ListenerPack*/> listenerStorage = new HashMap<>();
    private BotWebhookListener listener;
    private boolean debug = false;


    /**
     * Constructor
     */

    public WebhookBuilder() {
    }

    public WebhookBuilder(boolean debug) {
        this.debug = true;
    }

    /**
     * @param port sets the Port that is used for the Webhook
     *             Example: http://example.com:<b>6969</b>/dblwebhooks
     *             Default: <b>6969</b>
     * @return the current WebhookBuilder instance
     */
    public final WebhookBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public final WebhookBuilder addListener(String context, GuildWebhookListener listener, String httpAuthorization) {
        listenerStorage.put(context, new ListenerPack(listener, httpAuthorization));
        return this;
    }

    public final WebhookBuilder addListener(String context, BotWebhookListener listener, String httpAuthorization) {
        listenerStorage.put(context, new ListenerPack(listener, httpAuthorization));
        return this;
    }


    /**
     * Method to build the finished Webhook
     *
     * @return the built Webhook
     */
    public final Webhook build() {
        ArrayList<ContextPack> packs = new ArrayList<>();
        listenerStorage.forEach((s, o) -> packs.add(new ContextPack(s, o)));
        return new Webhook(this.debug, this.port, packs);
    }

}
