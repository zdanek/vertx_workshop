package io.vertx.workshop.model;

import io.vertx.core.json.JsonObject;

import java.util.Map;

/**
 * Created by bartek on 10.10.15.
 */
public class JenkinsJob extends JsonObject {

    public JenkinsJob(String json) {
        super(json);
    }

    public JenkinsJob() {
    }

    public JenkinsJob(Map<String, Object> map) {
        super(map);
    }

    public String getName() {
        return getString("name");
    }
}
