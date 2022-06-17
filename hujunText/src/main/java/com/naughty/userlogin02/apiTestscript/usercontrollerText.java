package com.naughty.userlogin02.apiTestscript;

import com.naughty.userlogin02.model.Case;
import com.naughty.userlogin02.utils.Assert.Assertion;
import com.naughty.userlogin02.utils.excl.Pushtest;
import com.naughty.userlogin02.utils.httputils.Constants;
import com.naughty.userlogin02.utils.httputils.HttpUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @desc  用户登录接口测试类
 * @createtime 2022-6-15
 * */
@Listeners(com.naughty.userlogin02.utils.Listener.AssertListener.class)
public class usercontrollerText {

    private Logger log = LoggerFactory.getLogger("usercontrollerText");
   @Test(dataProvider = "datas")
   public void testuserlogin(Case cases) throws Exception {
       String url = Constants.BASE_URL+"api/temp/add";
       String method = "posts";
       Map<String,Object> map = HttpUtils.call(method,url, (String) cases.getParams());
       String msg = (String) map.get("result");
       log.info("接口 api/temp/add响应数据{}",msg);
       Boolean res = msg.contains(cases.getMassert().toString());
       Assertion.verifyTrue(res);
   }


   @DataProvider(name="datas")
    public Object[][] datas() throws FileNotFoundException {
       //读取指定类名的xlsx文件中的测试用例
       String path = "F:\\Github\\Hujun_interfacetext\\caseXlsx\\"+usercontrollerText.class.getSimpleName()+".xlsx";
       System.out.println("正在获取测试用例文件的路径------>"+path);
       File f = new File(path);
       InputStream in = new FileInputStream(f);
       List<Case> list = new LinkedList<>();
       try {
           //开始解析测试用例
           Pushtest pushtest = new Pushtest();
           list = pushtest.uploadExcel(getMultipartFile(f),in);
       } catch (Exception e) {
           e.printStackTrace();
       }

       Object[][] datas = HttpUtils.getdata(list);
       return datas;
   }


    private MultipartFile getMultipartFile(File file){
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(),file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }

}
