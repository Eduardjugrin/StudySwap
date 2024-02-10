package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.appcontroller.PurchaseController;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.Singleton.Session;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.model.Note;
import com.sun.security.auth.NTDomainPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class BuyerNoteItemGUIController {
    @FXML
    private Label noteTitleLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Button buyNotesButton;

    private NoteBean noteBean;


    private static final String NOT_IMPLEMENTED = "Not implemented yet";

    public void setData(NoteBean setNote) {

        noteTitleLabel.setText(String.valueOf(setNote.getFileName()));
        subjectLabel.setText(String.valueOf(setNote.getSubject()));
        priceLabel.setText("€" + String.format("%.2f", setNote.getPrice()));
        authorLabel.setText(String.valueOf(setNote.getAuthor()));
    }

    public void buyNotes() {
        try {
            if(NoteDAOJDBC.isNotePurchased(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId())){
                ShowExceptionSupport.showException("You Have already bought these notes");
            } else if (PurchaseController.buyNote(noteBean)) {
                ShowExceptionSupport.showException("Notes purchased successfully");
            }
        }catch(SQLException e){
            ShowExceptionSupport.showException("Something went wrong.\n Try again.");
        }
    }

    public void setNoteBean(NoteBean noteBean){
        this.noteBean = noteBean;
    }
}
