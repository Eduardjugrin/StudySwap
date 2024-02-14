package com.example.studyswap.graphiccontroller;

import com.example.studyswap.DAO.NoteDAOJDBC;
import com.example.studyswap.appcontroller.PurchaseController;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.Singleton.Session;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
                return;
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
