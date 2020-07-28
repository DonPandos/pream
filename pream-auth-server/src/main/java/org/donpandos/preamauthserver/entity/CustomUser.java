package org.donpandos.preamauthserver.entity;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.stream.Collectors;

public class CustomUser extends User {
    public CustomUser(org.donpandos.preamauthserver.entity.User user){
        super(user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}
