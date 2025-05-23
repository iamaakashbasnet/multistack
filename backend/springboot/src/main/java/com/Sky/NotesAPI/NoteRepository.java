package com.Sky.NotesAPI;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}