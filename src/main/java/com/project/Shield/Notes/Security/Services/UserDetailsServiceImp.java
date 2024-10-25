package com.project.Shield.Notes.Security.Services;


import com.project.Shield.Notes.Entity.UserClass;
import com.project.Shield.Notes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserClass user = userRepository.findByUsername(username);
        UserDataImpl buildData =  new UserDataImpl();
        return buildData.build(user);
    }




}
