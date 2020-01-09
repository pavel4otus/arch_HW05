package ru.pavel2107.arch.notification.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class PushServiceImpl implements PushService {
    @Value("${app.push.account_sid}")
    private String ACCOUNT_SID;

    @Value("${app.push.auth_token}")
    private String AUTH_TOKEN;

    @Value("${app.push.twilio_number}")
    private String TWILIO_NUMBER;

    //private static final String ACCOUNT_SID = "ACffb38145b9c140cbcbff160dc18bfd1e";
    //private static final String AUTH_TOKEN = "33480ce54329c95467b57dceda3e6d07";
    //private static final String TWILIO_NUMBER = "+12403292300";

    @Override
    public void push(String phone, String text) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber from = new PhoneNumber(TWILIO_NUMBER);
        PhoneNumber to = new PhoneNumber(phone);
        Message message = Message.creator(to, from, text).create();

        System.out.println("Sending :" + text + " to:" + phone);
    }
}
