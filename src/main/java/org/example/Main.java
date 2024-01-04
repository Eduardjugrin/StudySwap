package org.example;

import Model.Buyer;
import Model.Note;
import Model.NoteContainer;

import java.util.List;

public class Main {
    static NoteContainer noteContainer = new NoteContainer();
    public static void main(String[] args) {
        Note note1 = new Note("titolo1", "content1", 1);
        Note note2 = new Note( "titolo2", "content2", 2);

        noteContainer.addNote(note1);
        noteContainer.addNote(note2);

        //ottieni e visualizza gli appunti
        List<Note> allNotes = noteContainer.getNotes();
        for(Note note : allNotes){
            System.out.println("Titolo: " + note.getTitle());
            System.out.println("Contenuto: " + note.getContent());
            System.out.println("Utente ID: " + note.getUserId());
            System.out.println("----");
        }

    }
}