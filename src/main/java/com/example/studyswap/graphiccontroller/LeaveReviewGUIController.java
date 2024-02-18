package com.example.studyswap.graphiccontroller;

import com.example.studyswap.Main;
import com.example.studyswap.appcontroller.PurchasedController;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.bean.ReviewBean;
import com.example.studyswap.engineering.Printer;
import com.example.studyswap.engineering.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class LeaveReviewGUIController {

    @FXML
    Button submitReview;

    @FXML
    private TextField review;

    @FXML
    private Spinner<Integer> voteSpinner;

    private ReviewBean reviewBean;
    private NoteBean noteBean;

    private static final String PURCHASED_HP = "/fxml/purchasedNotes.fxml";

    public void initialize() {
        voteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5));
    }


    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(PURCHASED_HP)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void setNoteBean(NoteBean noteBean){
        this.noteBean = noteBean;
        Printer.printMessage(noteBean.getFileName());
    }


    public void submitReview(){
        reviewBean = new ReviewBean(review.getText(), voteSpinner.getValue());
        PurchasedController purchasedController = new PurchasedController();
        purchasedController.setNoteBean(noteBean);

        boolean success = purchasedController.leaveReview(reviewBean);

        if(success){
            ShowExceptionSupport.showException("Review uploaded successfully");
        }else {
            ShowExceptionSupport.showException("Review not uploaded successfully");
        }
    }
}
