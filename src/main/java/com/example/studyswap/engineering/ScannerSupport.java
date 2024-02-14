package com.example.studyswap.engineering;

import java.util.Scanner;

public class ScannerSupport {
    private ScannerSupport(){
    }

    public static void waitEnter(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("")){
            input = scanner.nextLine();
        }
    }
}
