package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.observer.ShowExceptionSupport;
import com.example.StudySwap.model.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

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
    private Button buyNotes;


    private static final String NOT_IMPLEMENTED = "Not implemented yet";


    public void setData(Note note) {
        noteTitleLabel.setText(String.valueOf(note.getFileName()));
        subjectLabel.setText(String.valueOf(note.getSubject()));
        priceLabel.setText("€" + String.format("%.2f", note.getPrice()));
        authorLabel.setText(String.valueOf(note.getAuthor()));
    }

    public void buyNotes(){

    }
}
