package com.naughty.userlogin02.model;

import lombok.Data;

@Data
public class Case {
    private int id;//用例id
    private String desction; //用例描述
    private Object params; //接口参数
    private Object massert;//预期结果
}
