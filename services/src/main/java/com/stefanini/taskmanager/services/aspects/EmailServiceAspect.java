package com.stefanini.taskmanager.services.aspects;

import com.stefanini.taskmanager.services.model.Email;
import com.stefanini.taskmanager.services.service.impl.EmailServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import static com.stefanini.taskmanager.services.converter.JoinPointToEmail.joinPointToEmail;

@Aspect
public class EmailServiceAspect {

    private final EmailServiceImpl emailServiceImpl;

    public EmailServiceAspect() {
        this.emailServiceImpl = new EmailServiceImpl();
    }

    @AfterReturning(value = "execution(* com.stefanini.taskmanager.services.dao.impl.BaseDaoImpl.save(..))")
    public void sendEmailAfterSave(JoinPoint joinPoint) throws Exception {
        Email email = joinPointToEmail(joinPoint);
        emailServiceImpl.sendEmail(email);
    }

    @AfterReturning(value = "execution(* com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl.addTaskToUser(..))")
    public void sendEmailAfterAssignTask(JoinPoint joinPoint) throws Exception {
        Email email = joinPointToEmail(joinPoint);
        emailServiceImpl.sendEmail(email);
    }
}