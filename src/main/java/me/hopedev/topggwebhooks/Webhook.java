package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Webhook {


    public static boolean started = false;
    public final String authorization;
    private final int port;
    private final WebhookListener listener;
    private final String context;
    private HttpServer server;

    /**
     * @param port             Webhook Port
     * @param context          Webhook Context
     * @param authorizationKey Webhook Authorization
     * @param listener         Webhook Handler class
     */
    public Webhook(int port, String context, String authorizationKey, WebhookListener listener) {
        this.port = port;
        this.context = context;
        authorization = authorizationKey;
        this.listener = listener;
    }


    /**
     * Starts the Webhook HttpServer
     *
     * @return the current Webhook instance
     * @throws IOException on used Port or other
     */
    public final Webhook start() throws IOException {

        server = HttpServer.create(new InetSocketAddress(this.port), 0);
        server.createContext("/" + this.context, new RequestHandler(this.listener, this.authorization));
        server.setExecutor(null); // creates a default executor
        server.start();
        started = true;
        System.out.println("Webhook started under " + server.getAddress().getAddress().getHostAddress() + ":" + server.getAddress().getPort() + "/" + this.context);

        return this;
    }


    /**
     * get the Context from the current Webhook instance
     *
     * @return String containing the Context
     */
    public final String getContext() {
        return this.context;
    }

    /**
     * get the Authorization from the current Webhook instance
     *
     * @return String containing the Authorization
     */
    public final String getAuthorization() {
        return authorization;
    }

    /**
     * get the used Port from the current Webhook instance
     *
     * @return Integer containing the used Port of this instance
     */
    public final int getPort() {
        return this.port;
    }

}
