package com.example.StudySwap.appcontroller;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.exception.DuplicateNoteException;
import com.example.StudySwap.model.Note;

public class NoteUploadController {
    public void uploadFile(NoteBean noteBean, String sellerEmail) throws DuplicateNoteException {
        Note note = new Note(noteBean.getFileName(), noteBean.getExtension(), noteBean.getContent(), noteBean.getUploaderEmail(), noteBean.getPrice(), noteBean.getSubject(), noteBean.getAuthor());

        NoteDAOJDBC.uploadFile(note, sellerEmail);

    }
}
