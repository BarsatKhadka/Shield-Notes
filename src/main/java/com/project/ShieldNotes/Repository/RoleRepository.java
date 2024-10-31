package com.project.ShieldNotes.Repository;

import com.project.ShieldNotes.Entity.AppRole;
import com.project.ShieldNotes.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
