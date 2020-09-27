package com.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: testSelenium->BasePage
 * @description: 基础类
 * @author: qiuyu
 * @create: 2020-09-20 16:19
 **/
//adb logcat
public class BasePage {
    private int timeout=20;
    public  AppiumDriver<WebElement> driver;
    WebDriverWait wait;
    String appPackage;
    String appActivity;

    public BasePage(String appPackage, String appActivity) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        //调用启动app
        startApp(this.appPackage,this.appActivity);
    }

    public BasePage(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver,timeout);
    }
    /*
    启动appium
     */
    public void startApp(String appPackage,String appActivity){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("udid","31b22b68");
        capabilities.setCapability("deviceName", "mumu");
        capabilities.setCapability("appPackage",appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("noReset", "true");
        //不每次杀掉进行重启
        capabilities.setCapability("dontStopAppOnreset","true");

        try {
            driver=new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wait=new WebDriverWait(driver,timeout);

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).click();


    }
    /*
    根据xpath点击
     */
    public void click(String text){
        find(text).click();
    }
    /*
    By.xpath某个元素
     */
    public By bytext(String text){
        return By.xpath("//*[@text='"+ text +"']");
    }
    /*
    输入信息
     */
    public void sendkeys(By by,String content){
        //增强代码健壮性，判断某个元素是否可见（代表元素非隐藏，元素的宽和高都不等于0）

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }
    /*
    查找by元素对应 的
     */
    public WebElement find(By by){
        return driver.findElement(by);
    }
    /*
    根据xpath查找某个元素
     */
    public WebElement find(String text){
        return driver.findElement(bytext(text));
    }
    /*
    获取某个元素的list的数据
     */
    public List<String> getList(By by){
        //不加等待再次返回时没有效果
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        List<String> list1=new ArrayList<>();
        for(Object element:driver.findElements(by)){
            System.out.println(((WebElement) element).getText());
            list1.add(((WebElement) element).getText());
        }
        return list1;
    }

}
