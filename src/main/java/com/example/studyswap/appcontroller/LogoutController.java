package com.example.studyswap.appcontroller;

import com.example.studyswap.engineering.singleton.Session;

public class LogoutController {
    public void logout(){
        Session.closeSession();
    }
}
