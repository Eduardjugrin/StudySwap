package com.example.StudySwap.appcontroller;

import com.example.StudySwap.DAO.BuyerDAO;
import com.example.StudySwap.DAO.LoginDAO;
import com.example.StudySwap.DAO.SellerDAO;
import com.example.StudySwap.bean.BuyerBean;
import com.example.StudySwap.bean.LoginBean;
import com.example.StudySwap.bean.SellerBean;
import com.example.StudySwap.engineering.Session;
import com.example.StudySwap.engineering.factory.LoginDAOFactory;
import com.example.StudySwap.exception.NotFoundException;
import com.example.StudySwap.exception.UserNotFoundException;
import com.example.StudySwap.model.Buyer;
import com.example.StudySwap.model.Seller;
import com.example.StudySwap.model.User;

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
