package com.example.StudySwap.engineering;

import com.example.StudySwap.bean.BuyerBean;
import com.example.StudySwap.bean.SellerBean;

//sessione utente che memorizza le info del buyer e del seller
public class Session {

    private static Session sessionInstance = null;
    private BuyerBean buyerBean;
    private SellerBean sellerBean;

    //costruttore private per inizializzare la sessione con un oggetto specifico

    private Session(Object obj){
        if(obj instanceof BuyerBean buyBean){
            this.buyerBean = buyBean;
        }else if(obj instanceof SellerBean sellBean){
            this.sellerBean = sellBean;
        }
    }

    //impostazione istanza della sessione solo se non ne esiste già una attiva
    public static void setSessionInstance(Object obj){
        if(sessionInstance == null){
            sessionInstance = new Session(obj);
        }
    }

    //chiude la sessione corrente
    public static void closeSession(){
        sessionInstance = null;
    }

    //restituise l'istanza corrente della sessione
    public static Session getCurrentSession(){
        return sessionInstance;
    }

    //restituisce l'oggetto BuyerBean associato alla sessione corrente
    public BuyerBean getBuyerBean(){
        return buyerBean;
    }

    //restituisce l'oggetto SellerBean associato alla sessione corrente
    public SellerBean getSellerBean(){
        return sellerBean;
    }
}
