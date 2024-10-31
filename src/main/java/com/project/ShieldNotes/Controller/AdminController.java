package com.project.ShieldNotes.Controller;

import com.project.ShieldNotes.Entity.UserClass;
import com.project.ShieldNotes.Service.AdminService;
import com.project.ShieldNotes.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/getusers")
    public ResponseEntity<List<UserClass>> getAllUsers(){
        return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId, @RequestParam String roleName){
        adminService.updateUserRole(userId, roleName);
        return new ResponseEntity<>("Successfully updated user role", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(adminService.getUserById(userId), HttpStatus.OK);
    }

    

}
