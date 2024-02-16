package com.example.studyswap.dao;

import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.exception.UserNotFoundException;
import com.example.studyswap.model.User;
import com.example.studyswap.engineering.observer.Printer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//implementazione del LoginDAO usando un vile csv per l'accesso ai dati degli utenti
public class LoginDAOCSV implements LoginDAO{
    private static final String CSV_FILE_NAME = "src/main/java/res/users.csv";

    private static final int EMAIL = 0;
    private static final int PASSWORD = 1;
    private static final int ROLE = 2;

    //verifica delle credenziali di accesso forniscono a utente registrato
    @Override
    public User checkUser(String email, String password) throws UserNotFoundException {
        int role;
        User user = null;
        File file = new File(CSV_FILE_NAME);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String row;
            String[] data;

            //lettura di ogni riga del file CSV
            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if(data[EMAIL].equals(email) && data[PASSWORD].equals(password)){
                    switch (data[ROLE]){
                        case "buyer" -> role = 1;
                        case "seller" -> role = 2;
                        default -> throw new NotFoundException("No role found");
                    }
                    //se l'autenticazione ha successo crea un oggetto User utilizzando ruolo e username
                    user = new User(email, role);
                }
            }

            if(user == null){
                throw new UserNotFoundException();
            }

        } catch(IOException | NotFoundException e){
            Printer.printError(e.getMessage());
        }

        return user;
    }
}
