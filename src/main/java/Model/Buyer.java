package Model;

public class Buyer extends User {
    private String paymentMethod;

    public Buyer(String firstName, String lastName, String username, String paymentMethod, int id){
        super(firstName, lastName, username, id);
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod(){
        return(this.paymentMethod);
    }
}