package me.hopedev.topggwebhooks.bots;

import org.json.JSONObject;

public class BotVoteData {


    private final JSONObject data;

    /**
     * Constructor for the BotVoteData
     *
     * @param data parsed JSON
     */
    public BotVoteData(JSONObject data) {
        this.data = data;
    }

    /**
     * gets the User ID of the current vote
     *
     * @return Long containing the UserID of the voter
     */
    public Long getUserID() {
        return this.data.getLong("user");
    }

    /**
     * gets the ID of the Bot that was voted for
     *
     * @return Long containing the ID of the voted bot
     */
    public Long getBotID() {
        return this.data.getLong("bot");
    }

    /**
     * gets the type of vote that occured
     *
     * @return String containing either "upvote" when it was a normal vote, "test" when it was a test vote through the "Test Webhook" button
     */
    public String getType() {
        return this.data.getString("type");
    }

    /**
     * checks if it is the weekend
     *
     * @return Boolean returning if it's the weekend
     */
    public boolean isWeekend() {
        return this.data.getBoolean("isWeekend");
    }

    /**
     * gets the query of the vote page that was associated with the vote
     * Read the <a href="https://top.gg/api/docs#webhooks">top.gg api documentation</a> for more
     *
     * @return String containing the query
     */
    public String getQuery() {
        return this.data.getString("query");
    }
}
