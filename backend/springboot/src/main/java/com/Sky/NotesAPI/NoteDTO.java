package com.Sky.NotesAPI;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NoteDTO {
    private String title;
    private String content;

    public NoteDTO(Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}