package com.example.studyswap.bean;

public class ReviewBean {
    private int id;
    private int noteId;
    private int rating;
    private String comment;

    public ReviewBean(int id, int noteId, int rating, String comment) {
        this.id = id;
        this.noteId = noteId;
        this.rating = rating;
        this.comment = comment;
    }

    public ReviewBean(String comment, int rating) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getNoteId() {
        return noteId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

