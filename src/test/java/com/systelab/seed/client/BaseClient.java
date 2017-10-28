package com.systelab.seed.client;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BaseClient
{
  private Client client;

  private static String authenticationToken = "";

  public BaseClient()
  {
    client = ClientBuilder.newClient();
  }

  protected WebTarget getWebTarget()
  {
    return client.target(this.getServerURL());
  }

  public String getServerURL()
  {
    try
    {
      Properties p = new Properties();
      p.load(getClass().getResourceAsStream("test.properties"));
      String port = p.getProperty("server.port");
      return "http://127.0.0.1:" + port + "/seed/v1";
    }
    catch (IOException e)
    {
      throw new IllegalStateException("Could not load test.properties file in package " + this.getClass().getPackage().getName(), e);
    }
  }

  public Response login(String userName, String password) throws RequestException
  {
    Form form = new Form();
    form.param("login", userName);
    form.param("password", password);

    Response response = getWebTarget().path("users/login").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

    if (response.getStatus() != 200)
    {
      throw new RequestException(response.getStatus());
    }

    authenticationToken = response.getHeaderString(HttpHeaders.AUTHORIZATION);
    return response;
  }

  public String getAuthenticationToken()
  {
    return authenticationToken;
  }

  public void discartAuthenticationToken()
  {
    authenticationToken = "";
  }
}
