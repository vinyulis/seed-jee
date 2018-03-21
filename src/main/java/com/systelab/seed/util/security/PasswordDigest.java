package com.systelab.seed.util.security;

import com.systelab.seed.util.exceptions.SeedBaseException;

public interface PasswordDigest {
    public String digest(String plainTextPassword) throws SeedBaseException;

}
