package com.fifi.java.practise.springhibernate.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * Validates that the JWT token contains the intended audience in its claims.
 */
@Component
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
    private final String audience;

    AudienceValidator(@Value( "${auth0.audience}" ) String audience) {
//    	System.out.println("init AudienceValidator with " + audience);
        this.audience = audience;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);

//        System.out.println("audience " + jwt.getAudience());
//        System.out.println(jwt.getClaims());
        if (jwt.getAudience().contains(audience)) {
            return OAuth2TokenValidatorResult.success();
        }

        return OAuth2TokenValidatorResult.failure(error);
    }
}
