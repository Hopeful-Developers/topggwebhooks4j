package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Webhook {


    private final int port;
    private HttpServer server;
    private final ArrayList<ContextPack> contextPacks;
    private final boolean debug;

    /**
     * @param debug        Debugging
     * @param port         Webhook Port
     * @param contextPacks ContentPack ArrayList for Event Listeners
     */
    public Webhook(boolean debug, int port, ArrayList<ContextPack> contextPacks) {
        this.debug = debug;
        this.port = port;

        this.contextPacks = contextPacks;
    }


    /**
     * Starts the Webhook HttpServer
     *
     * @return the current Webhook instance
     * @throws IOException on used Port or other
     */
    public final Webhook start() throws IOException {

        server = HttpServer.create(new InetSocketAddress(this.port), 0);
        if (this.isDebug()) {
            System.out.println("[DEBUG] Starting Webserver");
        }
        contextPacks.forEach(contextPack -> {
            ListenerPack pack = contextPack.getListenerPack();
            server.createContext("/" + contextPack.getContext(), new RequestHandler(this, pack, contextPack));
            if (this.isDebug()) {
                System.out.println("[DEBUG] Added /" + contextPack.getContext());
            }
        });

        System.out.println("Webhook started under " + server.getAddress().getHostString() + " on port " + server.getAddress().getPort() + " under the following context | listeners | authorization");
        contextPacks.forEach(contextPack -> {
            ListenerPack listenerPack = contextPack.getListenerPack();
            System.out.println("> " + contextPack.getContext() + " - " + listenerPack.getListener().getClass().getSimpleName() + " - " + listenerPack.getAuthorization());

        });
        server.start();

        return this;
    }

    /**
     * get the used Port from the current Webhook instance
     *
     * @return Integer containing the used Port of this instance
     */
    public final int getPort() {
        return this.port;
    }


    public final boolean isDebug() {
        return this.debug;
    }
}
