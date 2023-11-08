package com.tanhua.sso.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SmsServiceTest {
    @Autowired
    private SmsService smsService;

    @Test
    public void testSend() {
        String mobile = "12345678910";
        String sms = mobile.substring(5);
//        String sms = smsService.sendSms("158****7944");
        System.out.println(sms);
    }
}
