package com.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @program: testAppium
 * @description: 微信小程序测试
 * @author: mengmeng
 * @create: 2020-10-22 16:54
 **/
//针对微信版本在7.0+，微信有对H5开关做了调整，需要在聊天窗口输入如下：
//
//http://debugmm.qq.com/?forcex5=true
//http://debugx5.qq.com
    //edge://inspect/devices#devices
    //打开webview的测试方法https://www.cnblogs.com/wolf-eye/p/12154491.html
public class WeiXinTest {
    public static AppiumDriver<WebElement> driver;
    static WebDriverWait wait;
    private int index = 0;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //因为小程序的进程名跟包名不一样，需要加上这个参数
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("udid","31b22b68");
        capabilities.setCapability("deviceName", "mumu");
        capabilities.setCapability("appPackage","com.tencent.mm");
        capabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("chromedriverExecutable", "C:/Users/ZhangMengm/Desktop/77/chromedriver.exe");
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");
        //第二步：设置chromeoption传递给chromedriver
        //因为小程序的进程名跟包名不一样，需要加上这个参数
        ChromeOptions chromeOptions = new ChromeOptions();
       chromeOptions.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
       capabilities.setCapability("goog:chromeOptions", chromeOptions);
        //必须得加上，因为默认生成browserName=chrome的设置，需要去掉
        capabilities.setCapability("browserName", "");
        //无需重新启动来回切换小程序
        //通过自己的adb代理修复chromedriver的bug并解决@xweb_devtools_remote的问题
        //capabilities.setCapability("adbPort", "5038");
        //capabilities.setCapability("dontStopAppOnReset", "true");
        //响应速度会变快
      //  capabilities.setCapability("skipLogcatCapture", "true");
        try {
            driver=new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wait=new WebDriverWait(driver,20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void webview_native() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //获取页面高度
        TouchAction touchAction = new TouchAction(driver);
        //手势按下
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //滑动页面
        touchAction.press(PointOption.point((int) (width/2), (int) (height/2)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                .moveTo(PointOption.point((int) (width/2), (int) (height-200))).release().perform();

       // driver.findElement(By.className("android.widget.EditText")).click();

        //driver.findElement(By.className("android.widget.EditText")).sendKeys("雪球");
      //  driver.findElement(By.className("android.widget.Button")).click();
        driver.findElement(By.xpath("//*[@text='雪球']")).click();
        driver.getContextHandles().forEach(context -> {
            System.out.println(context.toString());
        });

        String webview = driver.getContextHandles().stream()
                .filter(context -> context.toString().contains("WEBVIEW"))
                .findFirst().get().toString();
        System.out.println(webview);
        driver.context(webview);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        findTopWindow();
        screenshot();
        driver.findElement(By.cssSelector("[src*=stock_add]")).click();
//        driver.findElement(By.cssSelector("[src=\"/static/img/stock_add@3x.png\"]")).click();

        new WebDriverWait(driver, 10).until((driver) -> driver.getWindowHandles().size() > 2);
        findTopWindow();
        screenshot();
        WebElement input = driver.findElement(By.cssSelector("._input"));
        //html输入不生效
//        input.sendKeys("xiaomi");

        //js输入方法不可行
//        String keyword = "alibaba";
//        String js = String.format("arguments[0].setAttribute('value','%s')", keyword);
//        System.out.println(js);
//        driver.executeScript(js, input);

        //native原生输入可以
        driver.context("NATIVE_APP");
        new Actions(driver).sendKeys("xiaomi").perform();
        screenshot();
        driver.context("WEBVIEW_xweb");
        driver.findElement(By.cssSelector(".stock__item")).click();
        //截图
        findTopWindow();
        screenshot();
    }

    public void screenshot() {
        //截图
        index += 1;
        String path = "/tmp";
        try {
            FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
                    new File(String.format("%s/wx_%s.png", path, index)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findTopWindow() {
        for (String win : driver.getWindowHandles()) {
            if (driver.getTitle().contains(":VISIBLE")) {
                System.out.println(driver.getTitle());
                System.out.println(driver.findElement(By.cssSelector("body")).getAttribute("is"));
            } else {
                driver.switchTo().window(win);
            }
        }
        System.out.println(driver.getPageSource());
    }


    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}
