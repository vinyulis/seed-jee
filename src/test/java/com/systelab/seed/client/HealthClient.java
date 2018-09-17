package com.systelab.seed.client;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HealthClient extends BaseClient {

    public void getHealth() throws RequestException {
        WebTarget target = getWebTarget().path("health");

        Response response = target.request(MediaType.TEXT_PLAIN).get(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }
    }
}