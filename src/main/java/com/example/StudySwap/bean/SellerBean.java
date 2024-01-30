package com.example.StudySwap.bean;

public class SellerBean {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bankAccountDetails;

    public SellerBean(String firstName, String lastName, String email, String bankAccountDetails){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bankAccountDetails = bankAccountDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }
}
