package com.systelab.seed;


import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("/v1")

public class SeedApplication extends Application {

    public SeedApplication(@Context ServletConfig servletConfig) {
        super();

        OpenAPI oas = new OpenAPI();
        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(oas).prettyPrint(true)
                .resourcePackages(Stream.of("com.systelab.seed.resource").collect(Collectors.toSet()));

        try {
            new JaxrsOpenApiContextBuilder()
                    .servletConfig(servletConfig)
                    .application(this)
                    .openApiConfiguration(oasConfig)
                    .buildContext(true);
        } catch (OpenApiConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}