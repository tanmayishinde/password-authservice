package com.microservice.passwordmanagerservice.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
@Component
public class SendMailUtil {
    public static String generateOtp() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    public static boolean sendMail(String otp, String email) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");// SMTP server
        props.put("mail.smtp.socketFactory.port", "465");// SMTP port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");// Enable SSL
        props.put("mail.smtp.auth", "true");// Enable authentication
        props.put("mail.smtp.port", "465");// SMTP port

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("quickindustrymail@gmail.com",
                        "qvmdcqsyxadfohde");
            }
        });

        try {
            //String mail = email.toString();
            Message message1 = new MimeMessage(session);
            message1.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message1.setFrom(new InternetAddress("PasswordManager"));
            message1.setSubject("Login OTP");
            message1.setText("Please refer the attached OTP for your login: " + otp);
            // send message
            //This will connect to the SMTP server, authenticate if required, and send the email.
            Transport.send(message1);
            return true;
        } catch (MessagingException e) {
            System.out.println(e.getCause() + "::::::" + e.getMessage());
        }
        return false;
    }

}
