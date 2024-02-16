package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.appcontroller.LogoutController;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.viewcli.BuyerViewCLI;

import java.sql.SQLException;

public class BuyerCLIController implements GraphicCLIController{

    private static final String BUY_NOTES = "1";
    private static final String VIEW_PURCHASED_NOTES = "2";
    private static final String LOGOUT = "3";

    BuyerViewCLI buyerViewCLI;

    @Override
    public void start(){
        this.buyerViewCLI = new BuyerViewCLI(this);
        this.buyerViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException, NotFoundException, SQLException {
        switch(inputLine){
            case BUY_NOTES -> {
                BuyNotesCLIController buyNotesCLIController = new BuyNotesCLIController();
                buyNotesCLIController.start();

                this.start();
            }

            case VIEW_PURCHASED_NOTES -> {
                PurchasedNotesCLIController purchasedNotesCLIController = new PurchasedNotesCLIController();
                purchasedNotesCLIController.start();

                this.start();
            }

            case LOGOUT -> {
                LogoutController logoutController = new LogoutController();
                logoutController.logout();

                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            }
            default -> throw new CommandErrorException();
        }
    }
}
