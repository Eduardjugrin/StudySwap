package com.example.studyswap.dao;

import com.example.studyswap.connection.ConnectionDB;
import com.example.studyswap.dao.queries.RetrieveQueries;
import com.example.studyswap.engineering.observer.Printer;
import com.example.studyswap.exception.DuplicateNoteException;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.model.Note;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOJDBC extends NoteDAO {
    private static final String FILE_ID = "id";
    private static final String FILE_NAME = "fileName";
    private static final String EXTENSION = "extension";
    private static final String CONTENT = "content";
    private static final String UPLOADER_EMAIL = "uploaderEmail";
    private static final String SUBJECT = "subject";
    private static final String PRICE = "price";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    public static void uploadFile(Note note, String sellerEmail) throws DuplicateNoteException {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();

            if (note == null) {
                throw new IllegalArgumentException("Note object cannot be null");
            }
            if (note.getFileName() == null || note.getExtension() == null || note.getContent() == null) {
                throw new IllegalArgumentException("Note object contain null values");
            }
            if (fileExists(connection, note.getFileName())) {
                throw new DuplicateNoteException();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO files (fileName, extension, content, uploaderEmail, price, subject) VALUES(?, ?, ?, ?, ?, ?)");
            ) {
                preparedStatement.setString(1, note.getFileName());
                preparedStatement.setString(2, note.getExtension());
                preparedStatement.setBinaryStream(3, new ByteArrayInputStream(note.getContent()));
                preparedStatement.setString(4, sellerEmail);
                preparedStatement.setDouble(5, note.getPrice());
                preparedStatement.setString(6, note.getSubject().toLowerCase());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            Printer.printError("Error uploading file: " + e.getMessage());
        }
    }


    public static List<Note> getAllNotes() throws NotFoundException {

        List<Note> noteList = new ArrayList<>();
        Connection connection;
        Note note = null;

        ResultSet resultSet = null;
        try {

            connection = ConnectionDB.getConnection();

            resultSet = RetrieveQueries.retrieveAllNotes(connection);

            if (!resultSet.first()) {
                throw new NotFoundException("no notes found");
            }

            resultSet.first();
            do {
                note = setBuyerFileData(resultSet);
                noteList.add(note);
            } while (resultSet.next());


        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                Printer.printError(e.getMessage());
            }
        }
        return noteList;
    }

    public static List<Note> getAllNotesBySeller(String sellerEmail) {
        Connection connection;
        List<Note> notesByAuthorList = new ArrayList<>();
        Note note = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.getConnection();

            resultSet = RetrieveQueries.retrieveNotesBySeller(connection, sellerEmail);

            if (!resultSet.first()) {
                return notesByAuthorList;
            }

            resultSet.first();
            do {
                note = setSellerFileData(resultSet);
                notesByAuthorList.add(note);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                Printer.printError(e.getMessage());
            }
        }

        return notesByAuthorList;
    }

    public static Note setBuyerFileData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FILE_ID);
        String fileName = resultSet.getString(FILE_NAME);
        String extension = resultSet.getString(EXTENSION);
        byte[] content = resultSet.getBytes(CONTENT);
        double price = resultSet.getDouble(PRICE);
        String subject = resultSet.getString(SUBJECT);
        String author = resultSet.getString(FIRST_NAME) + " " + resultSet.getString(LAST_NAME);

        return new Note(id, fileName, extension, content, price, subject, author);
    }

    public static Note setSellerFileData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(FILE_ID);
        String fileName = resultSet.getString(FILE_NAME);
        String extension = resultSet.getString(EXTENSION);
        byte[] content = resultSet.getBytes(CONTENT);
        String uploaderEmail = resultSet.getString(UPLOADER_EMAIL);
        double price = resultSet.getDouble(PRICE);
        String subject = resultSet.getString(SUBJECT);

        return new Note(id, fileName, extension, content, uploaderEmail, price, subject);
    }

    private static boolean fileExists(Connection connection, String fileName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM files WHERE fileName = ?";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fileName);
            try {
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    Printer.printError(e.getMessage());
                }
            }
        }
        return false;
    }

    public static boolean purchaseNote(String buyerEmail, int fileId) {
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            if(isNotePurchased(buyerEmail, fileId)){
                return true;
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO purchased (buyerEmail, fileId) VALUES (?,?)")) {

                preparedStatement.setString(1, buyerEmail);
                preparedStatement.setInt(2, fileId);

                int rowsInserted = preparedStatement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
            return false;
        }

    }

    public static boolean isNotePurchased(String buyerEmail, int fileId) {
        boolean isPurchased = false;
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM purchased WHERE buyerEmail = ? AND fileId = ? ")) {
                preparedStatement.setString(1, buyerEmail);
                preparedStatement.setInt(2, fileId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    isPurchased = resultSet.next();
                }

            }
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
            return false;
        }
        return isPurchased;
    }




    public List<Note> getPurchasedNotes(String buyerEmail) {
        Connection connection;

        List<Note> purchasedNoteList = new ArrayList<>();
        Note note = null;

        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.getConnection();

            resultSet = RetrieveQueries.retrieveAllPurchasedNotes(connection, buyerEmail);

            if (!resultSet.first()) {
                throw new NotFoundException("no notes found");
            }

            resultSet.first();
            do {
                note = setBuyerFileData(resultSet);
                purchasedNoteList.add(note);
            } while (resultSet.next());

        } catch (SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                Printer.printError(e.getMessage());
            }
        }

        return purchasedNoteList;
    }

}
