package com.test_framework;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @program: testSelenium->BasePage
 * @description:自动化领域建模
 * @author: zhangmm3
 * @create: 2020-09-28 10:29
 **/
public class BasePage {
    public void click(HashMap<String,Object> hashMap){
        System.out.println("click");
        System.out.println(hashMap);

    }
    public void find(){

    }
    public void sendKeys(HashMap<String,Object> hashMap){
        System.out.println("sendkeys");
        System.out.println(
                hashMap
        );
    }
    public void getText(){

    }
    public void action(HashMap<String,Object> hashMap){
        System.out.println("action");
        System.out.println(
                hashMap
        );
    }
    public void run(UIAuto uiAuto){
        uiAuto.steps.stream().forEach(m->{
            //yaml配置转到具体的方法调用
            //if(m.keySet().contains("click")){
            //    click((HashMap<String, Object>) m.get("click"));
           // }
            if(m.containsKey("click")){
                HashMap<String,Object> by=(HashMap<String, Object>) m.get("click");
                click(by);
            }
            if(m.containsKey("sendKeys")){
                sendKeys(m);
            }
            if (m.containsKey("action")){
                action(m);
            }
        });
    }
    //ctrl+shift+t生成测试类
    public UIAuto load(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAuto uiauto = null;
        try {
            uiauto = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    UIAuto.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiauto;
    }

}
