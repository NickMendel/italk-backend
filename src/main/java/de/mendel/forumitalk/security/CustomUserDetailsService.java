package de.mendel.forumitalk.security;

import de.mendel.forumitalk.repository.UserRepository;
import de.mendel.forumitalk.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is used to load the user from the database.
 * It is used by the JwtAuthenticationFilter.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load user by username
     *
     * @param username username
     * @return user details
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);

        return UserPrincipal.builder()
                .username(user.getUsername())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .password(user.getPassword())
                .build();
    }
}
