package com.example.studyswap.graphiccontroller;

import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.observer.ShowExceptionSupport;
import com.example.studyswap.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class SellerNoteItemGUIController {
    @FXML
    private Label noteTitleLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Button editDetails;


    private NoteBean noteBean;
    private static final String NOT_IMPLEMENTED = "Not implemented yet";


    public void setData(Note note) {
        noteTitleLabel.setText(String.valueOf(note.getFileName()));
        subjectLabel.setText(String.valueOf(note.getSubject()));
        priceLabel.setText("€" + String.format("%.2f", note.getPrice()));
    }

    public void editNoteDetails(){
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }

    public void viewReviews(){
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }
}
