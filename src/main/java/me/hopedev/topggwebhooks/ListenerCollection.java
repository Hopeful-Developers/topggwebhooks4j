package me.hopedev.topggwebhooks;

import me.hopedev.topggwebhooks.enums.Options;

public class ListenerCollection {

    private final Object listenerObject;
    private final String authorization;
    private final Options[] options;

    public ListenerCollection(Object listenerObject, String authorization, Options[] options) {
        this.listenerObject = listenerObject;
        this.authorization = authorization;
        this.options = options;
    }

    public final Object getListener() {
        return this.listenerObject;
    }

    public final String getAuthorization() {
        return this.authorization;
    }

    public final Options[] getOptions() {
        return this.options;
    }
}
