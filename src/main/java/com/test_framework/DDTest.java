package com.test_framework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: testAppium
 * @description: 传统的测试用例
 * @author: mengmeng
 * @create: 2020-10-13 16:17
 **/
public class DDTest {
    /*
    执行工厂中的create以及load模式
     */
    private static BasePage basePage;
    @BeforeAll
    static void beforeAll(){
        //加载通用配置
    }
    @BeforeEach
    void beforeEach(){
        //每个用例相关
    }
    @ParameterizedTest
    @MethodSource
    public void classic(UIAuto uiAuto){
        basePage.run(uiAuto);

    }
    public static Stream<Arguments> classic(){
        basePage=Factory.create("web");
        List<Arguments> all=new ArrayList<Arguments>();
        //可以添加多个路径
        Arrays.asList("/test_framework/webauto-1.yaml")
                .stream().forEach(path->{
                    UIAuto uiAuto= basePage.load(path);
                    uiAuto.description=path;
                    all.add(Arguments.arguments(uiAuto,uiAuto.description));
                }

        );
        return all.stream();
    }

}
