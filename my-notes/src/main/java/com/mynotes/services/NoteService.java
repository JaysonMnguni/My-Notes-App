package com.mynotes.services;

import com.mynotes.models.Note;
import com.mynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes(){
        return noteRepository.getAllNotes();
    }
    // END OF GET ALL NOTES FOR TESTING METHOD.

    public List<Note> getNotesByUserId(Integer user_id){
        return noteRepository.getNotesByUserId(user_id);
    }
    public int createNote(int user_id, String title, String body){
        return noteRepository.createNote(user_id, title, body);
    }
    // END OF CREATE NOTE SERVICE METHOD.

    public int updateNoteIdByUserId(String title, String body, int noted_id, int user_id){
        return noteRepository.updateNoteByNoteIdAndUserId(title, body, noted_id, user_id);
    }
    // END OF UPDATE NOTE BY ID AND USER ID SERVICE METHOD.

    public int deleteNoteByNoteIdAndUserId(int note_id, int user_id){
        return noteRepository.deleteNoteByNoteIdAndUserId(note_id, user_id);
    }
    // END OF DELETE NOTE BY ID AND USER ID SERVICE METHOD.
}
// END OF NOTE SERVICE CLASS.
