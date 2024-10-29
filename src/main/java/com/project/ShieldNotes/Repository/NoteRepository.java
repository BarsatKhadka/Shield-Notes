package com.project.ShieldNotes.Repository;

import com.project.ShieldNotes.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {

    List<Notes> findByOwnerUsername(String ownerUsername);


}
