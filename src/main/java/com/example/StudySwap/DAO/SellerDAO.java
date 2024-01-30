package com.example.StudySwap.DAO;

import com.example.StudySwap.Connection.ConnectionDB;
import com.example.StudySwap.DAO.queries.RetrieveQueries;
import com.example.StudySwap.engineering.Printer;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Seller;
import org.w3c.dom.traversal.NodeIterator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerDAO {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String BANK_ACCOUNT_DETAILS = "bankAccountDetails";

    private SellerDAO(){
    }

    public static Seller getSellerByEmail(String email) throws NotFoundException{
        Connection connection;
        Seller seller = null;

        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveSellerByEmail(connection, email);

            if(!resultSet.first()){
                throw new NotFoundException("no seller found with the email: " + email);
            }

            resultSet.first();
            do{
                seller = setSellerData(resultSet);
            }while(resultSet.next());

            resultSet.close();
        }catch(SQLException e){
            Printer.printError(e.getMessage());
        }

        return seller;
    }

    public static Seller setSellerData(ResultSet resultSet) throws SQLException{
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String email = resultSet.getString(EMAIL);
        String bankAccountDetails = resultSet.getString(BANK_ACCOUNT_DETAILS);

        return new Seller(firstName, lastName, email, bankAccountDetails);
    }
}
