package org.carrental.service;

import org.carrental.dao.UserDAO;
import org.carrental.domain.User;

import javax.swing.*;

public class AuthenticationService {

    private final UserDAO userdao =new UserDAO();
    public Boolean checkLogin(String username , String password){
        User user = userdao.getUserByUsernameAndPassword(username, password);
            if(user != null){
                return Boolean.TRUE;
            }
            else{
                return Boolean.FALSE;
            }
    }
}

