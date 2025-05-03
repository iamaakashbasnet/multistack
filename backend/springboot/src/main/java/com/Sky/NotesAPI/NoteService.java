package com.Sky.NotesAPI;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {
    private final NoteRepository noteRepository;

    public List<NoteDTO> listNotes(Long userId) {
        List<Note> notes = noteRepository.findByUserId(userId);
        List<NoteDTO> notesResponseDTOS = new ArrayList<>();
        notes.forEach(item -> {
            NoteDTO noteResponseDTO = new NoteDTO(item);
            notesResponseDTOS.add(noteResponseDTO);
        });

        return notesResponseDTOS;
    }

    public NoteDTO createNote(Long userId, NoteDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setUserId(userId);
        Note savedNote = noteRepository.saveAndFlush(note);
        return new NoteDTO(savedNote);
    }
}
