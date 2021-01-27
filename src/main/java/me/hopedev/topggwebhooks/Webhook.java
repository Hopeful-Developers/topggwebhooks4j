package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpServer;
import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Webhook {


    private final int port;
    private final ArrayList<PathCollection> pathCollections;
    private HttpServer server;

    /**
     * @param port            Webhook Port
     * @param pathCollections ContentPack ArrayList for Event Listeners
     */
    public Webhook(int port, ArrayList<PathCollection> pathCollections) {
        this.port = port;
        this.pathCollections = pathCollections;
    }

    /**
     * Starts the Webhook HttpServer
     *
     * @return the current Webhook instance
     * @throws IOException on used Port or other
     */
    public final Webhook start() throws IOException {

        server = HttpServer.create(new InetSocketAddress(this.port), 0);

        pathCollections.forEach(pathCollection -> {
            ListenerCollection pack = pathCollection.getListenerCollection();
            server.createContext(pathCollection.getContext(), new RequestHandler(pack));
        });

        System.out.println("Webhook started under " + server.getAddress().getHostString() + " on port " + server.getAddress().getPort() + " under the following context | listeners | authorization");
        pathCollections.forEach(pathCollection -> {
            ListenerCollection listenerCollection = pathCollection.getListenerCollection();
            if (listenerCollection.getListener() instanceof GuildWebhookListener) {
                System.out.println("> " + pathCollection.getContext() + " - (GUILD) " + listenerCollection.getListener().getClass().getSimpleName() + " - " + listenerCollection.getAuthorization());
            } else if (listenerCollection.getListener() instanceof BotWebhookListener) {
                System.out.println("> " + pathCollection.getContext() + " - (BOT) " + listenerCollection.getListener().getClass().getSimpleName() + " - " + listenerCollection.getAuthorization());
            } else {
                System.out.println("severe warning, listener from " + listenerCollection.getListener().getClass().getSimpleName() + " is not type of GuildWebhookListener/BotWebhookListener!");
            }

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


}


