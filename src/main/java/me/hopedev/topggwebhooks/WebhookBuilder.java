package me.hopedev.topggwebhooks;


public class WebhookBuilder {

    private int port = 6969;
    private String context = "dblwebhook";
    private String authorization;
    private WebhookListener listener;

    public WebhookBuilder(WebhookListener listener) {
        this.listener = listener;
    }

    public final WebhookBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public final WebhookBuilder setContext(String context) {
        this.context = context;
        return this;
    }

    public final WebhookBuilder setAuthorization(String authorization) {
        this.authorization = authorization;
        return this;
    }

    public final WebhookBuilder setListener(WebhookListener listener) {
        this.listener = listener;
        return this;
    }

    public final Webhook build() {
        return new Webhook(this.port, this.context, this.authorization, this.listener);
    }

}
