package com.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @program: testSelenium->BasePage
 * @description: 基础类
 * @author: qiuyu
 * @create: 2020-09-20 16:19
 **/
//adb logcat
public class BasePage {
    public  AppiumDriver<WebElement> driver;
    WebDriverWait wait;

    public BasePage() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("udid","55510804");
        capabilities.setCapability("deviceName", "mumu");
        capabilities.setCapability("appPackage","com.xueqiu.android");
        capabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        capabilities.setCapability("noReset", "true");

        try {
            driver=new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wait=new WebDriverWait(driver,20);
    }

    public BasePage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver,20);
    }
    public void quit() {
        driver.quit();
    }
    /*
    点击效果的封装
     */
    public void click(By by){
        //显示等待数据展示

        //wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();


    }
    public void sendkeys(By by,String content){
        //增强代码健壮性，判断某个元素是否可见（代表元素非隐藏，元素的宽和高都不等于0）

        //wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }
    public WebElement find(By by){
        return driver.findElement(by);
    }
}
