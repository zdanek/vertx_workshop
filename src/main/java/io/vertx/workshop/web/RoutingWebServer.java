package io.vertx.workshop.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.FaviconHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

import java.util.Optional;

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
        //SessionHandler must be preceded with SessionHandler
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        router.get("/sessionCounter").handler(ctx -> {
            int counter = (int) Optional.ofNullable(ctx.session().get("counter")).orElse(0);
            counter++;
            ctx.session().put("counter", counter);

            ctx.response().end(String.valueOf(counter));
        });

        router.get("/globalCounter").handler(ctx -> {
            int counter = (int) Optional.ofNullable(vertx.sharedData().getLocalMap("data").get("counter")).orElse(0);
            counter++;
            vertx.sharedData().getLocalMap("data").put("counter", counter);

            ctx.response().end(String.valueOf(counter));
        });


        //last!
        router.route().handler(StaticHandler.create(ROOT));
        server.requestHandler(router::accept).listen(8080);

    }
}
