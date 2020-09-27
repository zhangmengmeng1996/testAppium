package com.wework.page;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
    /*
    添加日程

     */
    @Test
    public void addCalTest(){
       assertTrue(
               weWork.cal()
                       .addCal("上班打卡",null)
                       .getCalList(null).contains("上班打卡")
       );
    }
    /*
    删除日程
     */
    @Test
    public void deleteTest(){
        assertFalse(
                weWork.cal()
                        .delete("上班打卡")
                        .getCalList(null).contains("上班打卡")
        );
    }
    /*
    添加待办
     */
    @Test
    public void addTaskTest(){
      assertTrue(
              weWork.task()
                      .addTask("明天去图书馆")
                      .getTaskList().contains("明天去图书馆")
      );
    }
}
