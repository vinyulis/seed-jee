package com.systelab.seed;

import com.systelab.seed.infrastructure.auth.TokenAuthenticationFilter;
import com.systelab.seed.resource.HealthResource;
import com.systelab.seed.resource.PatientResource;
import com.systelab.seed.resource.UserResource;
import com.systelab.seed.util.security.CORSFilter;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/v1")

public class SeedApplication extends Application {
    public SeedApplication() {
        super();

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http", "https"});
        beanConfig.setHost("");
        beanConfig.setBasePath("/seed/v1");
        beanConfig.setResourcePackage("com.systelab.seed.resource");
        beanConfig.setScan(true);

    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();

        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        resources.add(PatientResource.class);
        resources.add(UserResource.class);
        resources.add(HealthResource.class);

        resources.add(CORSFilter.class);
        resources.add(TokenAuthenticationFilter.class);

        return resources;
    }
}