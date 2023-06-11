package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHandler {
    void sendMail() {
        Properties sysProperties = System.getProperties();
        System.out.println(sysProperties);

        sysProperties.put("mail.smtp.host", MailMetaData.HostServer);
        sysProperties.put("mail.smtp.port", MailMetaData.port);
        sysProperties.put(MailMetaData.sslProperty, "true");
        sysProperties.put(MailMetaData.authPerm, "true");

        //creating a session
        Authenticator mailAuthenticator = new CustomizedAuth();
        Session mailSession = Session.getInstance(sysProperties, mailAuthenticator);
        MimeMessage mailMessage = new MimeMessage(mailSession);
        try {
            mailMessage.setFrom(MailMetaData.myUserMail);
            mailMessage.setSubject("This is my weekly test project testing");
            mailMessage.setText("Hey this is Guddu who is trying to sending mail by using java code");

            //set receiver
            Address receiverEmail = new InternetAddress(MailMetaData.receiverMail);
            mailMessage.setRecipient(Message.RecipientType.TO, receiverEmail);
            Transport.send(mailMessage);
        } catch (Exception mailException) {
            System.out.println(mailException.toString());
        }
    }
}
