package me.hopedev.topggwebhooks;


public class WebhookBuilder {

    private int port = 6969;
    private String context = "dblwebhook";
    private String authorization = null;
    private WebhookListener listener;


    /**
     * Gets the constructor for the WebhookBuilder
     *
     * @param listener Class for handling the Webhook Requests
     */

    public WebhookBuilder(WebhookListener listener) {
        this.listener = listener;
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

    /**
     * @param context the Context of the Webhook
     *                Example: http://example.com:6969/<b>dblwebhook</b>
     *                Default: <b>dblwebhook</b>
     * @return the current WebhookBuilder instance
     */
    public final WebhookBuilder setContext(String context) {
        this.context = context;
        return this;
    }

    /**
     * @param authorization the Authorization that identifies top.gg on your webhook
     *                      Web requests to your Webhoook must include these. If empty, they won't be respected.
     *                      Default: null
     * @return the current WebhookBuilder instance
     */
    public final WebhookBuilder setAuthorization(String authorization) {
        this.authorization = authorization;
        return this;
    }

    /**
     * @param listener the Listener class that is going to handle the Webhook Requests
     * @return the current WebhookBuilder instance
     * @deprecated
     */
    public final WebhookBuilder setListener(WebhookListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * Method to build the finished Webhook
     *
     * @return the built Webhook
     */
    public final Webhook build() {
        return new Webhook(this.port, this.context, this.authorization, this.listener);
    }

}
