package com.example.studyswap.engineering.factory;

import com.example.studyswap.dao.NoteDAO;
import com.example.studyswap.dao.NoteDAOCSV;

public class NoteDAOFactory {

    private NoteDAOFactory(){

    }

    private static NoteDAOFactory instance = null;

    //restituisce istanza factory
    public static NoteDAOFactory getInstace(){
        if(instance == null){
            instance = new NoteDAOFactory();
        }
        return instance;
    }


    //crea un'istanza di NoteDAO
    public NoteDAO createNoteDAO(){

        return new NoteDAOCSV();
//        return new NoteDAOJDBC();
    }
}
