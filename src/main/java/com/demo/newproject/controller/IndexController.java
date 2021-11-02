package com.demo.newproject.controller;

import com.demo.newproject.model.User;
import com.demo.newproject.service.UserService;
import com.demo.newproject.util.jsonUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/index")
@MapperScan("com.demo.newproject.mapper")
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public String Login(String account, String password) {

        return jsonUtil.getJSONString(0, userService.register(account, password));
    }

}
