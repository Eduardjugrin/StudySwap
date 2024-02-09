package com.example.StudySwap.graphiccontroller;

import com.example.StudySwap.DAO.NoteDAO;
import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.Main;
import com.example.StudySwap.bean.SellerBean;
import com.example.StudySwap.engineering.Singleton.Session;
import com.example.StudySwap.engineering.observer.Printer;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;
import com.example.StudySwap.model.Seller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SellerHomepageGUIController{

    @FXML
    private Button toSellNotes;

    @FXML
    private GridPane grid;
    @FXML
    private Label welcome;

    private SellerBean sellerBean;
    private static String SELL_NOTES = "/fxml/sellNotesPage.fxml";

    public void initialize() throws IOException, NotFoundException {
        welcome.setText("Welcome in your Homepage "+Session.getCurrentSession().getSellerBean().getFirstName());

        List<Note> sellerNotes = NoteDAOJDBC.getAllNotesBySeller(Session.getCurrentSession().getSellerBean().getEmail());


        // Popola la GridPane con i dati degli appunti
        int row = 1;
        int col = 0;

        for (Note note : sellerNotes) {
            // Creazione dell'elemento elemento visuale per l'appunto
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/fxml/sellerNoteItem.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            SellerNoteItemGUIController sellerNoteItemGUIController = fxmlLoader.getController();
            sellerNoteItemGUIController.setData(note);


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
//        List<Note> sellerNotes = null;
//        sellerNotes = NoteDAOJDBC.getAllNotesBySeller(sellerEmail);
//
//        int column = 1;
//        int row = 1;
//
//        try{
//            for(Note note : sellerNotes){
//
//                if(sellerNotes == null){
//                    ShowExceptionSupport.showException("no notes uploaded yet");
//                }
//                //caricamento dell'elemento della GUI per singola corsa compatibile
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(Main.class.getResource("/fxml/sellerNoteItem.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                SellerNoteItemGUIController sellerNoteItemGUIController = fxmlLoader.getController();
//                sellerNoteItemGUIController.setData(note);
//
//                if(column == 1){
//                    column = 0;
//                    row++;
//                }
//
//                grid.add(anchorPane, column++, row);
//
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
//
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//
//            }
//        }catch(IOException e){
//            Printer.printError(e.getMessage());
//        }
//    }


//    @Override
//    public void initialize(URL location, ResourceBundle resources){
//        List<Note> sellerNotes = null;
//
//        try{
//            sellerNotes = NoteDAOJDBC.getAllNotesBySeller(sellerEmail);
//        }catch(Exception e){
//            Printer.printError("Error retrieving note: " + e.getMessage());
//            ShowExceptionSupport.showException("An error occured!");
//        }
//
//        if(sellerNotes != null && !sellerNotes.isEmpty()){
//            //popolamento della listView con gli appunti ottenuti
//            notesListView.getItems().addAll(sellerNotes);
//            notesListView.setCellFactory(param -> new NoteCell());
//        }else{
//            ShowExceptionSupport.showException("No notes uploaded yet");
//        }
//    }
//
//    private static class NoteCell extends ListCell<Note> {
//        @Override
//        protected void updateItem(Note item, boolean empty){
//            super.updateItem(item, empty);
//
//            if(empty || item == null){
//                setText(null);
//                getGraphic();
//            }else{
//                //todo personalizzazione dell'aspetto della text chip
//                VBox chipLayout = new VBox();
//                chipLayout.getChildren().addAll(
//                        new Label(item.getFileName()),
//                        new Label("Subject" + item.getSubject())
//                );
//                setText(null);
//                setGraphic(chipLayout);
//            }
//
//        }
//    }

    public void logout() throws IOException{
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toSellNotes() throws IOException{
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(SELL_NOTES)));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
