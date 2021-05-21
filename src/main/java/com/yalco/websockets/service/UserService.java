package com.yalco.websockets.service;

import com.yalco.websockets.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<UserModel> users = new ArrayList<>();

    public List<UserModel> getAllUsers(){
        return users;
    }

    public void addUser(String username){
        users.add(new UserModel(username));
    }
}
