package com.stefanini.taskmanager.services.service.impl;

import com.stefanini.taskmanager.services.model.Email;
import com.stefanini.taskmanager.services.service.EmailService;
import com.stefanini.taskmanager.services.utils.ReadFromProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    static Logger logger = Logger.getLogger(com.stefanini.taskmanager.services.service.EmailService.class);

    public Email sendEmail(Email email) {
        Properties properties = null;
        try {
            PropertyConfigurator.configure(ReadFromProperties.read("log4j-console.properties"));
            properties = ReadFromProperties.read("email.properties");
            final Properties finalProperties = properties;
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(finalProperties.getProperty("myAccount"), finalProperties.getProperty("password"));
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getFrom()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            Transport.send(message);
            return email;
        } catch (Exception e) {
            logger.error(e + "\n\n");
        }
        return email;
    }
}
