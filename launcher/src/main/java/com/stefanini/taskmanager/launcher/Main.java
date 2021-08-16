package com.stefanini.taskmanager.launcher;

import com.stefanini.taskmanager.services.utils.ReadFromProperties;
import org.apache.log4j.PropertyConfigurator;
import static com.stefanini.taskmanager.launcher.TaskExecutorService.execute;
import static com.stefanini.taskmanager.services.utils.ReadInputsFromConsole.readInputsFromConsole;

public class Main {
    public static void main(String[] args) {
        PropertyConfigurator.configure(ReadFromProperties.read("log4j-console.properties"));
        execute(readInputsFromConsole());
        System.out.println("Finished");
    }
}

