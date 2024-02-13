package com.example.StudySwap.exception;

public class CommandErrorException extends Exception {
    public CommandErrorException(){
        super("Command not found\n");
    }
}
