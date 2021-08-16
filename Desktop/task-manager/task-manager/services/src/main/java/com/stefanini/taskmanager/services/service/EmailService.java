package com.stefanini.taskmanager.services.service;

import com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl;
import com.stefanini.taskmanager.services.dao.impl.UserDaoImpl;
import com.stefanini.taskmanager.services.utils.ReadFromProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

public class EmailService {
    static Logger logger = Logger.getLogger(EmailService.class);

    public void sendEmail(JoinPoint joinPoint) {
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
            message.setFrom(new InternetAddress(finalProperties.getProperty("myAccount")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(finalProperties.getProperty("recipient")));

            message.setSubject(joinPoint.getStaticPart().toString());
            message.setText(Arrays.toString(joinPoint.getArgs()));
            Transport.send(message);
        } catch (Exception e) {
            logger.error(e + "\n\n");
        }
    }
}

