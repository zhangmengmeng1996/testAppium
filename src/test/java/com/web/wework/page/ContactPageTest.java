package com.web.wework.page;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPageTest {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }
    @Test
    void testAddMember(){
        String getUserName=contact.addMember("6","6","15600534769").search("6").getUserName();
        assertEquals(getUserName,"6");
    }
    @Test
    void testSearch(){

        contact.search("3").delete();
    }
    @Test
    void importFromFile(){
        contact.importFromFile("C:\\Users\\Administrator\\.ssh\\workspace\\testSelenium\\src\\main\\resources\\通讯录批量导入.xlsx");
    }
    @AfterAll
    static void afterAll(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.quit();
    }
}