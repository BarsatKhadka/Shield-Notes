package com.project.ShieldNotes.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;


     @ToString.Exclude
     @Enumerated(EnumType.STRING)
     private AppRole roleName;

     @OneToMany(mappedBy = "role" , fetch = FetchType.LAZY , cascade = {CascadeType.MERGE})
     @JsonBackReference
     @ToString.Exclude
    private Set<UserClass> users = new HashSet<>();

     public Role(AppRole roleName) {
         this.roleName = roleName;
     }

}
