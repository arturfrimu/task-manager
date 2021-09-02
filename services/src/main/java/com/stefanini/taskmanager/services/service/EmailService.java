package com.stefanini.taskmanager.services.service;

import com.stefanini.taskmanager.services.model.Email;

public interface EmailService {

    Email sendEmail(Email email);

}
