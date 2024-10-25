package com.project.Shield.Notes.ServiceImpl;



import com.project.Shield.Notes.Entity.Note;

import java.util.List;

public interface NoteServiceImpl {

    Note createNoteForUser(Note note);

    void deleteNoteForUser(Long noteId);

    Note updateNoteForUser(String ownerUsername , String content , Long noteId);

    List<Note> getAllNotes(String ownerUsername);

}
