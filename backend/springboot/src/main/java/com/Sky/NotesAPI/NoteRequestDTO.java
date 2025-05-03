package com.Sky.NotesAPI;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NoteRequestDTO {
    private String title;
    private String content;

    public NoteRequestDTO(Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}