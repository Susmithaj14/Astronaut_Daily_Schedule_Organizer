package com.taskmanager;


import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		        ScheduleManager manager = ScheduleManager.getInstance();
		        TaskObserver observer = new TaskObserver();
		        manager.addObserver(observer);

		        Scanner scanner = new Scanner(System.in);
		        String command;

		        while (true) {
		            System.out.println("Enter command (add, remove, view, viewByPriority, exit):");
		            command = scanner.nextLine().trim().toLowerCase();

		            switch (command) {
		                case "add":
		                    System.out.println("Enter task description:");
		                    String description = scanner.nextLine();
		                    System.out.println("Enter start time (HH:mm):");
		                    String startTime = scanner.nextLine();
		                    System.out.println("Enter end time (HH:mm):");
		                    String endTime = scanner.nextLine();
		                    System.out.println("Enter priority level:");
		                    String priority = scanner.nextLine();
		                    Task newTask = TaskFactory.createTask(description, startTime, endTime, priority);
		                    manager.addTask(newTask);
		                    break;
		                case "remove":
		                    System.out.println("Enter task description to remove:");
		                    String descToRemove = scanner.nextLine();
		                    manager.removeTask(descToRemove);
		                    break;
		                case "view":
		                    List<Task> tasks = manager.viewTasks();
		                    tasks.forEach(System.out::println);
		                    break;
		                case "viewbypriority":
		                    System.out.println("Enter priority level to view:");
		                    String priorityLevel = scanner.nextLine();
		                    List<Task> priorityTasks = manager.viewTasksByPriority(priorityLevel);
		                    priorityTasks.forEach(System.out::println);
		                    break;
		                case "exit":
		                    System.out.println("Exiting application...");
		                    return;
		                default:
		                    System.out.println("Invalid command. Try again.");
		                    break;
		            }
		        }
		    }
		


	}