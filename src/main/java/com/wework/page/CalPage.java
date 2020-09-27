package com.wework.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: testSelenium->CalPage
 * @description: 添加日程
 * @author: qiuyu
 * @create: 2020-09-24 17:27
 **/
public class CalPage extends BasePage{
    //日程输入框
    private final By taskName = By.id("bb3");
    //点击保存
    private final By save = bytext("保存");
    //获取列表
    private final By taskList = By.id("hdg");
    //首页日程入口
    private By add =By.id("hxm");

    public CalPage(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    /*
      添加日程
      */
    public CalPage addCal(String name,String time){


        click(add);
        sendkeys(taskName,name);
        click(save);
        return this;
    }
    /*
    获取日程的list
     */
    public List<String> getCalList(String date){
        /*//不加等待再次返回时没有效果
        wait.until(ExpectedConditions.visibilityOfElementLocated(taskList));
        if(date!=null){
            //todo:选择日期
        }
        int i=0;
       List<String> list= driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());
       //尝试把输入的list给遍历出来
        for (String m;i<list.size();i++){
            m=list.get(i);
            System.out.println(m);
        }
        List<String> list1=new ArrayList<>();
         for(Object element:driver.findElements(taskList)){
            list1.add(((WebElement) element).getText());
        }*/

        return getList(taskList);
        //driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());

    }
    /*
    删除日程
     */
    public CalPage delete(String deleteArea){
       for(Object element:driver.findElements(taskList)){
           System.out.println(((WebElement) element).getText());
           //不能用==号判断因为二者指向的是不同的对象地址不同，用equals判断内容
           if(deleteArea.equals(((WebElement) element).getText())){
               System.out.println("1111");

              ((WebElement) element).click();
                //删除按钮
              click(By.id("bpz"));
              //确认删除
              click(By.id("bjp"));
           }
       }
        return this;
    }
}
