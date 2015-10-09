package io.vertx.workshop.periodic;

import io.vertx.core.AbstractVerticle;

public class PeriodicVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        vertx.setPeriodic(2000L,
                event -> vertx.eventBus().publish("address", "Hello"));

    }
}
