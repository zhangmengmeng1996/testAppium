import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.hamcrest.core.IsEqual;
import org.hamcrest.text.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * @program: testSelenium->SowBallTest
 * @description: 雪球首页搜索
 * @author: qiuyu
 * @create: 2020-08-30 19:02
 **/
public class SowBallTest extends BaseTest {
    @Test
    public void testBall() {
        /*MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_search");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el2.click();
        el2.sendKeys("alibaba");*/

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("alibaba");
        driver.findElement(By.id("com.xueqiu.android:id/code")).click();
        System.out.println(driver.findElement(By.id("com.xueqiu.android:id/current_price")).getText());


    }

    @Test
    public void firstPage() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement we = driver.findElement(By.id("com.xueqiu.android:id/home_search"));
        //是否可见，可见的 打印坐标
        if (we.getAttribute("enabled").equals("true")) {
            System.out.print(we.getLocation());
            we.click();
            WebElement m = driver.findElement(By.id("com.xueqiu.android:id/search_input_text"));
            if (m.getAttribute("displayed").equals("true")) {
                System.out.print("搜索成功");

                m.sendKeys("alibaba");

            } else {
                System.out.print("搜索失败");
            }
        }
    }

    /*
    通过子节点寻找父节点
     */
    @Test
    public void finTwo() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("alibaba");
        driver.findElement(By.id("com.xueqiu.android:id/code")).click();
        System.out.println(driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText());
    }

    /*
    滑动页面
     */
    @Test
    public void swipeTest() {

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
        touchAction.press(PointOption.point((int) (width * 0.5), (int) (height * 0.8))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                .moveTo(PointOption.point((int) (width * 0.5), (int) (height * 0.2))).release().perform();

    }

    /*
    @Test
    public void uiTest(){
        AndroidDriver<MobileElement> driver=(AndroidDriver<MobileElement>) this.driver;
        driver.findElementByAndroidUIAutomator("new Uise");

    }*/
    /*
    显示等待
     */
    @Test

    public void waitTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //等待超时时间，等待时间
        wait = new WebDriverWait(driver, 20, 2000);
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys("alibaba");
        WebElement m=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='BABA']")));

        if(m.getAttribute("enabled").equals("true")){
            driver.findElement(By.xpath("//*[@text='BABA']")).click();
        }

    }


    @ParameterizedTest
    @MethodSource("byNameGetPrice")
    public void hamcrestTest(String name,double price){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.xueqiu.android:id/home_search")).click();
        driver.findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys(name);
        driver.findElement(By.id("com.xueqiu.android:id/code")).click();
        String realprice=driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();
        assertThat("股票",Double.parseDouble(realprice),greaterThanOrEqualTo(price));
    }
    private static Stream<Arguments> byNameGetPrice(){
        return Stream.of(Arguments.of("alibaba",210d),Arguments.of("baidu",500d));
    }
}


