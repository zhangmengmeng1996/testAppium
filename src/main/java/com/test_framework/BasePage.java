package com.test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: testSelenium->BasePage
 * @description:自动化领域建模
 * @author: zhangmm3
 * @create: 2020-09-28 10:29
 **/
public class BasePage {
    List<PageObjectModel> pages=new ArrayList<>();
    public void click(HashMap<String,Object> hashMap){
        System.out.println("click");
        System.out.println(hashMap);

    }
    public void find(){

    }
    /*
    sendkeys打印需要输入的信息
     */
    public void sendKeys(HashMap<String,Object> hashMap){
        System.out.println("sendkeys");
        System.out.println(
                hashMap
        );
    }
    public void getText(){

    }
    /*
    打印出action的相关信息
     */
    public void action(HashMap<String,Object> hashMap){
        System.out.println("action");
        System.out.println(hashMap);
        pages.forEach(pom->
                System.out.println(pom.name+"--"+pom.methods));
//        如果是page级别的关键字
        if (hashMap.containsKey("page")) {
            String action = hashMap.get("action").toString();
            String pageName = hashMap.get("page").toString();
            pages.forEach(pom->System.out.println(pom.name));

            pages.stream()
                    .filter(pom -> pom.name.equals(pageName))
                    .findFirst()
                    .get()
                    .methods.get(action).forEach(step -> {
                action(step);
            });
        } else {
//            自动化级别
            if (hashMap.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) hashMap.get("click");
                click(by);
            }

            if (hashMap.containsKey("sendKeys")) {
                HashMap<String,Object> by=(HashMap<String, Object>) hashMap.get("sendKeys");
                sendKeys(by);
            }
        }

    }
    /*
    执行方法，解析出url中所需要的数据
     */
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
                HashMap<String,Object> by=(HashMap<String, Object>) m.get("sendKeys");
                sendKeys(by);
            }
            if (m.containsKey("action")){
                action(m);
            }
            /*if (m.containsKey("page")){
                page(m);
            }*/
        });
    }
    //ctrl+shift+t生成测试类
    /*
   解析yaml文件，将yaml文件解析成hashmap的格式同时定义了数据格式uiauto
     */
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
    /*
    生成单个页面的po
     */
    public PageObjectModel loadPage(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom=null;
        try {
            pom = mapper.readValue(
                    new File(path),
                    PageObjectModel.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }
    /*
    多个页面的调用与生成
     */
    public void loadPages(String dir){
        //获取文件下面带有pag的文件
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");

            }
        })).forEach(path->{
            path=dir+path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }
}


