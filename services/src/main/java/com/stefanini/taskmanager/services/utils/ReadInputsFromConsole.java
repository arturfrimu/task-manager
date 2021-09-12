package com.stefanini.taskmanager.services.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadInputsFromConsole {
    public static Map<String, String []> readInputsFromConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert first name");
        String firstName = "-fn='" + sc.nextLine() + "'";
        System.out.println("Insert last name");
        String lastName = "-ln='" + sc.nextLine() + "'";
        System.out.println("Insert user name");
        String username = "-un='" + sc.nextLine() + "'";
        System.out.println("Insert title");
        String title = "-tt='" + sc.nextLine() + "'";
        System.out.println("Insert description name");
        String description = "-td='" + sc.nextLine() + "'";
        String[] arguments1 = {"-createUser", firstName, lastName, username};
        String[] arguments2 = {"-addTask", username, title, description};
        String[] arguments3 = {"-showTasks", username};
        String[] arguments4 = {"-showAllUsers"};
        Map<String, String []> map = new HashMap<>();
        map.put("createUser", arguments1);
        map.put("addTask", arguments2);
        map.put("showTasks", arguments3);
        map.put("showAllUsers", arguments4);
        return map;
    }
}
