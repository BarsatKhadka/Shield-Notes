package com.project.Shield.Notes.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @Lob
    private String content;

    private String ownerUsername;
}
