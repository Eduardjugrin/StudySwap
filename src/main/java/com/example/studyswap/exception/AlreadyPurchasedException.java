package com.example.studyswap.exception;

public class AlreadyPurchasedException extends Exception{

        public AlreadyPurchasedException(){
            super("You have already purchased this file");
        }
}
