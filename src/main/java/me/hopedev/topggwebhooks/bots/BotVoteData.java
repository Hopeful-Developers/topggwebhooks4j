package me.hopedev.topggwebhooks.bots;

import me.hopedev.topggwebhooks.enums.VotingType;
import me.hopedev.topggwebhooks.utils.Query;
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
     * gets the User ID of the current voter
     *
     * @return Snowflake containing the UserID of the voter
     */
    public Long getUserID() {
        return this.data.getLong("user");
    }

    /**
     * gets the ID of the Bot that was voted for
     *
     * @return Snowflake containing the ID of the voted guild
     */
    public Long getBotID() {
        return this.data.getLong("bot");
    }

    /**
     * gets the type of vote that occured
     *
     * @return Enum VotingType either "UPVOTE" when it was a normal vote, "TEST" when it was a test vote through the "Test Webhook" button, INVALID when errored
     */
    public VotingType getType() {
        String type = this.data.getString("type");
        return (type.equalsIgnoreCase("upvote") ? VotingType.UPVOTE : (type.equalsIgnoreCase("test") ? VotingType.TEST : VotingType.INVALID));
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
     * @return Query Object to be able to parse query values from (see documentation)
     */
    public Query getQuery() {
        return new Query(this.data.getString("query"));
    }
}
