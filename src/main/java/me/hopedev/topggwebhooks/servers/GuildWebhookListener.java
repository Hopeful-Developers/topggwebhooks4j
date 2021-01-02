package me.hopedev.topggwebhooks.servers;

public interface GuildWebhookListener {


    void onWebhookRequest(final GuildWebhookEvent event);

}
