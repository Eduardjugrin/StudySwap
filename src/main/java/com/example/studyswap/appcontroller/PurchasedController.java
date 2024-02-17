package com.example.studyswap.appcontroller;

import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.bean.ReviewBean;
import com.example.studyswap.dao.NoteDAO;
import com.example.studyswap.dao.ReviewDAO;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.engineering.singleton.Session;
import com.example.studyswap.engineering.factory.NoteDAOFactory;
import com.example.studyswap.model.Note;
import com.example.studyswap.model.Review;

import java.util.ArrayList;
import java.util.List;

public class PurchasedController {
    NoteBean noteBean;

    public List<Note> getPurchasedNotes(){
        NoteDAO noteDAO = NoteDAOFactory.getInstace().createNoteDAO();

        List<Note> noteList = new ArrayList<>();

        String buyerEmail = Session.getCurrentSession().getBuyerBean().getEmail();

        noteList = noteDAO.getPurchasedNotes(buyerEmail);

        return noteList;
    }

    public void setNoteBean(NoteBean noteBean) {
        this.noteBean = noteBean;
    }

    public boolean leaveReview(ReviewBean reviewBean){
        Review review = new Review(reviewBean.getComment(), reviewBean.getRating());
        Boolean success = false;
        try{
            ReviewDAO.addReview(review, noteBean.getFileId());
            success = true;
        }catch(Exception e){
            Printer.printError(e.getMessage());
        }
        return success;

    }
}
