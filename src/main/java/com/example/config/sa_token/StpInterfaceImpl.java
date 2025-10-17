package com.example.config.sa_token;


import cn.dev33.satoken.stp.StpInterface;

import com.example.db.dao.UserDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {


    private final UserDao userDao;

    public StpInterfaceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 返回一个用户所拥有的权限集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
//        List<String> list = new ArrayList<>();
        int userId = Integer.parseInt(loginId.toString());
//        List<String> list = userDao.searchUserPermission(userId);
//        Set<String> set = userDao.searchUserPermissions(userId);
//        list.addAll(set);
        return null;
    }


    /**
     * 返回一个用户所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
//        ArrayList<String> list = new ArrayList();
//        int userId = Integer.parseInt(loginId.toString());
//        List<String> list = userDao.searchUserRole(userId);
        return null;
    }
}
