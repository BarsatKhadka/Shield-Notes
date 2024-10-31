package com.project.ShieldNotes.dto;

import com.project.ShieldNotes.Entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean accountNonExpired ;
    private boolean accountNonLocked ;
    private boolean credentialsNonExpired ;
    private boolean enabled ;
    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;
    private String twoFactorSecret;
    private boolean isTwoFactorEnabled ;
    private String signUpMethod;

}
