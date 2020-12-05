package me.hopedev.topggwebhooks;

import org.json.JSONObject;

public class WebhookEvent {

    private final String requestString;
    private final String currentAuthorization;
    private final WebhookListener listener;
    private final String globalAuthorization;
    private boolean isValid;

    /**
     * Main constructor for the Webhook Event
     *
     * @param requestString        the full request body of the webhook
     * @param currentAuthorization authorization of the current webhook request
     * @param listener             involved Webhook Handler
     * @param globalAuthorization  global authorization of that Webhook instance
     */
    public WebhookEvent(String requestString, String currentAuthorization, WebhookListener listener, String globalAuthorization) {
        this.currentAuthorization = currentAuthorization;
        this.globalAuthorization = globalAuthorization;
        this.requestString = requestString;
        this.listener = listener;
        try {
            JSONObject object = new JSONObject(requestString);

            /*Dummy data to see if it's valid, will cause an exception when
             * The data is missing one point.
             */
            object.isEmpty();
            object.getLong("bot");
            object.getLong("user");
            object.getString("type");
            object.getBoolean("isWeekend");
            object.getString("query");
            this.isValid = true;
        } catch (Exception e) {
            this.isValid = false;
            System.out.println("Invalid Request Data");
            System.out.println(requestString);
            System.out.println("This will cause errors if trying to parse.");
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
     * gets the VoteData Object that is involved into the current WebhookEvent
     *
     * @return VoteData Object containing the vote data from the current WebhookEvent
     */
    public final VoteData getVote() {
        return new VoteData(new JSONObject(this.requestString));
    }

    /**
     * gets the Authorization from the WebhookEvent Request
     *
     * @return String containing the Authorization
     */
    public final String getAuthorization() {
        return this.currentAuthorization == null ? "empty" : this.currentAuthorization;
    }

    /**
     * gets the listener instance
     *
     * @return WebhookListener instance
     */

    public final WebhookListener getListener() {
        return this.listener;
    }

    /**
     * checks if the current Request is authorized if the Authorization
     * of the current Request matches with the Authorization bound to this WebhookEvent
     *
     * @return Boolean if the Authorization matches
     */
    public final boolean isAuthorized() {
        if (this.globalAuthorization == null) {
            return true;
        } else {
            return this.globalAuthorization.equals(this.currentAuthorization);

        }
    }

    /**
     * Checks if the Request Data is valid and matches the Data documented on the <a href="https://top.gg/api/docs#webhooks">top.gg api documentation</a>
     *
     * @return Boolean if the Request Data is valid and matches the documentation
     */
    public final boolean isValid() {
        return this.isValid;
    }


}
