package com.systelab.seed.util.security.implementation;

import com.systelab.seed.util.security.PasswordDigest;

import java.security.MessageDigest;
import java.util.Base64;

public class SHA256PasswordDigest implements PasswordDigest
{
  @Override
  public String digest(String plainTextPassword)
  {
    try
    {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(plainTextPassword.getBytes("UTF-8"));
      byte[] passwordDigest = md.digest();
      return new String(Base64.getEncoder().encode(passwordDigest));
    }
    catch (Exception e)
    {
      throw new RuntimeException("Exception encoding password", e);
    }
  }

}
