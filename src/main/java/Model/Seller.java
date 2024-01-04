package Model;

public class Seller extends User {
    private String bankAccountDetails;

    public Seller(String firstName, String lastName, String username, String bankAccountDetails, int id){
        super(firstName, lastName, username, id);
        this.bankAccountDetails = bankAccountDetails;
    }

    public String getBankAccountDetails(){
        return(this.bankAccountDetails);
    }
}