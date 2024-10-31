package com.project.ShieldNotes.Service;

import com.project.ShieldNotes.Entity.AppRole;
import com.project.ShieldNotes.Entity.Role;
import com.project.ShieldNotes.Entity.UserClass;
import com.project.ShieldNotes.Interfaces.AdminServiceInterface;
import com.project.ShieldNotes.Repository.RoleRepository;
import com.project.ShieldNotes.Repository.UserRepository;
import com.project.ShieldNotes.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserClass> getAllUsers(){
        return userRepository.findAll();
    }
    public void updateUserRole(Long userId, String roleName){
        UserClass findUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole).orElseThrow(() -> new RuntimeException("Role Not Found"));
        findUser.setRole(role);
        userRepository.save(findUser);
    }

    public UserDTO getUserById(Long userId){
        UserClass findUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        return convertToDTO(findUser);
    }

    private UserDTO convertToDTO(UserClass user){
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod()
        );
    }




}
