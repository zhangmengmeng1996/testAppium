package com.web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * @program: testSelenium->MainPage
 * @description: 首页
 * @author: qiuyu
 * @create: 2020-08-11 18:56
 **/
public class MainPage extends BasePage{


    /*
    完成浏览器的调用
     */
    public MainPage() {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        webDriver.get("https://work.weixin.qq.com/wework_admin/frame");
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
            webDriver.get("https://work.weixin.qq.com/wework_admin/frame");
            //隐式等待
            webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ContactPage toContact() {
        //定位到通讯录
        click(By.xpath("//a[@id='menu_contacts']"));
        //webDriver.findElement(By.xpath("//a[@id='menu_contacts']")).click();
        return new ContactPage(webDriver);
    }


}
