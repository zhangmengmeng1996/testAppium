package com.wework.page;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @program: testSelenium->calPageTest
 * @description: 微信添加日程测试
 * @author: qiuyu
 * @create: 2020-09-24 18:06
 **/
public class calPageTest {
    private static WeWork weWork;
    @BeforeAll
    static void beforeAll(){
        weWork=new WeWork();
    }
    @Test
    public void addCalTest(){
       assertTrue( weWork.cal().addCal("上班打卡",null).getList(null).contains("上班打卡"));
    }
}
