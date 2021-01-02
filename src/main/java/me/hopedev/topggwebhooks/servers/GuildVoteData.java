package me.hopedev.topggwebhooks.servers;

import org.json.JSONObject;

public class GuildVoteData {


    private final JSONObject data;

    /**
     * Constructor for the BotVoteData
     *
     * @param data parsed JSON
     */
    public GuildVoteData(JSONObject data) {
        this.data = data;
    }

    /**
     * gets the Discord based ID of the current vote
     *
     * @return Snowflake containing the UserID of the voter
     */
    public Long getUserID() {
        return this.data.getLong("user");
    }

    /**
     * gets the Discord based ID of the guild that was voted for
     *
     * @return Snowflake containing the ID of the voted guild
     */
    public Long getGuildID() {
        return this.data.getLong("guild");
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
     * gets the query of the vote page that was associated with the vote
     * Read the <a href="https://top.gg/api/docs#webhooks">top.gg api documentation</a> for more
     *
     * @return String containing the query
     */
    public String getQuery() {
        return this.data.getString("query");
    }
}
