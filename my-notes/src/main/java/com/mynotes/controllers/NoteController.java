package com.mynotes.controllers;

import com.mynotes.dto.requests.DeleteNoteRequest;
import com.mynotes.dto.requests.NoteRequest;
import com.mynotes.dto.requests.UpdateNoteRequest;
import com.mynotes.dto.responses.GetAllNotesResponse;
import com.mynotes.dto.responses.NoteCreatedResponse;
import com.mynotes.helpers.ExtractUserIDFromTokenHelper;
import com.mynotes.models.Note;
import com.mynotes.services.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/note")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private ExtractUserIDFromTokenHelper extractUserIDFromTokenHelper;

    @GetMapping("/all")
    public ResponseEntity getAllNotes(){
        System.out.println("In Notes Controller All Method.");
        // TODO: FOR TESTING:
        List<Note> noteList = noteService.getAllNotes();
        // CHECK FOR RESULT SET:
        if(noteList.isEmpty()){
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        // END CHECK FOR RESULT SET.

        // GET AND SET NOTES LIST:
        GetAllNotesResponse getAllNotesResponse = new GetAllNotesResponse();
        getAllNotesResponse.setGetAllNotes(noteList);

        // RETURN RESPONSE:
        return new ResponseEntity(getAllNotesResponse, HttpStatus.OK);
    }
    // END OF GET ALL NOTES TEST GET METHOD.

    @GetMapping("/my_notes")
    public ResponseEntity getMyNotes(HttpServletRequest request){
        // EXTRACT USER ID FROM TOKEN:
        Integer user_id = extractUserIDFromTokenHelper.getUserIdFromToken(request);

        // CHECK / VALIDATE USER ID IF BLOCK:
        if(user_id == null){
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        // END OF CHECK / VALIDATE USER ID IF BLOCK.

        List<Note> noteList = noteService.getNotesByUserId(user_id);

        // CHECK IF NOTES ARE NOT EMPTY IF BLOCK:
        if(noteList.isEmpty() || noteList == null){
            return new ResponseEntity("No notes to display", HttpStatus.NO_CONTENT);
        }
        // CHECK IF NOTES ARE NOT EMPTY IF BLOCK.

        return new ResponseEntity(noteList, HttpStatus.OK);
    }
    // GET NOTES THAT BELONG TO USER BY ID GET METHOD.


    @PostMapping("/create")
    public ResponseEntity createNote(@RequestParam("title") String title,
                                     @RequestParam("body") String body, HttpServletRequest request){
        // GET USER ID FROM TOKEN:
        Integer user_id = extractUserIDFromTokenHelper.getUserIdFromToken(request);


        // CHECK IF USER ID IS NOT NULL IF BLOCK:
        if(user_id == null){
            // RETURN ERROR RESPONSE:
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        // END OF CHECK IF USER ID IS NOT NULL IF BLOCK.

        // CREATE NOTE:
        int result = noteService.createNote(user_id, title, body);

        // CHECK FOR RESULT SET:
        if(result != 1){
            // RETURN ERROR RESPONSE:
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        // END OF CHECK FOR RESULT SET.

        // RETURN SUCCESS RESPONSE:
        return new ResponseEntity("Note Created Successfully", HttpStatus.CREATED);
    }
    // END OF CREATE NOTE POST METHOD.

    @PutMapping("/update")
    public ResponseEntity updateNoteById(@RequestBody UpdateNoteRequest updateNoteRequest, HttpServletRequest request){
        // GET USER ID:
        Integer user_id = extractUserIDFromTokenHelper.getUserIdFromToken(request);

        // VALIDATE USER ID IF BLOCK:
        if(user_id == null){
            throw new UsernameNotFoundException("User not found!");
        }
        // END OF VALIDATE USER ID IF BLOCK.

        // UPDATE NOTE:
        int result =
                noteService.updateNoteIdByUserId(updateNoteRequest.getTitle(),
                        updateNoteRequest.getBody(),
                        updateNoteRequest.getNote_id(), user_id);

        // CHECK FOR RESULT SET IF BLOCK:
        if(result != 1){
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        // END OF CHECK FOR RESULT SET IF BLOCK.

        NoteCreatedResponse response = new NoteCreatedResponse("Note updated successfully!");
        // SUCCESS RESPONSE:
        return new ResponseEntity(response, HttpStatus.CREATED);

    }
    // END OF CREATE NOTE POST METHOD.

    @PostMapping("/delete")
    public ResponseEntity deleteNoteById(@RequestParam("note_id") String note_id, HttpServletRequest request){
        // GET USER ID:
        Integer user_id = extractUserIDFromTokenHelper.getUserIdFromToken(request);

        // VALIDATE USER ID IF BLOCK:
        if(user_id == null){
            throw new UsernameNotFoundException("User not found!");
        }
        // END OF VALIDATE USER ID IF BLOCK.

        int noteId = Integer.parseInt(note_id);

        // DELETE NOTE:
        int result = noteService.deleteNoteByNoteIdAndUserId(noteId, user_id);

        // CHECK FOR RESULT SET IF BLOCK:
        if(result != 1){
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        // END OF CHECK FOR RESULT SET IF BLOCK.

        // SUCCESS RESPONSE:
        return new ResponseEntity("Note Deleted Successfully", HttpStatus.OK);

    }
    // END OF CREATE NOTE POST METHOD.



}// END OF NOTE CONTROLLER CLASS.