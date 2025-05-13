package com.Sky.NotesAPI.messaging;

import com.Sky.NotesAPI.NoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class UserEventListener {
    private final NoteRepository noteRepository;

    public UserEventListener(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RabbitListener(queues = "user.deleted")
    @Transactional
    public void handleUserDeleted(String message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(message, Map.class);

        Long userId = Long.valueOf(data.get("user_id").toString());

        noteRepository.deleteByUserId(userId);
        System.out.println("Deleted notes for user " + userId);
    }
}
