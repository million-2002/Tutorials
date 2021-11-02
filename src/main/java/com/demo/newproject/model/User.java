package com.demo.newproject.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int id;

    private String account;

    private String password;

    private String head_url;

    private String salt;

    private int status;

}
