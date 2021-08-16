package com.stefanini.taskmanager.services.aspects;

import com.stefanini.taskmanager.services.service.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class EmailServiceAspect {

    private final EmailService emailService;

    public EmailServiceAspect() {
        this.emailService = new EmailService();
    }

    @AfterReturning(value = "execution(* com.stefanini.taskmanager.services.dao.impl.BaseDaoImpl.save(..))")
    public void sendEmailAfterSave(JoinPoint joinPoint) {
        emailService.sendEmail(joinPoint);
    }

    @AfterReturning(value = "execution(* com.stefanini.taskmanager.services.dao.impl.TaskDaoImpl.addTaskToUser(..))")
    public void sendEmailAfterAssignTask(JoinPoint joinPoint){
        emailService.sendEmail(joinPoint);
    }
}
