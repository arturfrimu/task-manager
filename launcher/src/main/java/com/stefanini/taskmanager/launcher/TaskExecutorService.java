package com.stefanini.taskmanager.launcher;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.stefanini.taskmanager.launcher.command.CommandFactory.getCommand;
import static com.stefanini.taskmanager.services.database.DatabaseSessionFactory.closeSessionFactory;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineDown;
import static com.stefanini.taskmanager.services.utils.StringGenerator.lineUp;

public class TaskExecutorService {
    public static void execute(Map<String, String[]> commands) {
        Logger logger = Logger.getLogger(TaskExecutorService.class);
        ExecutorService es = Executors.newFixedThreadPool(4);
        try {
            es.submit(() -> {
                getCommand(commands.get("createUser")).execute();
            }).get();

            es.submit(() -> {
                getCommand(commands.get("addTask")).execute();
            }).get();

            es.submit(() -> {
                getCommand(commands.get("showTasks")).execute();
            }).get();

            es.submit(() -> {
                getCommand(commands.get("showAllUsers")).execute();
            }).get();

            es.shutdown();
        } catch (InterruptedException | ExecutionException | CancellationException e) {
            logger.error(lineUp + " ---- " + e + lineDown);
        } finally {
            es.shutdown();
            closeSessionFactory();
        }
    }
}


