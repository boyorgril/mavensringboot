package com.oldsix.test.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {

    private Long id;
    private String name;
    private SexType sex;
    private Date birthday;
    private Date createTime;
    private String status;

}
