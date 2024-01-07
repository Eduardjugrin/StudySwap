package DAO;


import exception.AuthorNotFoundException;
import exception.NotFoundException;
import model.Note;

import java.util.List;

public interface NoteDAO {
    public List<Note> getAllNotes();
    public List<Note> getNotesByAuthor(String author) throws AuthorNotFoundException;
}
