package io.vertx.workshop.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.workshop.model.JenkinsJob;

public class WebClient extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpClientOptions opts = new HttpClientOptions()
            .setDefaultHost("builds.apache.org")
            .setTrustAll(true)
            .setDefaultPort(443)
            .setSsl(true);
        HttpClient client = vertx.createHttpClient(opts);

        client.get("/api/json").handler(resp -> {
            System.out.println("Got response");
            resp.bodyHandler(bodyBuffer -> {
                System.out.println("here's the body");
                System.out.println(bodyBuffer.toString());
                JsonObject respJson = new JsonObject(bodyBuffer.toString());
                System.out.println(respJson.getJsonArray("jobs"));
                JsonObject first = respJson.getJsonArray("jobs").getJsonObject(0);
                JenkinsJob job = new JenkinsJob(first.getMap());
                System.out.println("Description: " + job.getName());

                //https://builds.apache.org/job/Abdera-trunk/api/json?pretty
            });
        }).end();
    }
}
