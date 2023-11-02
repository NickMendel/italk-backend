package de.mendel.forumitalk.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Convert a JWT to a UserPrincipal
 */
@Component
public class JwtToPrincipalConverter {

    /**
     * Convert a JWT to a UserPrincipal
     * @param jwt JWT
     * @return UserPrincipal
     */
    public UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .username(jwt.getClaim("un").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    /**
     * Extract authorities from claim
     * @param jwt JWT
     * @return list of authorities
     */
    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
