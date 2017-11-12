package com.systelab.seed.client;

import com.systelab.seed.model.user.User;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserClient extends BaseClient {

    public List<User> get() throws RequestException {
        WebTarget target = getWebTarget().path("users");

        Response response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, getAuthenticationToken()).get(Response.class);

        if (response.getStatus() != 200) {
            throw new RequestException(response.getStatus());
        }

        return response.readEntity(new GenericType<List<User>>() {
        });
    }
}