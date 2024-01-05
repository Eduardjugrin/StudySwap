package model;

import java.util.ArrayList;
import java.util.List;

//componente che gestisce la collezione di oggetti Note tramite una List
public class NoteContainer {
    private List<Note> notes;

    public NoteContainer(){
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public List<Note> getNotes(){
        return new ArrayList<>(notes);
    }
}
