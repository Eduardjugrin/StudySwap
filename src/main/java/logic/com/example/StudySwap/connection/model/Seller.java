package logic.com.example.StudySwap.connection.model;

public class Seller extends User {
    private String bankAccountDetails;

    public Seller(String firstName, String lastName, String email, String bankAccountDetails){
        super(firstName, lastName, email);
        this.bankAccountDetails = bankAccountDetails;
    }

    public String getBankAccountDetails(){
        return(this.bankAccountDetails);
    }
}