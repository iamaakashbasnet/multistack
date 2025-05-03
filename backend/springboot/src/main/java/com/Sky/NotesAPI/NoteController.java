package com.Sky.NotesAPI;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notes/")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getNotes(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.listNotes(userId));
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@RequestBody NoteRequestDTO noteRequestDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.createNote(userId, noteRequestDTO));
    }

    @PutMapping("{noteId}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long noteId, @RequestBody NoteRequestDTO noteRequestDTO, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        
        return ResponseEntity.ok(noteService.updateNote(userId, noteId, noteRequestDTO));
    }

    @DeleteMapping("{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.deleteNote(userId, noteId));
    }
}