package me.hopedev.topggwebhooks;

import org.json.JSONObject;

public class VoteData {


    private final Long userID;
    private final Long botID;
    private final String type;
    private final boolean isWeekend;
    private final String query;

    public VoteData(JSONObject data) {
        this.userID = data.getLong("user");
        this.botID = data.getLong("bot");
        this.type = data.getString("type");
        this.isWeekend = data.getBoolean("isWeekend");
        this.query = data.getString("query?");
    }

    public Long getUserID() {
        return this.userID;
    }

    public Long getBotID() {
        return this.botID;
    }

    public String getType() {
        return this.type;
    }

    public boolean isWeekend() {
        return this.isWeekend;
    }

    public String getQuery() {
        return this.query;
    }
}
