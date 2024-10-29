package com.project.ShieldNotes.Interfaces;

import com.project.ShieldNotes.Entity.Notes;

import java.util.List;

public interface NoteServiceInterface {
    Notes createNotesForUser(String username , String content);
    Notes updateNotesForUser(String username , String content , Long noteId);
    void deleteNotesForUser(Long noteId);
    List<Notes> getNotesForUser(String username);
}
