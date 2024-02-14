package com.example.studyswap.exception;

public class DuplicateNoteException extends Exception{
    public DuplicateNoteException(){
        super("You have already uploaded this note.");
    }
}
