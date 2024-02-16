package com.example.studyswap.appcontroller;

import com.example.studyswap.dao.NoteDAOJDBC;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.exception.DuplicateNoteException;
import com.example.studyswap.model.Note;

public class NoteUploadController {
    public void sellFile(NoteBean noteBean, String sellerEmail) throws DuplicateNoteException {
        Note note = new Note(noteBean.getFileId(), noteBean.getFileName(), noteBean.getExtension(), noteBean.getContent(), noteBean.getPrice(), noteBean.getSubject(), noteBean.getAuthor());

        NoteDAOJDBC.uploadFile(note, sellerEmail);

    }
}
