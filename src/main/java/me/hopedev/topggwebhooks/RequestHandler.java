package me.hopedev.topggwebhooks;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.hopedev.topggwebhooks.bots.BotWebhookEvent;
import me.hopedev.topggwebhooks.bots.BotWebhookListener;
import me.hopedev.topggwebhooks.enums.Options;
import me.hopedev.topggwebhooks.enums.VotingType;
import me.hopedev.topggwebhooks.servers.GuildWebhookEvent;
import me.hopedev.topggwebhooks.servers.GuildWebhookListener;
import me.hopedev.topggwebhooks.utils.RequestManager;
import me.hopedev.topggwebhooks.utils.ResponseManager;

import java.util.Arrays;
import java.util.List;

public class RequestHandler implements HttpHandler {

    private final ListenerPack listenerPack;

    public RequestHandler(ListenerPack listenerPack) {
        this.listenerPack = listenerPack;
    }

    @Override
    public void handle(HttpExchange httpExchange) {
        List<Options> currOptions = null;
        boolean validationDisabled = false;
        try {


            RequestManager reqManager = new RequestManager(httpExchange);
            ResponseManager resManager = new ResponseManager(httpExchange);
            String dataString = reqManager.getString();

            String authorization;
            try {
                authorization = httpExchange.getRequestHeaders().getFirst("Authorization");
                if (authorization == null) {
                    authorization = "none";
                }
            } catch (Exception ignored) {
                authorization = "none";
            }

            // send earlier because everything needed was stored already
            resManager.setResponseCode(200).writeResponse("Received! Thank you :)");

            Object listenerObject = this.listenerPack.getListener();

            if (listenerObject instanceof BotWebhookListener) {
                BotWebhookListener listener = (BotWebhookListener) listenerObject;

                BotWebhookEvent event = new BotWebhookEvent(dataString, listener, authorization, this.listenerPack.getAuthorization());

                List<Options> optionsList = Arrays.asList(this.listenerPack.getOptions());

                // Exception purposes
                currOptions = optionsList;

                // DEBUG System.out.println("auth? "+event.isAuthorized());
                // DEBUG System.out.println("valid? "+event.isValid());

                if (optionsList.contains(Options.ONLY_LISTEN_FOR_TEST_VOTES)) {
                    if (!event.getVote().getType().equals(VotingType.TEST) && !event.getVote().getType().equals(VotingType.INVALID)) {
                        return;
                    }
                }

                //TODO please make this somewhat more efficient
                if (event.isAuthorized() && event.isValid()) {
                    listener.onWebhookRequest(event);
                    return;
                }

                if (!event.isAuthorized()) {
                    if (optionsList.contains(Options.IGNORE_AUTHORIZATION)) {
                        if (!event.isValid()) {
                            if (optionsList.contains(Options.IGNORE_VALIDATION)) {
                                validationDisabled = true;
                                listener.onWebhookRequest(event);
                            }
                        } else {
                            listener.onWebhookRequest(event);
                        }
                        return;
                    }
                }

                if (!event.isValid()) {
                    if (optionsList.contains(Options.IGNORE_VALIDATION)) {
                        validationDisabled = true;
                        if (!event.isAuthorized()) {
                            if (optionsList.contains(Options.IGNORE_AUTHORIZATION)) {
                                listener.onWebhookRequest(event);
                            }
                        } else {
                            listener.onWebhookRequest(event);
                        }
                    }
                }


            } else if (listenerObject instanceof GuildWebhookListener) {

                GuildWebhookListener listener = (GuildWebhookListener) listenerObject;
                GuildWebhookEvent event = new GuildWebhookEvent(dataString, listener, authorization, this.listenerPack.getAuthorization());

                List<Options> optionsList = Arrays.asList(this.listenerPack.getOptions());

                // Exception purposes
                currOptions = optionsList;


                if (optionsList.contains(Options.ONLY_LISTEN_FOR_TEST_VOTES)) {
                    if (!event.getVote().getType().equals(VotingType.TEST) && !event.getVote().getType().equals(VotingType.INVALID)) {
                        return;
                    }
                }

                //TODO please make this somewhat more efficient
                if (event.isAuthorized() && event.isValid()) {
                    listener.onWebhookRequest(event);
                    return;
                }

                if (!event.isAuthorized()) {
                    if (optionsList.contains(Options.IGNORE_AUTHORIZATION)) {
                        if (!event.isValid()) {
                            if (optionsList.contains(Options.IGNORE_VALIDATION)) {
                                validationDisabled = true;
                                listener.onWebhookRequest(event);
                            }
                        } else {
                            listener.onWebhookRequest(event);
                        }
                        return;
                    }
                }

                if (!event.isValid()) {
                    if (optionsList.contains(Options.IGNORE_VALIDATION)) {
                        validationDisabled = true;
                        if (!event.isAuthorized()) {
                            if (optionsList.contains(Options.IGNORE_AUTHORIZATION)) {
                                listener.onWebhookRequest(event);
                            }
                        } else {
                            listener.onWebhookRequest(event);
                        }
                    }
                }

            } else {
                System.out.println("Error handling Request");
            }

        } catch (Exception e) {
            System.err.println("Error while handling request");
            if (validationDisabled) {
                System.err.println("Note: Validation was disabled through the Listener, check first before asking for help!");
                System.err.println("Referring Option: IGNORE_VALIDATION");
            }
            System.err.print("Active Options: ");

            assert currOptions != null;
            currOptions.forEach(options -> System.err.print(options.name() + ", "));
            System.err.println();
            e.printStackTrace();
            // Debug Arrays.stream(e.getStackTrace()).iterator().forEachRemaining(traceElement -> System.err.println("at "+traceElement.getClassName() + " on line "+ traceElement.getLineNumber()+" by method "+traceElement.getMethodName()));
        }
    }
}
