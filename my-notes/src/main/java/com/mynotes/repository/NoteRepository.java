package com.mynotes.repository;

import com.mynotes.models.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

    // GET ALL NOTES:
    @Query(value = "SELECT * FROM notes", nativeQuery = true)
    List<Note> getAllNotes();

    @Query(value = "SELECT * FROM notes WHERE user_id = :user_id", nativeQuery = true)
    List<Note> getNotesByUserId(@Param("user_id") int user_id);

    // UPDATE NOTE BY ID AND USER ID:
    @Modifying
    @Transactional
    @Query(value = "UPDATE notes SET title = :title, body = :body, is_updated = 1 WHERE note_id = :note_id AND user_id = :user_id",nativeQuery = true )
    int updateNoteByNoteIdAndUserId(@Param("title")String title, @Param("body")String body,@Param("note_id") int note_id ,@Param("user_id")int user_id);

    @Transactional
    @Modifying
    // DELETE NOTE BY NOTE ID AND USER ID:
    @Query(value = "DELETE FROM notes WHERE note_id = :note_id AND user_id = :user_id", nativeQuery = true)
    int deleteNoteByNoteIdAndUserId(@Param("note_id")int note_id, @Param("user_id")int user_id);

    // CREATE NOTE:
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO notes(user_id, title,body) VALUES(:user_id, :title, :body)", nativeQuery = true)
    int createNote(@Param("user_id") int user_id, @Param("title") String tile, @Param("body") String body);
}
// END OF REPOSITORY INTERFACE.
