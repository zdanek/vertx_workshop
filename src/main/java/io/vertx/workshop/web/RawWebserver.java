package io.vertx.workshop.web;

import io.vertx.core.AbstractVerticle;

/**
 * Created by bartek on 09.10.15.
 */
public class RawWebserver extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        vertx.createHttpServer().requestHandler(request -> {
            String file = "";
            if (request.path().equals("/")) {
                request.response().headers().set("content-type", "text/html");
                file = "index.html";
            } else if (!request.path().contains("..")) {
                file = request.path();
            }
            request.response().sendFile("webroot/" + file);
        }).listen(8080);


    }
}
