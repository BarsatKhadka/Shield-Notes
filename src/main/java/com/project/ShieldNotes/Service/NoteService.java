package com.project.ShieldNotes.Service;

import com.project.ShieldNotes.Entity.Notes;
import com.project.ShieldNotes.Interfaces.NoteServiceInterface;
import com.project.ShieldNotes.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class NoteService implements NoteServiceInterface {

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public Notes createNotesForUser(String username,  String content) {
        Notes note = new Notes();
        note.setContent(content);
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    @Override
    public List<Notes> getNotesForUser(String ownerUsername) {
        return noteRepository.findByOwnerUsername(ownerUsername);
    }

    @Override
    public Notes updateNotesForUser(String username , String content , Long noteId) {
        Notes note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found") );
        note.setContent(content);
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    @Override
        public void deleteNotesForUser(Long noteId) {
        Notes note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            noteRepository.delete(note);
        }
    }


    }





