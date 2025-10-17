package com.example.db.dao;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author lenovo
* @description 针对表【tb_user(用户表)】的数据库操作Mapper
* @createDate 2025-10-17 16:04:32
* @Entity com.example.db.pojo.UserEntity
*/
public interface UserDao {
    public ArrayList<HashMap> getUsers();
}




