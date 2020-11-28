package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Webhook {


    public static boolean started = false;
    private final int port;
    private HttpServer server;
    private final WebhookListener listener;
    private final String context;
    public final String authorization;

    public Webhook(int port, String context, String authorizationkey, WebhookListener listener) {
        this.port = port;
        this.context = context;
        authorization = authorizationkey;
        this.listener = listener;
    }

    public final Webhook start() {

        try {
            server = HttpServer.create(new InetSocketAddress(this.port), 0);
            server.createContext("/"+this.context, new RequestHandler(this.listener, this.authorization));
            server.setExecutor(null); // creates a default executor
            server.start();
            started = true;
            System.out.println("Webhook started under "+server.getAddress().getAddress().getHostAddress()+":"+server.getAddress().getPort()+"/"+this.context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public final String getContext() {
        return this.context;
    }

    public final String getAuthorization() {
        return authorization;
    }

    public final int getPort() {
        return this.port;
    }

}
