package com.example.studyswap.appcontroller;

import com.example.studyswap.dao.NoteDAOJDBC;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.Singleton.Session;

import java.sql.SQLException;

public class PurchaseController {

    public static Boolean buyNote(NoteBean noteBean) throws SQLException {
        return NoteDAOJDBC.purchaseNote(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId());
    }
}
