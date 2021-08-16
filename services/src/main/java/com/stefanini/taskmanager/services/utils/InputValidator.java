package com.stefanini.taskmanager.services.utils;

import org.apache.log4j.Logger;

public class InputValidator {
    static Logger logger = Logger.getLogger(InputValidator.class);

    public static String subStringInput(String input) {
        input = input.substring(4);
        if (input.contains("'")) {
            input = input.replace("'", "");
        }
        return input;
    }

    public static boolean isValid(String input){
        boolean isValid = false;
        if (input != null && input.length() > 1){
            if (input.startsWith("-fn=")) {
                isValid = true;
            } else if (input.startsWith("-ln=")) {
                isValid = true;
            } else if (input.startsWith("-un=") && input.length() < 30) {
                isValid = true;
            } else if (input.startsWith("-tt=")) {
                isValid = true;
            } else if (input.startsWith("-td=")) {
                isValid = true;
            } else if (input.startsWith("-gn=")) {
                isValid = true;
            } else {
                logger.info("Input [ " + input + " ] has wrong format [ -xx='input' ]");
            }
        }
        return isValid;
    }
}
