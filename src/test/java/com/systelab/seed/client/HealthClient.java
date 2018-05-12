package com.systelab.seed.client;

import com.systelab.seed.model.user.User;
import io.qameta.allure.Step;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
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