package com.naughty.userlogin02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.naughty.userlogin02.dao")
@SpringBootApplication
public class TextstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextstartApplication.class, args);
        System.out.println("\"(♥◠‿◠)ﾉﾞ     接口自动化项目启动成功     ლ(´ڡ`ლ)ﾞ  \n"+
                "ლ♥-.     .-♥-.        .-♥.        .-♥. +\n"+
                "| ♥ |     | ♥ |        | ♥|        | ♥|+\n"+
                "| ♥ |     | ♥ |        | ♥|        | ♥|+\n"+
                "| ♥ !_____! ♥ | .-*.   | ♥| .—*.   | ♥|+\n"+
                "| ♥ (__♥__) ♥ | |♥ |   | ♥| |♥ !   | ♥!+\n"+
                "| ♥ !     ! ♥ | |♥ |   | ♥| |♥ |   | ♥|+\n"+
                "| ♥ |     | ♥ | |♥ |^V^! ♥| |♥ |^V^! ♥!+\n"+
                "!_♥_!     !_♥_! \\♥_♥_♥_♥_/  \\♥_♥_♥_♥_/");
    }

}
