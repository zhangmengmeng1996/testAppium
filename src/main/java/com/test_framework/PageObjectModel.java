package com.test_framework;

import java.util.HashMap;
import java.util.List;

/**
 * @program: testAppium
 * @description: 解析每一个页面
 * @author: mengmeng
 * @create: 2020-10-16 16:06
 **/
public class PageObjectModel {
    /*
    页面的名字
     */
    public String name;
    public HashMap<String,List<HashMap<String,Object>>> methods;
}
