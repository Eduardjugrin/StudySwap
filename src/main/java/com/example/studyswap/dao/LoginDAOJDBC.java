package com.example.studyswap.dao;

import com.example.studyswap.connection.ConnectionDB;
import com.example.studyswap.dao.queries.RetrieveQueries;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.exception.UserNotFoundException;
import com.example.studyswap.model.User;
import com.example.studyswap.engineering.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOJDBC implements LoginDAO {
    @Override
    public User checkUser(String email, String password) throws UserNotFoundException {
        Connection connection;
        int role;
        User user = null;

        try{
            //stabiliamo la connessinoe al db
            connection = ConnectionDB.getConnection();

            //esecuzione query per verificare le credenziali
            ResultSet resultSet = RetrieveQueries.checkUser(connection, email, password);

            //l'utente non è stato trovato
            if(!resultSet.isBeforeFirst()){
                Printer.printMessage("user not found with the provided email: " + email);
                throw new UserNotFoundException();
            }

            //recupero prima riga del result set
            resultSet.first();

            //Recuper nome del ruolo e assegnazione valore numerico corrispondente al ruolo
            String nameRole = resultSet.getString(1);
            switch(nameRole){
                case "1" -> role = 1;
                case "2" -> role = 2;
                default -> throw new NotFoundException("No role found");
            }


            //creazione ogetto User
            user = new User(email, role);
        }catch(NotFoundException | SQLException e){
            Printer.printError(e.getMessage());
        }

        return user;
    }
}
