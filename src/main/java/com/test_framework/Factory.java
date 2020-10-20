package com.test_framework;

/**
 * @program: testSelenium->Factory
 * @description: 工厂方法决定去app还是web
 * @author: qiuyu
 * @create: 2020-09-29 16:43
 **/
public class Factory {
//通常为静态方法
    public static BasePage create(String driverName){
        if(driverName.equals("app")|| driverName.equals("appium")){
            return new com.wework.page.BasePage();
        }
        if(driverName.equals("web")|| driverName.equals("selenium")){
            return new com.web.wework.page.BasePage();
        }
        return null;
    }
}
