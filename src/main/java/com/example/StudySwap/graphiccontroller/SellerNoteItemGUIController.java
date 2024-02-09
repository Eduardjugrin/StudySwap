package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.bean.SellerBean;
import com.example.StudySwap.engineering.Singleton.Session;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
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

    public void editNoteDetails() throws IOException{
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }

    public void viewReviews() throws IOException{
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }
}
