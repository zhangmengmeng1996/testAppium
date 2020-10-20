package com.test_framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BasePageTest {
    private static BasePage basePage;
    @BeforeAll
    static void beforeAll(){
      basePage=new BasePage();
    }

    @Test
    void click() {
    }

    @Test
    void find() {
    }

    @Test
    void send() {
    }

    @Test
    void getText() {
    }


    @Test
    void run() {
        UIAuto uiAuto=basePage.load("/test_framework/uiauto.yaml");
        basePage.run(uiAuto);
    }
    /*
    执行测试用例页面
     */
    @Test
    void runPom(){
        basePage.loadPages("src/main/resources/test_framework/");
        UIAuto uiauto=basePage.load("/test_framework/webauto-3.yaml");
        basePage.run(uiauto);
    }
    @Test
    void load() throws JsonProcessingException {
        UIAuto uiauto=basePage.load("/test_framework/uiauto.yaml");
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiauto));
    }
}