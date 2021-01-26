package me.hopedev.topggwebhooks.bots;

import org.json.JSONObject;

public class BotWebhookEvent {

    private final String requestString, requestAuthorization, contextAuthorization;
    private final BotWebhookListener listener;
    private boolean isValid;

    /**
     * Main constructor for the Webhook Event
     *
     * @param requestString        the full request body of the webhook
     * @param listener             involved Webhook Handler
     * @param requestAuthorization Authorization from current Event
     * @param contextAuthorization Authorization from associated context
     */
    public BotWebhookEvent(String requestString, BotWebhookListener listener, String requestAuthorization, String contextAuthorization) {

        this.requestString = requestString;
        this.contextAuthorization = contextAuthorization;
        this.listener = listener;
        this.requestAuthorization = requestAuthorization;
        try {
            JSONObject object = new JSONObject(requestString);

            /*Dummy data to see if it's valid, will cause an exception when
             * The data is missing one point.
             */
            object.getLong("bot");
            object.getLong("user");
            object.getString("type");
            object.getBoolean("isWeekend");
            object.getString("query");
            this.isValid = true;
        } catch (Exception e) {
            this.isValid = false;
        }
    }

    /**
     * gets the full request body from the WebhookEvent
     *
     * @return String containing the full request body
     */
    public final String getRequestString() {
        return this.requestString;
    }

    /**
     * get the authorization from the context related to the event
     *
     * @return String containing the httpAuthorization by the context
     */

    public final String getListenerAuthorization() {
        return this.contextAuthorization;
    }

    /**
     * gets the VoteData Object that is involved into the current WebhookEvent
     *
     * @return VoteData Object containing the vote data from the current WebhookEvent
     */
    public final BotVoteData getVote() {
        return new BotVoteData(new JSONObject(this.requestString));
    }

    /**
     * gets the listener instance
     *
     * @return WebhookListener instance
     */

    public final BotWebhookListener getListener() {
        return this.listener;
    }


    /**
     * gets the used authorization by the request associated with this event
     *
     * @return none when no Authorization header is available, otherwise returns corresponding Authorization
     */
    public final String getRequestAuthorization() {
        return this.requestAuthorization;
    }

    /**
     * Checks if the Request Data is valid and matches the Data documented on the <a href="https://top.gg/api/docs#webhooks">top.gg api documentation</a>
     *
     * @return Boolean if the Request Data is valid and matches the documentation
     */
    public final boolean isValid() {
        return this.isValid;
    }

    /**
     * returns true/false if the Authorization from the request matches the one from the context associated with the event
     *
     * @return Boolean
     */
    public final boolean isAuthorized() {
        return this.getRequestAuthorization().equals(this.getListenerAuthorization());
    }
}
