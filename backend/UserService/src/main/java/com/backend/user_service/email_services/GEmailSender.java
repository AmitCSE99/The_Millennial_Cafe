package com.backend.user_service.email_services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class GEmailSender {

    public boolean sendEmail(String to, String from,String subject,String message){
        boolean flag=false;

        Properties properties=new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.host","smtp.gmail.com");

        String user="amitenducse91";
        String password="rdtbtpznqxenucrg";


        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });

        try{
            Message myMessage=new MimeMessage(session);
            myMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            myMessage.setFrom(new InternetAddress(from));
            myMessage.setSubject(subject);
            myMessage.setText(message);

            Transport.send(myMessage);

            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
