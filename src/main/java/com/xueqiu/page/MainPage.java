package com.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @program: testSelenium->MainPage
 * @description: 基础页面调用逻辑
 * @author: qiuyu
 * @create: 2020-09-11 13:47
 **/
public class MainPage extends BasePage {




    public SearchPage toSearch() throws MalformedURLException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        click(By.id("com.xueqiu.android:id/home_search"));
        //driver.findElement(By.id("com.xueqiu.androidit brance :id/home_search")).click();
        return new SearchPage(driver);
    }
    public void toStock(){
        //
        System.out.println("111");
        //List<WebElement> StockList = this.driver.findElements(By.id("com.xueqiu.android:id/stockName"));
        //        List<String> nameList;
        //        if (StockList.size()>0){
        //            nameList = StockList.stream().map(item -> item.getText()).collect(Collectors.toList());
        //        }else {
        //            nameList = Arrays.asList("no such elements");


    }
}
//        }