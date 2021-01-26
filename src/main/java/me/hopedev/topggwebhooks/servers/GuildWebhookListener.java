package me.hopedev.topggwebhooks.servers;

public interface GuildWebhookListener {

    /**
     * Default implementation for listening to webhooks.<br>
     * Code example:<br>
     * <code>
     * public void onWebhookRequest(GuildWebhookEvent event) {<br>
     * System.out.println(event.isValid() ? "valid" : "Not valid");<br>
     * System.out.println(event.isAuthorized() ? "Is authorized" : "is not authorized");<br>
     * <br>
     * GuildVoteData data = event.getVote();<br>
     * System.out.println("GuildID: " + data.getGuildID()); // 12345<br>
     * System.out.println("UserID: " + data.getUserID()); // 12345<br>
     * System.out.println("type: " + data.getType().name()); // UPVOTEx   <br>
     * <br>
     * <br>
     * }<br>
     * </code>
     */
    void onWebhookRequest(final GuildWebhookEvent event);

}
