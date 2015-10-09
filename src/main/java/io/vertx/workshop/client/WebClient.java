package io.vertx.workshop.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

public class WebClient extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpClientOptions opts = new HttpClientOptions()
            .setDefaultHost("builds.apache.org")
            .setTrustAll(true)
            .setDefaultPort(443)
            .setSsl(true);
        HttpClient client = vertx.createHttpClient(opts);

        client.get("/api/json").
    }
}
