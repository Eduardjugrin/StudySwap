package com.example.StudySwap.bean;

import java.time.LocalDate;

public class BuyerBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String paymentMethod;

    public BuyerBean(String firstName, String lastName, String email, String paymentMethod){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.paymentMethod = paymentMethod;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getPaymentMethod(){
        return this.paymentMethod;
    }
}
