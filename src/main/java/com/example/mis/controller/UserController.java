package com.example.mis.controller;


import com.example.common.result.R;
import com.example.mis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/user/getusers")
    public R getUsers() {
        ArrayList<HashMap> users = userService.getUsers();
        return R.success(users);
    }
}
