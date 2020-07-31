package org.donpandos.preamauthserver.service;


import lombok.extern.slf4j.Slf4j;
import org.donpandos.preamauthserver.entity.User;
import org.donpandos.preamauthserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }
}
