package com.web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * @program: testSelenium->ContactPage
 * @description: 联系人页面
 * @author: qiuyu
 * @create: 2020-08-12 14:14
 **/
public class ContactPage extends BasePage{
    By addMember=By.linkText("添加成员");
    By username=By.name("username");
    By delete=By.linkText("删除");

    public ContactPage(RemoteWebDriver webDriver) {
        super(webDriver);
    }


    public ContactPage addMember(String username, String acctid, String mobile){
        //显示等待10

        //new WebDriverWait(MainPage.webDriver,10)
              //  .until(ExpectedConditions.visibilityOfElementLocated(addMember));
        //new WebDriverWait(MainPage.webDriver, 10)
         //       .until(ExpectedConditions.elementToBeClickable(addMember));
        while(webDriver.findElements(this.username).size()==0){
        click(addMember);
        }
        sendkeys(By.name("username"),username);
        sendkeys(By.name("acctid"),acctid);
        sendkeys(By.name("mobile"),mobile);
      // webDriver.findElement(By.name("username")).sendKeys(username);
      // webDriver.findElement(By.name("acctid")).sendKeys(acctid);
        // webDriver.findElement(By.name("mobile")).sendKeys(mobile);
       click(By.cssSelector(".js_btn_save"));
       //webDriver.findElement(By.cssSelector(".js_btn_save")).click();

        return this;
    }
    /*
    搜索到通讯录的信息
     */
    public ContactPage search(String keyword ){
        sendkeys(By.id("memberSearchInput"),keyword);
        //webDriver.findElement(By.id("memberSearchInput")).sendKeys(keyword);

        //显示等待直到删除按钮出现,父类click方法
        wait.until(ExpectedConditions.elementToBeClickable(delete));
        return this;
    }
    /*
    删除
     */
    public ContactPage delete(){
        //wait.until(ExpectedConditions.elementToBeClickable(delete));
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
       //webDriver.findElement(delete).click();
       //webDriver.findElement(By.linkText("确认")).click();
       //webDriver.findElement(By.id("clearMemberSearchInput")).click();
        return this;
    }
    /*
    通讯录导入
     */
    public ContactPage importFromFile(String path){
        //todo:


        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
        upload(By.name("file"),path);
        //webDriver.findElement(By.name("file")).sendKeys("C:\\Users\\Administrator\\.ssh\\workspace\\testSelenium\\src\\main\\resources\\通讯录批量导入.xlsx");
        //sendkeys(By.name("file"), "C:\\Users\\Administrator\\.ssh\\workspace\\testSelenium\\src\\main\\resources\\通讯录批量导入.xlsx");
        click(By.linkText("确认导入"));
        click(By.linkText("前往查看"));
        return this;
    }
    /*
    获取用户信息
     */
    public String getUserName(){
        return webDriver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }
}
