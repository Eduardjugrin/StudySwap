package com.example.StudySwap.DAO;


import com.example.StudySwap.exception.AuthorNotFoundException;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;

import java.util.List;

public interface NoteDAO {
    public List<Note> getAllNotes() throws NotFoundException;
//    public List<Note> getNotesByAuthor(String author) throws AuthorNotFoundException;
}