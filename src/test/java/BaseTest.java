/**
 * @program: testAppium->BaseTest
 * @description: 定位打开首页测试
 * @author: qiuyu
 * @create: 2020-08-29 14:56
 **/
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static AppiumDriver driver;
    public WebDriverWait wait;
    /*

     */
    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("udid","55510804");
        capabilities.setCapability("deviceName", "mumu");
        capabilities.setCapability("appPackage","com.xueqiu.android");
        capabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        capabilities.setCapability("noReset", "true");

        driver=new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);

    }

}
