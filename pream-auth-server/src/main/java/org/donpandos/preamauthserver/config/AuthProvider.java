package org.donpandos.preamauthserver.config;

import lombok.extern.slf4j.Slf4j;
import org.donpandos.preamauthserver.entity.User;
import org.donpandos.preamauthserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userService.findByUsername(username);

        if(user != null && user.getUsername().equals(username)){
            log.error(password);
            log.error("encode " + passwordEncoder.matches(password, user.getPassword()));
            log.error(user.getPassword());
            if(!passwordEncoder.matches(password, user.getPassword())){
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        else throw new BadCredentialsException("Username not found");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
