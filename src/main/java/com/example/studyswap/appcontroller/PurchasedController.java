package com.example.studyswap.appcontroller;

import com.example.studyswap.dao.NoteDAO;
import com.example.studyswap.engineering.singleton.Session;
import com.example.studyswap.engineering.factory.NoteDAOFactory;
import com.example.studyswap.model.Note;

import java.util.ArrayList;
import java.util.List;

public class PurchasedController {
    public List<Note> getPurchasedNotes(){
        NoteDAO noteDAO = NoteDAOFactory.getInstace().createNoteDAO();

        List<Note> noteList = new ArrayList<>();

        String buyerEmail = Session.getCurrentSession().getBuyerBean().getEmail();

        noteList = noteDAO.getPurchasedNotes(buyerEmail);

        return noteList;
    }
}
