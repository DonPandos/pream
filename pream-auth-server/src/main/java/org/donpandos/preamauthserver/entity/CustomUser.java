package org.donpandos.preamauthserver.entity;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    public CustomUser(org.donpandos.preamauthserver.entity.User user){
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
