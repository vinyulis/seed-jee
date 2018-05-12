package com.systelab.seed.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Health")

@Path("health")
@Produces({MediaType.TEXT_PLAIN})
public class HealthResource {

    @ApiOperation(value = "Check Health", notes = "")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Healthy"), @ApiResponse(code = 500, message = "Internal Server Error")})

    @GET
    @PermitAll
    public Response checkHealth() {
        try {
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}