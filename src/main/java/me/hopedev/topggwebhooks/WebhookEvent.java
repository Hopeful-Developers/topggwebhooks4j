package me.hopedev.topggwebhooks;

import org.json.JSONObject;

public class WebhookEvent {

    private final String requestString;
    private final String currentauthorization;
    private final WebhookListener listener;
    private final String globalauthorization;
    public WebhookEvent(String requestString, String currentauthorization, WebhookListener listener, String globalauthorization) {
        this.currentauthorization = currentauthorization;
        this.globalauthorization = globalauthorization;
        this.requestString = requestString;
        this.listener = listener;
        try {
            JSONObject object = new JSONObject(requestString);
            object.isEmpty();
        } catch (Exception e) {
            System.out.println("Invalid Request Data");
            System.out.println(requestString);
            System.out.println("This will cause errors if trying to parse.");
        }
    }

    public final String getRequestString() {
        return this.requestString;
    }

    public final VoteData getVote() {
        return new VoteData(new JSONObject(this.requestString));
    }

    public final String getAuthorization() {
        return this.currentauthorization;
    }

    public final boolean isAuthorized() {
        return this.globalauthorization.equals(this.currentauthorization);
    }



}
