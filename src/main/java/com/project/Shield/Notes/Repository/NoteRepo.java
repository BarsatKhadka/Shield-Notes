package com.project.Shield.Notes.Repository;


import com.project.Shield.Notes.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note,Long> {

    List<Note> findByOwnerUsername(String ownerUsername);
}
