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
public class SearchPage {
    private AppiumDriver<WebElement> driver;
    public SearchPage(AppiumDriver driver){
      this.driver=driver;
    }
    /*
        根据传入的关键值进行搜索
    */
    public SearchPage search(String keyword){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys(keyword);

        return this;
    }
    /*
    获取查询结果中的list
     */
    public List<String> getSearchList(){
        List<String> list=new ArrayList<String>();
        for(Object element:driver.findElements(By.id("name"))){
            list.add(((WebElement) element).getText());
        }

        return list;
    }
    /*
   点击搜索项获取传入的价格
     */
    public double getPrice(){
        driver.findElement(By.id("com.xueqiu.android:id/code")).click();
        return Double.parseDouble(driver.findElement(By.id("com.xueqiu.android:id/current_price")).getText());

    }
}
