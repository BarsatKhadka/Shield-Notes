package com.project.Shield.Notes.Service;



import com.project.Shield.Notes.Entity.Note;
import com.project.Shield.Notes.Repository.NoteRepo;
import com.project.Shield.Notes.ServiceImpl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Noteservice implements NoteServiceImpl {

    @Autowired
    private NoteRepo noteRepo;

    @Override
    public void deleteNoteForUser(Long id){
        Note targetNote = noteRepo.findById(id).orElseThrow(() -> new RuntimeException("target not found"));
        noteRepo.delete(targetNote);
    }


    @Override
    public Note createNoteForUser( Note note){
        return noteRepo.save(note);
    }

    @Override
    public List<Note> getAllNotes(String ownerUsername){
        return noteRepo.findByOwnerUsername(ownerUsername);
    }

    @Override
    public Note updateNoteForUser(String content, String ownerUsername , Long noteId){
        Note targetNote = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        targetNote.setOwnerUsername(ownerUsername);
        targetNote.setContent(content);
        return noteRepo.save(targetNote);
    }






}
