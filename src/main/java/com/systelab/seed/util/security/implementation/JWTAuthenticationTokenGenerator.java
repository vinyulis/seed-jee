package com.systelab.seed.util.security.implementation;

import com.systelab.seed.util.security.AuthenticationTokenGenerator;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationTokenGenerator implements AuthenticationTokenGenerator
{

  @Override
  public String issueToken(String username, String role, String uri)
  {
    Key key = generateKey();

    Claims customClaims = Jwts.claims();
    customClaims.put("role", role);

    return Jwts.builder().setClaims(customClaims).setSubject(username).setIssuer(uri).setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L))).signWith(SignatureAlgorithm.HS512, key).compact();
  }

  @Override
  public String validateToken(String token) throws Exception
  {
    Key key = generateKey();
    Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    Claims c = claims.getBody();
    return c.get("role").toString();
  }

  @Override
  public Key generateKey()
  {
    String keyString = "simplekey";
    return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
  }

  private Date toDate(LocalDateTime localDateTime)
  {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

}