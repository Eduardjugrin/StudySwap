package com.example.StudySwap;

import com.example.StudySwap.DAO.LoginDAO;
import com.example.StudySwap.DAO.LoginDAOJDBC;
import com.example.StudySwap.appcontroller.LoginController;
import com.example.StudySwap.bean.BuyerBean;
import com.example.StudySwap.bean.LoginBean;
import com.example.StudySwap.bean.SellerBean;
import com.example.StudySwap.engineering.Printer;
import com.example.StudySwap.engineering.Session;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.exception.UserNotFoundException;
import com.example.StudySwap.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//public class Main {
public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/values/style.css").toExternalForm());
        setStage(stage);

        //configurazione finestra GUI
        stage.setTitle("StudySwap");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();
    }

    public static void setStage(Stage stage){
        Main.stage = stage;
    }

    public static Stage getStage(){
        return stage;
    }

}

    //test login con csv
//    public static void main(String[] args) {
//        // Crea un'istanza di LoginDAOCSV
//        LoginDAOCSV loginDAOCSV = new LoginDAOCSV();
//
//        // Utente di test
//        String testEmail = "test";
//        String testPassword = "testpassword";
//
//        try {
//            // Prova ad autenticare l'utente
//            User authenticatedUser = loginDAOCSV.checkUser(testEmail, testPassword);
//
//            // Stampa informazioni sull'utente autenticato
//            System.out.println("User authenticated successfully");
//            System.out.println("Email: " + authenticatedUser.getEmail());
//            System.out.println("Role: " + authenticatedUser.getRole());
//
//        } catch (UserNotFoundException e) {
//            // Gestisci il caso in cui l'utente non viene trovato
//            System.out.println("User not found. Authentication failed.");
//        }
//
//        System.out.println("-*-*-*-*-*-*-*");
//
//        //test notes con csv
//
//        NoteDAOCSV noteDAOCSV = new NoteDAOCSV();
//
//        List<Note> allNotes = noteDAOCSV.getAllNotes();
//
//        if(allNotes.isEmpty()){
//            System.out.println("Nessun appunto trovato");
//        }else{
//            System.out.println("Elenco appunti:");
//            for(Note note: allNotes){
//                System.out.println("Titolo:" + note.getTitle());
//                System.out.println("DataPath:" + note.getPath());
//                System.out.println("Autore: " + note.getAuthor());
//                System.out.println("Prezzo: €" + note.getPrice());
//                System.out.println("-----");
//            }
//        }
//        System.out.println("-*-*-*-*-*-*-*");
//
//        //test notes per autore con csv
//
//        String AUTHOR = "Simone Niro";
//        System.out.println("Libri di: " + AUTHOR);
//        NoteDAOCSV noteDAO = new NoteDAOCSV();
//        try {
//            // Chiamata al metodo getNotesByAuthor per ottenere le note di un autore specifico
//            List<Note> notes = noteDAO.getNotesByAuthor(AUTHOR);
//
//            // Stampa delle note ottenute
//            for (Note note : notes) {
//                System.out.println("Title: " + note.getTitle());
//             /*   System.out.println("Path: " + note.getPath());
//                System.out.println("Author: " + note.getAuthor());
//                System.out.println("Price: " + note.getPrice());
//                System.out.println("---------------");*/
//            }
//        } catch (AuthorNotFoundException e) {
//            // Gestione dell'eccezione se le note non sono trovate
//            System.out.println("Author not found");
//        }
//
//
//    public static void main(String[] args) throws Exception{
//        LoginBean loginBean = new LoginBean("eduard@sample.com", "test456");
//        LoginBean loginBean2 = new LoginBean("simone@sample.com", "test123");
//
//        LoginController loginController = new LoginController();
//        System.out.println("buyer:");
//        try{
//            loginController.checkUser(loginBean);
//
//            loginController.buyerLogin(loginBean);
//
//            Session session = Session.getCurrentSession();
//            if(session != null){
//                System.out.println("    buyer logged in sucesfully:");
//                BuyerBean buyerBean = session.getBuyerBean();
//                System.out.println("    first name:" + buyerBean.getFirstName());
//                System.out.println("    last name:" + buyerBean.getLastName());
//                System.out.println("    last email:" + buyerBean.getEmail());
//                System.out.println("    payment method:" + buyerBean.getPaymentMethod());
//                session.closeSession();
//            }else{
//                System.out.println("buer login failed");
//            }
//        }catch(UserNotFoundException | NotFoundException e){
//            e.printStackTrace();
//        }
//
//        System.out.println("seller:");
//        try{
//            loginController.checkUser(loginBean2);
//
//            loginController.sellerLogin(loginBean2);
//
//            Session session = Session.getCurrentSession();
//            if(session != null){
//                System.out.println("    seller logged in sucesfully:");
//                SellerBean sellerBean = session.getSellerBean();
//                System.out.println("    first name:" + sellerBean.getFirstName());
//                System.out.println("    last name:" + sellerBean.getLastName());
//                System.out.println("    last email:" + sellerBean.getEmail());
//                System.out.println("    payment method:" + sellerBean.getBankAccountDetails());
//            }else{
//                System.out.println("buyer login failed");
//            }
//        }catch(UserNotFoundException | NotFoundException e){
//            e.printStackTrace();
//        }
//    }
//}
