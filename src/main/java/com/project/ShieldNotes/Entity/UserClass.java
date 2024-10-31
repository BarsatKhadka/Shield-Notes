package com.project.ShieldNotes.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @NotBlank
    @Email
    private String email;


    @Column(name = "password")
    @JsonIgnore
    @ToString.Exclude
    private String password;

    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id" , referencedColumnName = "roleId")
    @JsonBackReference
    @ToString.Exclude
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;



    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;


    private String twoFactorSecret;
  private boolean isTwoFactorEnabled = false;
  private String signUpMethod;


  public UserClass(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UserClass(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserClass)) return false;
        return userId != null && userId.equals(((UserClass) o).getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }






}
