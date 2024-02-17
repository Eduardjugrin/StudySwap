package com.example.studyswap.dao;

import com.example.studyswap.connection.ConnectionDB;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDAO {
    private static final String REVIEW_ID = "id";
    private static final String FILE_ID = "fileId";
    private static final String RATING = "rating";
    private static final String COMMENT = "comment";
    private static final String TIME_STAMP = "created_at";

    private ReviewDAO(){

    }
    public static Boolean  addReview(Review review, int fileId){
        Connection connection;
        boolean success = false;

        try{
            connection = ConnectionDB.getConnection();
            if(review.getComment().isEmpty()){
                throw new IllegalArgumentException("Comment field contains null values");
            }else if(review.getRating() == 0){
                throw new IllegalArgumentException("Vote field contains null values ");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reviews (rating, comment, fileId) VALUES (?,?, ?)")){
                preparedStatement.setInt(1, review.getRating());
                preparedStatement.setString(2, review.getComment());
                preparedStatement.setInt(3, fileId);

                int rowsAffected = preparedStatement.executeUpdate();
                success = rowsAffected > 0;
            }

        }catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return success;
    }
}
