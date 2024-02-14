package com.example.studyswap.graphiccontroller;

import com.example.studyswap.DAO.NoteDAOJDBC;
import com.example.studyswap.Main;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.Singleton.Session;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.model.Note;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class BuyerHomepageGUIController {
    @FXML
    private GridPane grid;
    @FXML
    private Label welcomeLabel;

    private String PURCHASED_NOTES = "/fxml/purchasedNotes.fxml";

    public void initialize() throws IOException, NotFoundException {
        welcomeLabel.setText("Welcome in your Homepage " + Session.getCurrentSession().getBuyerBean().getFirstName());

       List<Note> allNotes = NoteDAOJDBC.getAllNotes();

        // Popola la GridPane con i dati degli appunti
        int row = 1;
        int col = 0;

        for (Note note : allNotes) {
            NoteBean noteBean = new NoteBean(note.getFileID(), note.getFileName(), note.getExtension(), note.getContent(), note.getUploaderEmail(), note.getPrice(), note.getSubject(), note.getAuthor());
            // Creazione dell'elemento elemento visuale per l'appunto
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/fxml/buyerNoteItem.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            BuyerNoteItemGUIController buyerNoteItemGUIController = fxmlLoader.getController();
            buyerNoteItemGUIController.setData(noteBean);
            buyerNoteItemGUIController.setNoteBean(noteBean);

            // Incrementa la riga  per posizionare il prossimo elemento
            if (col == 1) {
                col = 0;
                row++;
            }

            grid.add(anchorPane, col++, row);

            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    public void toBoughtNotes() throws IOException{
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(PURCHASED_NOTES)));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }
}
