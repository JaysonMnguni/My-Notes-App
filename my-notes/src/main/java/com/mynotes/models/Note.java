package com.mynotes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    private int note_id;
    private int user_id;
    private String title;
    private String body;
    private int is_updated;
    private LocalDateTime created_at;
    @Transient
    private LocalDateTime updated_at;


    /**
     * -----------------------------------------
     *  GETTERS AND SETTERS:
     * ------------------------------------------
     * */

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getIs_updated() {
        return is_updated;
    }

    public void setIs_updated(int is_updated) {
        this.is_updated = is_updated;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
