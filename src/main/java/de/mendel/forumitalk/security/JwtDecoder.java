package de.mendel.forumitalk.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class is used to decode the JWT token.
 */
@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties jwtProperties;

    /**
     * Decodes the given token.
     * @param token the token to decode
     * @return the decoded token
     */
    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey()))
                .build()
                .verify(token);
    }
}
