package com.naughty.userlogin02.utils.httputils;

import com.naughty.userlogin02.model.Case;
import okhttp3.*;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    public static Map<String,Object> get(String url, String params) throws IOException {
        //1.创建okhttp
        OkHttpClient client = new OkHttpClient();
        //2.创建Request
        Request request = new Request.Builder().url(url+"?"+params).get().build();
        //3.使用client发送请求
        try{
            Map<String,Object> map = new HashMap<>();
            Response response = client.newCall(request).execute();
            map.put("code",response.code());
            map.put("result",response.body().string());
            map.put("headers",request.headers());
            return map;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String,Object>  post(String url,String params) throws IOException {
        //1.创建okhttp
        OkHttpClient client = new OkHttpClient();
        //2.创建Requestbody
        MediaType type = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(type,params);
        //3.创建Request
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //3.使用client发送请求
        try{
            Response response = client.newCall(request).execute();
            Map<String,Object> map = new HashMap<>();
            map.put("code",response.code());
            map.put("result",response.body().string());
            map.put("headers",request.headers());
            return map;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object>  posts(String url,String params) throws IOException {
        //1.创建okhttp
        OkHttpClient client = new OkHttpClient();
        //2.创建Requestbody
        MediaType type = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(type,params);
        //3.创建Request
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //3.使用client发送请求
        try{
            Response response = client.newCall(request).execute();
            Map<String,Object> map = new HashMap<>();
            map.put("code",response.code());
            map.put("result",response.body().string());
            map.put("headers",request.headers());
            return map;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String,Object>  call(String method,String url,String params) throws IOException {
        if("get".equalsIgnoreCase(method)){
           return get(url,params);
        }else if("post".equalsIgnoreCase(method)){
           return post(url,params);
        }else {
           return posts(url,params);
        }
    }

  //读取测试用例
    public static Object[][] getdata(List<Case> caselist){
        //计算当前用例条数
        int num = caselist.size();
        System.out.println(num);
        // 定义一个二维数组，把list数据转换成二维数组，给数据提供者使用
        Object[][] obj = new Object[num][1];
        // 数组长度为用例行的长度
        for (int i = 0; i < num; i++) {
            // 第i行第2列保存case对象
            obj[i][0] = caselist.get(i);
        }
        return obj;
    }

    //序列化对象
    public static byte[] obj2byte(Object obj) throws Exception {
        byte[] ret = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(obj);
        out.close();
        ret = baos.toByteArray();
        baos.close();
        return ret;
    }

    //反序列化
    public static Object byte2obj(byte[] bytes) throws Exception {
        Object ret = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ret = in.readObject();
        in.close();
        return ret;
    }
}



