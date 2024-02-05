package com.example.StudySwap.DAO;

import com.example.StudySwap.Connection.ConnectionDB;
import com.example.StudySwap.DAO.queries.RetrieveQueries;
import com.example.StudySwap.engineering.Printer;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.model.Note;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOJDBC implements NoteDAO{
    private static final String FILE_NAME = "fileName";
    private static final String EXTENSION = "extension";
    private static final String CONTENT = "content";

    private static final String UPLOADER_EMAIL = "uploaderEmail";

    private NoteDAOJDBC(){

    }

    public void uploadFIle(Note note){
        Connection connection;

        try{
            if(note == null){
                throw new IllegalArgumentException("Note object cannot be null");
            }
            if(note.getFileName() == null || note.getExtension() == null || note.getContent() == null){
                throw new IllegalArgumentException("Note object contain null values");
            }
            connection = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO files (fileName, extension, content) VALUES(?, ?, ?)");
                preparedStatement.setString(1, note.getFileName());
                preparedStatement.setString(2, note.getExtension());
                preparedStatement.setBinaryStream(3, new ByteArrayInputStream(note.getContent()));

                preparedStatement.executeUpdate();
            }catch(SQLException e){
            Printer.printError("Error uploading file: " + e.getMessage());
        }
    }

    @Override
    public List<Note> getAllNotes() throws NotFoundException{

        List<Note> noteList = new ArrayList<>();
        Connection connection;
        Note note = null;

        try{

            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveAllNotes(connection);

            if(!resultSet.first()){
                throw new NotFoundException("no notes found");
            }

            resultSet.first();
            do{
                note = setFileData(resultSet);
            }while(resultSet.next());

            noteList.add(note);

        }catch(SQLException e){
            Printer.printError(e.getMessage());
        }
        return noteList;
    }

    public static Note setFileData(ResultSet resultSet) throws SQLException{
        String fileName = resultSet.getString(FILE_NAME);
        String extension = resultSet.getString(EXTENSION);
        byte[] content = resultSet.getBytes(CONTENT);
        String uploaderEmail = resultSet.getString(UPLOADER_EMAIL);

        return new Note(fileName, extension, content, uploaderEmail);
    }
}
