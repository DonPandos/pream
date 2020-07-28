package org.donpandos.preamauthserver.service;

import org.donpandos.preamauthserver.entity.CustomUser;
import org.donpandos.preamauthserver.entity.User;
import org.donpandos.preamauthserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User with username " + user + "not found");
        }

        CustomUser customUser = new CustomUser(user);

        return customUser;
    }
}
