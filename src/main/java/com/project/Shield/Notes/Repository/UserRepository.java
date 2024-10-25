package com.project.Shield.Notes.Repository;

import com.project.Shield.Notes.Entity.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserClass,Integer> {
    <Optional> UserClass findByUsername(String username);
}
