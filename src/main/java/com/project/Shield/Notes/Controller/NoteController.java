package com.project.Shield.Notes.Controller;


import com.project.Shield.Notes.Entity.Note;
import com.project.Shield.Notes.Service.Noteservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private Noteservice noteservice;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping
    public List<Note> getAllNotesForUser(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        return noteservice.getAllNotes(username);
    }

    @PutMapping("/{noteId}")
    public Note updateNoteForUser(@RequestBody String content , @PathVariable long noteId, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        return noteservice.updateNoteForUser(content,username,noteId);
    }

    @PostMapping
    public Note createNoteForUser(@RequestBody Note note, @AuthenticationPrincipal UserDetails userDetails){
        // Get username from security context
        String username = userDetails.getUsername();
        note.setOwnerUsername(username); // Set the logged-in user's username
        return noteservice.createNoteForUser(note);
    }


    @DeleteMapping("/delete/{noteId}")
    public void deleteNoteForUser(@PathVariable long noteId){
        noteservice.deleteNoteForUser(noteId);
    }



}
