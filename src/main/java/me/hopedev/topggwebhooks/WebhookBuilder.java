package me.hopedev.topggwebhooks;


import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.enums.Options;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;

import java.util.ArrayList;
import java.util.HashMap;

public class WebhookBuilder {

    private final HashMap<String /*url context*/, ListenerPack /*ListenerPack*/> listenerStorage = new HashMap<>();
    private int port = 6969;


    /**
     * Constructor
     */

    public WebhookBuilder() {
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

    public final WebhookBuilder addGuildListener(String context, GuildWebhookListener listener, String httpAuthorization, Options... options) {
        listenerStorage.put(context, new ListenerPack(listener, httpAuthorization, options));
        return this;
    }

    public final WebhookBuilder addBotListener(String context, BotWebhookListener listener, String httpAuthorization, Options... options) {
        listenerStorage.put(context, new ListenerPack(listener, httpAuthorization, options));
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
        return new Webhook(this.port, packs);
    }

}
