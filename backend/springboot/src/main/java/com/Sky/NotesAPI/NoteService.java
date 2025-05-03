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

    public List<NoteResponseDTO> listNotes(Long userId) {
        List<Note> notes = noteRepository.findByUserId(userId);
        List<NoteResponseDTO> notesResponseDTOS = new ArrayList<>();
        notes.forEach(item -> {
            NoteResponseDTO noteResponseDTO = new NoteResponseDTO(item);
            notesResponseDTOS.add(noteResponseDTO);
        });

        return notesResponseDTOS;
    }

    public NoteResponseDTO createNote(Long userId, NoteRequestDTO noteRequestDTO) {
        Note note = new Note();
        note.setTitle(noteRequestDTO.getTitle());
        note.setContent(noteRequestDTO.getContent());
        note.setUserId(userId);
        Note savedNote = noteRepository.saveAndFlush(note);
        return new NoteResponseDTO(savedNote);
    }

    public NoteResponseDTO updateNote(Long userId, Long noteId, NoteRequestDTO noteRequestDTO) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if(note == null) {
            return null;
        }
        note.setTitle(noteRequestDTO.getTitle());
        note.setContent(noteRequestDTO.getContent());
        Note savedNote = noteRepository.saveAndFlush(note);
        return new NoteResponseDTO(savedNote);
    }

    public String deleteNote(Long userId, Long noteId) {
        if (userId != noteRepository.findById(noteId).get().getUserId()) {
            return "You don't have permission to delete this note";
        } else {
            noteRepository.deleteById(noteId);
            return "Note deleted successfully";
        }
    }
}
