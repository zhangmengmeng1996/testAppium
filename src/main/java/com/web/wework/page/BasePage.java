package com.web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
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
    @Override
    public void sendKeys(HashMap<String,Object> hashMap){
        super.sendKeys(hashMap);
        String key= (String) hashMap.keySet().toArray()[0];
        String value= (String) hashMap.values().toArray()[0];
        By by = null;
        by=By.name(key);
        sendkeys(by,value);
    }
    @Override
    public void click(HashMap<String,Object> hashMap){
        super.click(hashMap);
        String key= (String) hashMap.keySet().toArray()[0];
        String value= (String) hashMap.values().toArray()[0];
        By by = null;
        if(key.toLowerCase().equals("id")){
            by=By.id(value);
        }
        if(key.toLowerCase().equals("linktext".toLowerCase())){
            by=By.linkText(value);
        }
        if(key.toLowerCase().equals("partiallinktext".toLowerCase())){
            by=By.partialLinkText(value);
        }
        if(key.toLowerCase().equals("xpath".toLowerCase())){
            by=By.xpath(value);
        }
        if(key.toLowerCase().equals("cssSelector".toLowerCase())){
            by=By.cssSelector(value);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        click(by);
    }
    @Override
    public void action(HashMap<String,Object> map){
        super.action(map);
        //map层级不一致所以先获取action以后再获取get
        if(map.containsKey("action")){
            String action = map.get("action").toString().toLowerCase();
            if(action.equals("get")) {
                webDriver.get(map.get("url").toString());
                try {
                    FileReader fileReader = new FileReader("cookie.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        //按；获取每一行数据
                        StringTokenizer tokenizer = new StringTokenizer(line, ";");
                        String name = tokenizer.nextToken();
                        String value = tokenizer.nextToken();
                        String domain = tokenizer.nextToken();
                        String path = tokenizer.nextToken();
                        Date expiry = null;
                        String dt = tokenizer.nextToken();
                        //date类型转换为
                        if (!dt.equals("null")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                            //把 string 转换成 date
                            expiry = sdf.parse(dt);
                        }
                        //把 string 转换成 boolean
                        boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());
                        Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                        webDriver.manage().addCookie(cookie);

                    }
                    webDriver.get(map.get("url").toString());
                    //隐式等待
                    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println(" get找不到");
            }
        }
        else {
            System.out.println("action找不到");
        }


    }

}
