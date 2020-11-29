package me.hopedev.topggwebhooks;

public interface WebhookListener {


    void onWebhookRequest(final WebhookEvent event);

}
