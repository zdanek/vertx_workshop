package io.vertx.workshop.periodic;

import io.vertx.core.AbstractVerticle;
import io.vertx.workshop.web.RoutingWebServer;

public class PeriodicVerticle extends AbstractVerticle {

    private int i = 0;

    @Override
    public void start() throws Exception {

        System.out.println("Deployed periodinc sender");
        vertx.setPeriodic(2000L,
                event -> vertx.eventBus().publish(RoutingWebServer.WEB_CLIENT_ADDRESS, "Hello " + i++));

    }
}
