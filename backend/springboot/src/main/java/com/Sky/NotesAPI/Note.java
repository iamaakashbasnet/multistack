package com.Sky.NotesAPI;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "user_id")
    private Long userId;
}
