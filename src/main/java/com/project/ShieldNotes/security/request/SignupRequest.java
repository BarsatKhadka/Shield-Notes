package com.project.ShieldNotes.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(max=253)
    @Email
    private String email;

    @Getter
    @Setter
    private Set<String> role;

    @NotBlank
    @Size(min= 6, max = 50)
    private String password;
}

