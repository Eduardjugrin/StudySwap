package com.example.studyswap.DAO;

import com.example.studyswap.Connection.ConnectionDB;
import com.example.studyswap.DAO.queries.RetrieveQueries;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.model.Buyer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


//gestisce l'accesso ai dati dei buyer
public class BuyerDAO {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PAYMENT_METHOD = "paymentMethod";

    private BuyerDAO(){
    }

    public static Buyer getBuyerByEmail(String email) throws NotFoundException {
        Connection connection;
        Buyer buyer = null;

        try{
            //stabilimento della connessione al db
            connection = ConnectionDB.getConnection();

            //esecuzione query per verificare credenziali
            ResultSet resultSet = RetrieveQueries.retrieveBuyerByEmail(connection, email);

            //l'utente non è stato trovato
            if(!resultSet.first()){
                throw new NotFoundException("no buyer found with the email: " + email);
            }

            resultSet.first();
            do{
                buyer = setBuyerData(resultSet);
            }while(resultSet.next());

            resultSet.close();
        }catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return buyer;
    }

    public static Buyer setBuyerData(ResultSet resultSet) throws SQLException{
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String email = resultSet.getString(EMAIL);
        String paymentMethod = resultSet.getString(PAYMENT_METHOD);

        return new Buyer(firstName, lastName, email, paymentMethod);
    }

}
