package com.stefanini.taskmanager.launcher;

import com.stefanini.taskmanager.services.TaskDao;

public class Main {
	public static void main(String[] args){
		System.out.println("launcher");
		
		System.out.println( new TaskDao().getTasks());
		
	}
}
