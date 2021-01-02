package me.hopedev.topggwebhooks;

import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;

public class ContextPack {

    private final String context;
    private final ListenerPack listenerPack;


    public ContextPack(String context, ListenerPack listenerPack) {
        this.context = context;
        this.listenerPack = listenerPack;
    }

    public String getContext() {
        return this.context;
    }

    public ListenerPack getListenerPack() {
        return this.listenerPack;
    }

    public ListenerType getListenerType() {
        if (this.getListenerPack().getListener() instanceof BotWebhookListener) {
            return ListenerType.BOT;
        } else if (this.getListenerPack().getListener() instanceof GuildWebhookListener) {
            return ListenerType.GUILD;
        } else {
            return ListenerType.UNKNOWN;
        }
    }

    public enum ListenerType {
        GUILD,
        BOT,
        UNKNOWN
    }
}
