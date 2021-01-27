package me.hopedev.topggwebhooks;

import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.enums.ListenerType;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;

public class PathCollection {

    private final String context;
    private final ListenerCollection listenerCollection;


    public PathCollection(String context, ListenerCollection listenerCollection) {
        this.context = context;
        this.listenerCollection = listenerCollection;
    }

    public String getContext() {
        return this.context;
    }

    public ListenerCollection getListenerCollection() {
        return this.listenerCollection;
    }

    public ListenerType getListenerType() {
        if (this.getListenerCollection().getListener() instanceof BotWebhookListener) {
            return ListenerType.BOT;
        } else if (this.getListenerCollection().getListener() instanceof GuildWebhookListener) {
            return ListenerType.GUILD;
        } else {
            return ListenerType.UNKNOWN;
        }
    }

}
