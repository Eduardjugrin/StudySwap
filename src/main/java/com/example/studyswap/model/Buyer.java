package com.example.studyswap.model;

public class Buyer extends User {
    private String paymentMethod;

    public Buyer(String firstName, String lastName, String email, String paymentMethod){
        super(firstName, lastName, email);
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod(){
        return(this.paymentMethod);
    }
}