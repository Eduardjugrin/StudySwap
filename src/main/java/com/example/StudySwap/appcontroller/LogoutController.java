package com.example.StudySwap.appcontroller;

import com.example.StudySwap.engineering.Singleton.Session;

public class LogoutController {
    public void logout(){
        Session.closeSession();
    }
}
