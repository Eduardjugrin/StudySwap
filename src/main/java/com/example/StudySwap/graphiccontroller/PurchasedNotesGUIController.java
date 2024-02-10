package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.Main;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.Singleton.Session;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;
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
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class PurchasedNotesGUIController {
    @FXML
    private GridPane grid;

    private String BUYER_HP = "/fxml/buyerHomePage.fxml";

    public void initialize() throws NotFoundException, IOException {

        List<Note> allNotes = NoteDAOJDBC.getPurchasedNotes(Session.getCurrentSession().getBuyerBean().getEmail());

        // Popola la GridPane con i dati degli appunti
        int row = 1;
        int col = 0;

        for (Note note : allNotes) {

            NoteBean noteBean = new NoteBean(note.getFileID(), note.getFileName(), note.getExtension(), note.getContent(), note.getUploaderEmail(), note.getPrice(), note.getSubject(), note.getAuthor());
            // Creazione dell'elemento elemento visuale per l'appunto
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/fxml/purchasedNoteItem.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            PurchasedNoteItemGUIController purchasedNoteItemGUIController = fxmlLoader.getController();
            purchasedNoteItemGUIController.setData(noteBean);
            purchasedNoteItemGUIController.setNoteBean(noteBean);

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

    public void logout() throws IOException{
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(BUYER_HP)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
