package com.project.ShieldNotes.Controller;

import com.project.ShieldNotes.Entity.Notes;
import com.project.ShieldNotes.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;



    @PutMapping("/update/{id}")
    public Notes updateNotesForUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody String content , @PathVariable Long id){
        String username = userDetails.getUsername();
        return noteService.updateNotesForUser(userDetails.getUsername(),content,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotesForUser( @PathVariable Long id){
        noteService.deleteNotesForUser(id);
    }

    @GetMapping
    public List<Notes> getNotesForUser(@AuthenticationPrincipal UserDetails userDetails){
        String ownerUsername = userDetails.getUsername();
        return noteService.getNotesForUser(ownerUsername);
    }

    @PostMapping
    public Notes createNotesForUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody String content){
        String username = userDetails.getUsername();
        return noteService.createNotesForUser(username,content);

    }


}
