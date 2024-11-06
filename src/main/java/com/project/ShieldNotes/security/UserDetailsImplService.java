package com.project.ShieldNotes.security;

import com.project.ShieldNotes.Entity.UserClass;
import com.project.ShieldNotes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsImplService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserClass user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public UserClass findByUserName(String userName) {
        Optional<UserClass> user = userRepository.findByUserName(userName);
        return user.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));

    }




}



