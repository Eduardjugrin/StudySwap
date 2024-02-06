package com.example.StudySwap.appcontroller;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.ShowExceptionSupport;
import com.example.StudySwap.exception.DuplicateNoteException;
import com.example.StudySwap.model.Note;

import java.io.IOException;

public class NoteUploadController {
    public void uploadFile(NoteBean noteBean, String sellerEmail) throws DuplicateNoteException {
        Note note = new Note(noteBean.getFileName(), noteBean.getExtension(), noteBean.getContent(), noteBean.getUploaderEmail());

        NoteDAOJDBC.uploadFile(note, sellerEmail);

    }
}
