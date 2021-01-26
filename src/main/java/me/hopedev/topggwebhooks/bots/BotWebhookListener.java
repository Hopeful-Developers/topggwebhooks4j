package me.hopedev.topggwebhooks.bots;

public interface BotWebhookListener {

    /**
     * Default implementation for listening to webhooks.<br>
     * Code example:<br>
     * <code>
     * public void onWebhookRequest(BotWebhookEvent event) {<br>
     * System.out.println(event.isAuthorized() ? "Is authorized" : "is not authorized");<br>
     * <br>
     * BotVoteData data = event.getVote();<br>
     * <br>
     * System.out.println("botID: " + data.getBotID()); // 12345<br>
     * System.out.println("UserID: " + data.getUserID()); // 12345<br>
     * System.out.println("type: " + data.getType().name()); // UPVOTE<br>
     * System.out.println("Weekend: " + data.isWeekend()); // true<br>
     * <br>
     * <br>
     * }<br>
     * </code>
     */
    void onWebhookRequest(final BotWebhookEvent event);

}
