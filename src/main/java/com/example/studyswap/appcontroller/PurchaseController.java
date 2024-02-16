package com.example.studyswap.appcontroller;

import com.example.studyswap.dao.NoteDAOJDBC;
import com.example.studyswap.bean.NoteBean;
import com.example.studyswap.engineering.singleton.Session;

public class PurchaseController {

    private PurchaseController() {
    }

    public static Boolean buyNote(NoteBean noteBean){
        return NoteDAOJDBC.purchaseNote(Session.getCurrentSession().getBuyerBean().getEmail(), noteBean.getFileId());
    }
}
