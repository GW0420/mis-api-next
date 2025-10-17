package com.example.mis.service;


import com.example.db.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public ArrayList<HashMap> getUsers() {
        ArrayList<HashMap> users = userDao.getUsers();
        return users;
    }
}
