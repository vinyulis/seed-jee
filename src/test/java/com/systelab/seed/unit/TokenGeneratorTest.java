package com.systelab.seed.unit;

import com.systelab.seed.util.security.implementation.JWTAuthenticationTokenGenerator;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.security.Key;
import java.security.SecureRandom;

/**
 * Unit Test Class to check the expected behavior for {@link JWTAuthenticationTokenGenerator}
 */
public class TokenGeneratorTest {

    private JWTAuthenticationTokenGenerator jwtTokenGenerator;

    @BeforeEach
    @DisplayName("Initialize all the resources to be tested")
    public void initialize(){
        jwtTokenGenerator = new JWTAuthenticationTokenGenerator();
    }

    @Test
    public void whenKeyIsGeneratedThenAlgorithmIsDES() {
        Key key = jwtTokenGenerator.generateKey();
        Assertions.assertEquals("DES", key.getAlgorithm(), "Unexpected Key SignAlgorithm");
    }

    @Test
    public void whenKeyIsGeneratedThenFormatIsDES() {
        Key key = jwtTokenGenerator.generateKey();
        Assertions.assertEquals("RAW", key.getFormat(), "Unexpected Key Format");
    }

    @Test
    @DisplayName("Assert example when a sentence throws an exception")
    public void givenARandomTokenWhenIsInvalidThenExceptionThrown() {
        Assertions.assertThrows(MalformedJwtException.class, () -> {
            jwtTokenGenerator.validateToken(generateRandomToken());
        });
    }

    @Test
    public void givenUserRoleUriWhenIsValidThenExceptionThrown() {
        String token = jwtTokenGenerator.issueToken("Systelab", "ADMIN", "http://127.0.0.1:13080/seed/v1/");
        Assertions.assertNotNull(token);
    }

    @Test
    public void givenUserRoleUriWhenTokenGeneratedThenValidated() throws Exception {
        String token = jwtTokenGenerator.issueToken("Systelab", "ADMIN", "http://127.0.0.1:13080/seed/v1/");
        String validated = jwtTokenGenerator.validateToken(token);

        Assertions.assertEquals("ADMIN", validated, "Unexpected Role for Validated Token");
    }

    @Test
    public void givenIncorrectDataWhenTokenGeneratedThenValidated() throws Exception {
        String token = jwtTokenGenerator.issueToken("Systelab", "USER", "http://127.0.0.1:13080/seed/v1/");
        String validated = jwtTokenGenerator.validateToken(token);

        Assertions.assertNotEquals("ADMIN", validated, "Unexpected Role for Validated Token");
    }

    private static String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        long longToken = Math.abs( random.nextLong() );
        return Long.toString( longToken, 16 );
    }
}
