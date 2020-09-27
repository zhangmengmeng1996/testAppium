package com.wework.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @program: testSelenium->TaskPage
 * @description: 待办
 * @author: qiuyu
 * @create: 2020-09-27 16:39
 **/
public class TaskPage extends BasePage{
    //添加点半
    private final By add=By.id("hxm");
    //待办文本
    private final By taskArea=By.id("bb3");
    //待办保存按钮
    private final By save=By.id("hwn");
    //待办列表
    private final  By taskList=By.id("hv3");
    public  TaskPage(AppiumDriver<WebElement> driver){
        super(driver);
        }
        /*
        添加待办
         */
     public TaskPage addTask(String task){
        click(add);
        sendkeys(taskArea,task);
        click(save);
        return this;
     }
     /*
     获取待办列表
      */
     public List<String> getTaskList(){
         return getList(taskList);
     }
}
