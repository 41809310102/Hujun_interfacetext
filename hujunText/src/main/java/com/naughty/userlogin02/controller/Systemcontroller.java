package com.naughty.userlogin02.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @desc:接口自动化测试平台非业务前端服务接口
 * @createTime:2022-6-16
 * @Actor:hujunjie
* */
@CrossOrigin(origins ={"http://localhost:8080","http://localhost:63342","http://localhost:8081"})
@RestController
public class Systemcontroller {

    @PostMapping("api/user/login")
    public String login(){
        System.out.println("登录成功");
        return "success";
    }
}
