package me.hopedev.topggwebhooks;

public class ListenerPack {

    private final Object listenerObject;
    private final String authorization;

    public ListenerPack(Object listenerObject, String authorization) {
        this.listenerObject = listenerObject;
        this.authorization = authorization;
    }

    public final Object getListener() {
        return this.listenerObject;
    }

    public final String getAuthorization() {
        return this.authorization;
    }
}
