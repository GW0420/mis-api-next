package com.example.db.pojo;

import java.util.Date;
import lombok.Data;

/**
 * @TableName tb_user
 */
@Data
public class UserEntity {
    private Integer id;

    private String username;

    private String password;

    private String openId;

    private String photo;

    private String name;

    private Object sex;

    private String tel;

    private String email;

    private Date hiredate;

    private Object role;

    private Integer root;

    private Integer deptId;

    private Integer status;

    private Date createTime;
}