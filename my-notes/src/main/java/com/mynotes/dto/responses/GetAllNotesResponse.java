package com.mynotes.dto.responses;

import com.mynotes.models.Note;

import java.util.List;

public class GetAllNotesResponse {

    private List<Note> getAllNotes;

    public List<Note> getGetAllNotes() {
        return getAllNotes;
    }

    public void setGetAllNotes(List<Note> getAllNotes) {
        this.getAllNotes = getAllNotes;
    }
}
// END OF GET ALL NOTES RESPONSE CLASS.
