package com.stefanini.taskmanager.services.converter;

import com.stefanini.taskmanager.services.model.Email;
import com.stefanini.taskmanager.services.utils.ReadFromProperties;
import org.aspectj.lang.JoinPoint;
import java.util.Arrays;
import java.util.Properties;

public class JoinPointToEmail {
    public static Email joinPointToEmail(JoinPoint joinPoint) throws Exception {
        Properties properties = ReadFromProperties.read("email.properties");
        Email email = new Email();
        email.setFrom(properties.getProperty("myAccount"));
        email.setTo(properties.getProperty("recipient"));
        email.setSubject(joinPoint.getThis().toString());
        email.setContent(Arrays.toString(joinPoint.getArgs()));
        return email;
    }
}
