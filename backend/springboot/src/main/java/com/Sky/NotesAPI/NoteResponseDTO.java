package com.Sky.NotesAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteResponseDTO {
    private Long id;
    private String title;
    private String content;

    public NoteResponseDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}
