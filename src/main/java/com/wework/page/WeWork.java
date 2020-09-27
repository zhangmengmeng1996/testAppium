package com.wework.page;

import org.openqa.selenium.By;

/**
 * @program: testSelenium->WeWork
 * @description: 初始化启动的app参数
 * @author: qiuyu
 * @create: 2020-09-24 17:00
 **/
public class WeWork extends BasePage{
    public WeWork() {
        //调用父类构造方法
        super("com.tencent.wework",".launch.LaunchSplashActivity");
    }
    /*
    创建日程添加创建日程
     */
    public CalPage cal(){
        click(By.id("an0"));
        return new CalPage(driver);
    }
    /*
    点击跳转入待办页面
     */
    public TaskPage task(){
        click(By.id("hvo"));
        return new TaskPage(driver);
    }
}
