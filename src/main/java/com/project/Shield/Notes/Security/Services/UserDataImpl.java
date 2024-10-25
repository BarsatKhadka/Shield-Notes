package com.project.Shield.Notes.Security.Services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Shield.Notes.Entity.Role;
import com.project.Shield.Notes.Entity.UserClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDataImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String email;


    @JsonIgnore
    private String password;

    private boolean is2faEnabled;

    private Collection< ? extends GrantedAuthority> authorities;

    public UserDataImpl(Long id, String username, String email,
                        String password, boolean is2faEnabled,
                        Collection<? extends GrantedAuthority> authorities){

        this.id = id;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.is2faEnabled = is2faEnabled;
        this.authorities = authorities;

    }



    public UserDataImpl build(UserClass newUser){

        GrantedAuthority authority = new SimpleGrantedAuthority(newUser.getRole().getRoleName().name());

        return new UserDataImpl(
                newUser.getUserId(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.isTwoFactorEnabled(),
                List.of(authority)


        );




    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword(){
        return password;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
