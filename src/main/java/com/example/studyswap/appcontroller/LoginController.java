package com.example.studyswap.appcontroller;

import com.example.studyswap.dao.BuyerDAO;
import com.example.studyswap.dao.LoginDAO;
import com.example.studyswap.dao.SellerDAO;
import com.example.studyswap.bean.BuyerBean;
import com.example.studyswap.bean.LoginBean;
import com.example.studyswap.bean.SellerBean;
import com.example.studyswap.engineering.Singleton.Session;
import com.example.studyswap.engineering.factory.LoginDAOFactory;
import com.example.studyswap.exception.NotFoundException;
import com.example.studyswap.exception.UserNotFoundException;
import com.example.studyswap.model.Buyer;
import com.example.studyswap.model.Seller;
import com.example.studyswap.model.User;

public class LoginController {

    //verifica utente con le credenziali fornite dal LoginBean
    public void checkUser(LoginBean loginBean) throws UserNotFoundException {

        //otteniamo l'istanza del DAO per il controllo delle credenziali
        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();

        //controllo dell'utente tramite il dao e otteniamo del relativo ruolo
        User user = loginDAO.checkUser(loginBean.getEmail(), loginBean.getPassword());
        loginBean.setRole(user.getRole());
    }

    //login del buyer dopo il controllo delle credenziali
    public void buyerLogin(LoginBean loginBean) throws NotFoundException{

        //buyer corrisponde al nome utente nel DAO
        Buyer buyer = BuyerDAO.getBuyerByEmail(loginBean.getEmail());

        //creazione BuyerBean per il Buyer autenticato
        BuyerBean buyerBean = new BuyerBean(buyer.getFirstName(), buyer.getLastName(), buyer.getEmail(), buyer.getPaymentMethod());

        //imposta sessione con il BuyerBean autenticato
        Session.setSessionInstance(buyerBean);
    }

    public void sellerLogin(LoginBean loginBean) throws NotFoundException{

        //seller corrisponde al nome utente nel DAO
        Seller seller = SellerDAO.getSellerByEmail(loginBean.getEmail());

        //creazione SellerBean per il Seller autenticato
        SellerBean sellerBean = new SellerBean(seller.getFirstName(), seller.getLastName(), seller.getEmail(), seller.getBankAccountDetails());

        //imposta sessione con il SellerBean autenticato
        Session.setSessionInstance(sellerBean);
    }
}
