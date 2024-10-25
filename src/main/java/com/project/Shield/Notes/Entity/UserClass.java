package com.project.Shield.Notes.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
public class UserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 20)
    @Email
    @Column(name = "email")
    private String email;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference // Prevents circular references during JSON serialization
    private Role role;

    @NotBlank
    @Size(max = 150)
    @Column(name = "password")
    @JsonIgnore
    private String password;


    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;

    private String twoFactorSet;
    private boolean isTwoFactorEnabled = false;
    private String signUpMethod;



    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createData;

    @UpdateTimestamp
    private LocalDate updatedData;


    public UserClass(String username, String email , String password  ){
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public UserClass(String username, String email){
        this.username = username;
        this.email = email;
    }




    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserClass)) return false;
        return userId != null && userId.equals(((UserClass) o).getUserId());
    }

    @Override
    public int hashCode(){ return getClass().hashCode() ; }





}
