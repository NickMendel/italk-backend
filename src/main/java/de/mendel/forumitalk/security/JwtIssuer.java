package de.mendel.forumitalk.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * This class is used to issue a JWT token.
 */
@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    /**
     * Issue a JWT token for the given username and roles
     * @param username username
     * @param roles list of roles
     * @return JWT token
     */
    public String issueToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("un", username)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
