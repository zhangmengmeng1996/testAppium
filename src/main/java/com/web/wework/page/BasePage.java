package com.web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @program: testSelenium->BasePage
 * @description: 父类数据
 * @author: qiuyu
 * @create: 2020-08-19 13:11
 **/
public class BasePage extends com.test_framework.BasePage{
    //转换成非静态变量，防止存在多线程并发问题
    public RemoteWebDriver webDriver;
    WebDriverWait wait;
//mainpage调用
    public BasePage() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wait=new WebDriverWait(webDriver,20);
    }

    public BasePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        wait=new WebDriverWait(webDriver,20);
    }

    public void quit() {
        webDriver.quit();
    }
    /*
    点击效果的封装
     */
    public void click(By by){
        //显示等待数据展示

        wait.until(ExpectedConditions.elementToBeClickable(by));
        webDriver.findElement(by).click();


    }
    /*
    输入相关信息
     */
    public void sendkeys(By by,String content){
        //增强代码健壮性，判断某个元素是否可见（代表元素非隐藏，元素的宽和高都不等于0）

       wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        webDriver.findElement(by).sendKeys(content);
    }
    /*
    上传文件封装
     */
    public void upload(By by,String path){
        //判断某个元素是否被加到了dom树里，并不代表该元素一定可见；
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        webDriver.findElement(by).sendKeys(path);
    }

}
