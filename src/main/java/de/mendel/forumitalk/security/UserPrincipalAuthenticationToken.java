package de.mendel.forumitalk.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * This class is used to store the user principal in the security context.
 * It is used by the JwtAuthenticationFilter.
 */
public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal principal;
    public UserPrincipalAuthenticationToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}
