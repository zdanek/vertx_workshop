package io.vertx.workshop.boot;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.workshop.client.WebClient;
import io.vertx.workshop.web.RawWebserver;

public class Starter extends AbstractVerticle {

    private static Logger LOG = LoggerFactory.getLogger(Starter.class);

    public static void main(String [] args) {
        LOG.info("info");
        Vertx.vertx().deployVerticle(Starter.class.getName());
    }

    @Override
    public void start() throws Exception {
        LOG.info("Starting vert.x 3 application");

        vertx.deployVerticle(RawWebserver.class.getName());
        vertx.deployVerticle(WebClient.class.getName());
        LOG.info("Ready!");
    }

}
