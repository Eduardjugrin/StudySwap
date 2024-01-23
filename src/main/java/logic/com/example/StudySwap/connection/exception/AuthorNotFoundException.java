package logic.com.example.StudySwap.connection.exception;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(){
        super("Author not found");
    }
}
