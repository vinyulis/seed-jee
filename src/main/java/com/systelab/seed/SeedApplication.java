package com.systelab.seed;

import com.systelab.seed.infrastructure.RealtimePatientTracking;
import com.systelab.seed.infrastructure.auth.TokenAuthenticationFilter;
import com.systelab.seed.infrastructure.events.cdi.PatientCreated;
import com.systelab.seed.resource.PatientResource;
import com.systelab.seed.resource.UserResource;
import com.systelab.seed.util.security.CORSFilter;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/v1")

public class SeedApplication extends Application
{
  public SeedApplication()
  {
    super();

    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion("1.0.2");
    beanConfig.setSchemes(new String[] { "http", "https" });
    beanConfig.setHost("");
    beanConfig.setBasePath("/seed/v1");
    beanConfig.setResourcePackage("com.systelab.seed.resource");
    beanConfig.setScan(true);

  }

  @Override
  public Set<Class<?>> getClasses()
  {
    Set<Class<?>> yourResources = new HashSet<Class<?>>();

    yourResources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
    yourResources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

    yourResources.add(PatientResource.class);
    yourResources.add(UserResource.class);

    yourResources.add(CORSFilter.class);
    yourResources.add(TokenAuthenticationFilter.class);
    
    return yourResources;
  }
}