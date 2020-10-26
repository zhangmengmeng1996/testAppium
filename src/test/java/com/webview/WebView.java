package com.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @program: testAppium
 * @description: webview调试
 * @author: mengmeng
 * @create: 2020-10-20 16:15
 **/
public class WebView {

    public static AppiumDriver<WebElement> driver;
    static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("udid","31b22b68");
        capabilities.setCapability("deviceName", "mumu");
        capabilities.setCapability("appPackage","com.xueqiu.android");
        capabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("chromedriverExecutable", "C:/Users/ZhangMengm/Desktop/chromedriver.exe");

        try {
            driver=new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wait=new WebDriverWait(driver,20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='交易']"));

    }

    @Test
    public void webview_native() {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        driver.findElement(By.xpath("//*[@text='基金']")).click();
    }

    @Test
    //真机打不开webview调试开关
    public void webview_web() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        for (int i = 0; i < 2; i++) {
            //获取切换的上下文
            driver.getContextHandles().forEach(context -> System.out.println(context.toString()));
            Thread.sleep(1000);
        }
        //切换上下文
        driver.context(driver.getContextHandles().toArray()[1].toString());

        driver.getWindowHandles().forEach(window -> {
            System.out.println(window);
            System.out.println(driver.getTitle());
            driver.switchTo().window(window);
            System.out.println(driver.getPageSource());
        });


//        driver.getWindowHandles().stream().filter(win->{
//            driver.switchTo().window(win);
//            return driver.getTitle().contains("实盘交易");
//        });

        Object[] array = driver.getWindowHandles().toArray();
        driver.switchTo().window(array[array.length - 1].toString());

        driver.findElement(By.cssSelector(".trade_home_info_3aI")).click();


    }

    @Test
    public void wxmicroApplication(){

    }

    @Test
    public void sampleTest() {
        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
        el4.click();
        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el5.sendKeys("alibaba");
        MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el6.click();
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}