package com.project.Shield.Notes.Repository;


import com.project.Shield.Notes.Entity.AppRole;
import com.project.Shield.Notes.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface roleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole RoleName);
}
