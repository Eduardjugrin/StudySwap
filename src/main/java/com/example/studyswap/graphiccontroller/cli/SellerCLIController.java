package com.example.studyswap.graphiccontroller.cli;

import com.example.studyswap.appcontroller.LogoutController;
import com.example.studyswap.exception.CommandErrorException;
import com.example.studyswap.viewcli.SellerViewCLI;

public class SellerCLIController implements GraphicCLIController{
    private static final String SELL_NOTES = "1";
    private static final String VIEW_UPLOADED_NOTES = "2";
    private static final String  LOGOUT = "3";

    SellerViewCLI sellerViewCLI;

    @Override
    public void start(){
        this.sellerViewCLI= new SellerViewCLI(this);
        this.sellerViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException{
        switch (inputLine){
            case SELL_NOTES -> {
                SellNotesCLIController sellNotesCLIController = new SellNotesCLIController();
                sellNotesCLIController.start();

                this.start();
            }

            case VIEW_UPLOADED_NOTES-> {
                UploadedCLIController uploadedCLIController = new UploadedCLIController();
                uploadedCLIController.start();

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
