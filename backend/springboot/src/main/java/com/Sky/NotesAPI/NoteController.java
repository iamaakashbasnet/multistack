package com.Sky.NotesAPI;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Sky.NotesAPI.utils.JwtUtils;

import java.util.List;


@RestController
@RequestMapping("/api/notes/")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final JwtUtils jwtUtil;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        Long userId = jwtUtil.extractUserId(authHeader);

        return ResponseEntity.ok(noteService.listNotes(userId));
    }

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        Long userId = jwtUtil.extractUserId(authHeader);


        return ResponseEntity.ok(noteService.createNote(userId, noteDTO));
    }
}