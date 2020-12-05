package me.hopedev.topggwebhooks;

import org.json.JSONObject;

public class VoteData {


    private final Long userID;
    private final Long botID;
    private final String type;
    private final boolean isWeekend;
    private final String query;


    /**
     * Constructor for the VoteData
     *
     * @param data parsed JSON
     */
    public VoteData(JSONObject data) {
        this.userID = data.getLong("user");
        this.botID = data.getLong("bot");
        this.type = data.getString("type");
        this.isWeekend = data.getBoolean("isWeekend");
        this.query = data.getString("query");
    }

    /**
     * gets the User ID of the current vote
     *
     * @return Long containing the UserID of the voter
     */
    public Long getUserID() {
        return this.userID;
    }

    /**
     * gets the ID of the Bot that was voted for
     *
     * @return Long containing the ID of the voted bot
     */
    public Long getBotID() {
        return this.botID;
    }

    /**
     * gets the type of vote that occured
     *
     * @return String containing either "upvote" when it was a normal vote, "test" when it was a test vote
     */
    public String getType() {
        return this.type;
    }

    /**
     * checks if it is the weekend
     *
     * @return Boolean returning if it's the weekend
     */
    public boolean isWeekend() {
        return this.isWeekend;
    }

    /**
     * gets the query of the vote page that was associated with the vote
     * Read the <a href="https://top.gg/api/docs#webhooks">top.gg api documentation</a> for more
     *
     * @return String containing the query
     */
    public String getQuery() {
        return this.query;
    }
}
