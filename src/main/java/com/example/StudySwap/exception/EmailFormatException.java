package com.example.StudySwap.exception;

public class EmailFormatException extends Exception{
    public EmailFormatException(String email){
        super("'" + email + "' \n email not valid please insert a new email");
    }
}