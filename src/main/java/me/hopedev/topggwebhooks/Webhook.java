package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpServer;
import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Webhook {


    private final int port;
    private final ArrayList<ContextPack> contextPacks;
    private HttpServer server;

    /**
     * @param port         Webhook Port
     * @param contextPacks ContentPack ArrayList for Event Listeners
     */
    public Webhook(int port, ArrayList<ContextPack> contextPacks) {
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

        contextPacks.forEach(contextPack -> {
            ListenerPack pack = contextPack.getListenerPack();
            server.createContext("/" + contextPack.getContext(), new RequestHandler(pack));
        });

        System.out.println("Webhook started under " + server.getAddress().getHostString() + " on port " + server.getAddress().getPort() + " under the following context | listeners | authorization");
        contextPacks.forEach(contextPack -> {
            ListenerPack listenerPack = contextPack.getListenerPack();
            if (listenerPack.getListener() instanceof GuildWebhookListener) {
                System.out.println("> " + contextPack.getContext() + " - (GUILD) " + listenerPack.getListener().getClass().getSimpleName() + " - " + listenerPack.getAuthorization());
            } else if (listenerPack.getListener() instanceof BotWebhookListener) {
                System.out.println("> " + contextPack.getContext() + " - (BOT) " + listenerPack.getListener().getClass().getSimpleName() + " - " + listenerPack.getAuthorization());
            } else {
                System.out.println("severe warning, listener from " + listenerPack.getListener().getClass().getSimpleName() + " is not type of GuildWebhookListener/BotWebhookListener!");
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
