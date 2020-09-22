package com.xueqiu.page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: testSelenium->SearchPage
 * @description: 搜索
 * @author: qiuyu
 * @create: 2020-09-11 13:48
 **/
public class SearchPage extends BasePage{

    public SearchPage(AppiumDriver<WebElement> driver) {
        super(driver);
    }

    /*
            根据传入的关键值进行搜索
        */
    public SearchPage search(String keyword){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        sendkeys(By.id("com.xueqiu.android:id/search_input_text"),keyword);
        //driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys(keyword);

        return this;
    }
    /*
    获取查询结果中的list
     */
    public List<String> getSearchList(){
        List<String> list=new ArrayList<>();
       /* for(Object element:driver.findElements(By.id("name"))){
            list.add(((WebElement) element).getText());
        }*/
       //lambda表达式
       driver.findElements(By.id("name")).forEach(element->list.add(element.getText()));
        return list;
    }
    /*
   点击搜索项获取传入的价格
     */
    public double getPrice(){
        click(By.id("com.xueqiu.android:id/code"));
        //driver.findElement(By.id("com.xueqiu.android:id/code")).click();
        return Double.parseDouble(find(By.id("com.xueqiu.android:id/current_price")).getText());

    }
}
