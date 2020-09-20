package com.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
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
public class MainPage {
    private static AppiumDriver driver;
    private  WebDriverWait wait;

    public MainPage() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("udid","55510804");
        capabilities.setCapability("deviceName", "mumu");
        capabilities.setCapability("appPackage","com.xueqiu.android");
        capabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        capabilities.setCapability("noReset", "true");

        driver=new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
    }

    public SearchPage toSearch() throws MalformedURLException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        return new SearchPage(driver);
    }
    public void toStock(){
        //
        //
        //

    }
}
