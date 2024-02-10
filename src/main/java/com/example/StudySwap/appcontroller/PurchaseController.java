package com.example.StudySwap.appcontroller;

import com.example.StudySwap.DAO.NoteDAOJDBC;
import com.example.StudySwap.bean.NoteBean;
import com.example.StudySwap.engineering.Singleton.Session;
import com.example.StudySwap.exception.DuplicateNoteException;
import com.example.StudySwap.model.Note;

import java.sql.SQLException;

public class PurchaseController {

    public static Boolean buyNote(NoteBean noteBean) throws SQLException {
        return NoteDAOJDBC.purchaseNote(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId());
    }
}
