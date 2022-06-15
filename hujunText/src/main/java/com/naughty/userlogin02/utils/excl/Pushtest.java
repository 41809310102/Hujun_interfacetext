package com.naughty.userlogin02.utils.excl;


import com.naughty.userlogin02.model.Case;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

//将数据封装进list中;
public class Pushtest implements Serializable {

    public List<Case> uploadExcel(MultipartFile file, InputStream inputStream) throws Exception {
        System.out.println(file.getOriginalFilename());
        if (file.isEmpty()) {
            return null;
        }
        ImportService importService = new ImportService();
        List<List<Object>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();
        List<Case> text  = addSubject(list);
        return text;
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
        System.out.println(ret);
        return ret;
    }
    //导入成绩
    public List<Case>  addSubject(List<List<Object>> list ) throws Exception {
        List<Case> textList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            //开始创建对象，存储在数据库中
            //System.out.println(lo);
            Case sub = new Case();
            String id = lo.get(0).toString().substring(0,lo.get(0).toString().length()-2);
            sub.setId(Integer.parseInt(id));
            sub.setDesction(lo.get(1).toString());
            sub.setParams(lo.get(2).toString());
            sub.setMassert(lo.get(3).toString());
            textList.add(sub);
        }
        //System.out.println(textList.toString());
        return textList;
    }


}
