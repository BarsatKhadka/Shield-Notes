package com.project.ShieldNotes.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private String ownerUsername;

}
