package com.testcase;

import com.xueqiu.page.MainPage;
import com.xueqiu.page.SearchPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @program: testSelenium->SearchPageTest
 * @description: 测试 用例
 * @author: qiuyu
 * @create: 2020-09-11 13:46
 **/
//appium --session-override
public class SearchPageTest {
    static MainPage mainPage;
    static SearchPage searchPage;
    @BeforeAll
    static void beforeAll(){
        try {
            searchPage=new MainPage().toSearch();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    @AfterAll
    static void afterAll(){
        searchPage.quit();
    }
    @ParameterizedTest
    @CsvSource({"alibaba,阿里巴巴","jd,京东"})
    void search(String keyword, String name){
        assertEquals(searchPage.search(keyword).getSearchList().get(0),name);

    }
    @Test
    void getPrice(){
        assertTrue(searchPage.search("alibaba").getPrice()>200);
    }

}
