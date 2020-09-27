package com.wework.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    private By add =By.id("an0");

    public CalPage(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    /*
      添加日程
      */
    public CalPage addCal(String name,String time){

        click(add);
        click(By.id("hxm"));
        sendkeys(taskName,name);
        click(save);
        return this;
    }
    public List<String> getList(String date){
        if(date!=null){
            //todo:选择日期
        }
        int i=0;
       List<String> list= driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());
        for (String m;i<list.size();i++){
            m=list.get(i);
            System.out.println(m);
        }
        List<String> list1=new ArrayList<>();
         for(Object element:driver.findElements(taskList)){
            list1.add(((WebElement) element).getText());
        }
        return list1;
        //driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());

    }
}
