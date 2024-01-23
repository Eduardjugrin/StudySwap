package logic.com.example.StudySwap.connection.DAO;


import logic.com.example.StudySwap.connection.exception.AuthorNotFoundException;
import logic.com.example.StudySwap.connection.model.Note;

import java.util.List;

public interface NoteDAO {
    public List<Note> getAllNotes();
    public List<Note> getNotesByAuthor(String author) throws AuthorNotFoundException;
}
