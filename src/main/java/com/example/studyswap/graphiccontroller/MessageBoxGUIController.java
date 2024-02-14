package com.example.studyswap.graphiccontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Node;

public class MessageBoxGUIController {
    @FXML
    private Label messageText;

    //imposta il testo del messaggio
    public void setMessage(String messageText){
        this.messageText.setText(messageText);
    }

    //gestione della chiusura del popup quando viene premuto il tasto
    public void close(ActionEvent event){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
