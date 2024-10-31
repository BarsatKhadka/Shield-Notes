package com.project.ShieldNotes.Interfaces;

import com.project.ShieldNotes.Entity.UserClass;
import com.project.ShieldNotes.dto.UserDTO;

import java.util.List;

public interface AdminServiceInterface {

    List<UserClass> getAllUsers();
    void updateUserRole(Long userId, String roleName);
    UserDTO getUserById(Long userId);
}
