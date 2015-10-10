package io.vertx.workshop.periodic;

import io.vertx.core.AbstractVerticle;
import io.vertx.workshop.web.RoutingWebServer;

/**
 * Created by bartek on 10.10.15.
 */
public class PeriodicReceiver extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        System.out.println("DEeployed periodic receiver");
        vertx.eventBus().consumer(RoutingWebServer.WEB_CLIENT_ADDRESS, msg -> {
            System.out.println("Got periodic message: " + msg.body());
        });
    }
}
