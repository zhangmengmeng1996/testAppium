package com.wework.page;

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
        return new CalPage(driver);
    }
}
