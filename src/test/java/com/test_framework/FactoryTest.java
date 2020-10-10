package com.test_framework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @Test
    void create() {
        //静态外部类调用静态
        BasePage web=Factory.create("web");
        UIAuto uiAuto= web.load("/test_framework/webauto.yaml");
        web.run(uiAuto);
    }
}