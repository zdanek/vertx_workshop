package io.vertx.workshop.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FaviconHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

/**
 * Created by bartek on 09.10.15.
 */
public class RoutingWebServer extends AbstractVerticle {

    private final static String ROOT = "webroot";
    public static final String WEB_CLIENT_ADDRESS = "web.client";


    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions()
                        .setAddress(WEB_CLIENT_ADDRESS));
        sockJSHandler.bridge(options);

        router.route("/eventbus/*").handler(sockJSHandler);

        router.route().handler(FaviconHandler.create(ROOT + "/favicon.ico"));
        router.route().handler(StaticHandler.create(ROOT));

        server.requestHandler(router::accept).listen(8080);

    }
}
