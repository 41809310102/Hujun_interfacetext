package com.naughty.userlogin02.controller;

import com.naughty.userlogin02.apiTestscript.usercontrollerText;
import com.naughty.userlogin02.model.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.testng.TestNG;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins ={"http://localhost:8080","http://localhost:63342","http://localhost:8081"})
public class Testngcontroller {
    @PostMapping("api/doTest/testng")
    public String  doTest() {
        TestNG testNG = new TestNG();
        Class[] classes = {usercontrollerText.class};
        testNG.setTestClasses(classes);
        testNG.run();
        return "执行成功";
    }


    // 将 yml 中的自定义配置注入到这里
    @Value("${gorit.file.root.path}")
    private String filePath;
    private Logger log = LoggerFactory.getLogger("uploadcase");
    @PostMapping(value = "api/upCase/uploadcase")
    @BeforeTest
    public String uploadfile(@RequestParam("file") MultipartFile file) throws Exception{

        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        String newFileName = fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);

        InputStream inputStream = file.getInputStream();
        List<Case> list = new LinkedList<>();
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            System.out.println("测试用例导入成功！");
            //开始解析，存储在数据库中
            inputStream.close();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return "测试导入成功";
    }

}
