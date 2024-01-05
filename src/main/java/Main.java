import DAO.LoginDAOCSV;
import exception.UserNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Note;
import model.NoteContainer;
import model.User;

public class Main{

/*    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        Button btn = new Button("Say 'Hello World'");
        btn.setOnAction(e -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/


    static NoteContainer noteContainer = new NoteContainer();
    public static void main(String[] args) {
        // Crea un'istanza di LoginDAOCSV
        LoginDAOCSV loginDAO = new LoginDAOCSV();

        // Utente di test
        String testEmail = "test";
        String testPassword = "testpassword";

        try {
            // Prova ad autenticare l'utente
            User authenticatedUser = loginDAO.checkUser(testEmail, testPassword);

            // Stampa informazioni sull'utente autenticato
            System.out.println("User authenticated successfully");
            System.out.println("Email: " + authenticatedUser.getEmail());
            System.out.println("Role: " + authenticatedUser.getRole());

        } catch (UserNotFoundException e) {
            // Gestisci il caso in cui l'utente non viene trovato
            System.out.println("User not found. Authentication failed.");
        }
    }
}