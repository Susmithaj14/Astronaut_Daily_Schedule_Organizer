package com.taskmanager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
	
	

	
	    private static ScheduleManager instance;
	    private List<Task> tasks;
	    private List<Observer> observers;

	    private ScheduleManager() {
	        tasks = new ArrayList<>();
	        observers = new ArrayList<>();
	    }

	    public static synchronized ScheduleManager getInstance() {
	        if (instance == null) {
	            instance = new ScheduleManager();
	        }
	        return instance;
	    }

	    public void addObserver(Observer observer) {
	        observers.add(observer);
	    }

	    public void removeObserver(Observer observer) {
	        observers.remove(observer);
	    }

	    private void notifyObservers(String message) {
	        for (Observer observer : observers) {
	            observer.update(message);
	        }
	    }

	    public boolean addTask(Task task) {
	        if (isConflict(task)) {
	            notifyObservers("Error: Task conflicts with an existing task.");
	            return false;
	        }
	        tasks.add(task);
	        notifyObservers("Task added successfully. No conflicts.");
	        return true;
	    }

	    public boolean removeTask(String description) {
	        for (Task task : tasks) {
	            if (task.getDescription().equals(description)) {
	                tasks.remove(task);
	                notifyObservers("Task removed successfully.");
	                return true;
	            }
	        }
	        notifyObservers("Error: Task not found.");
	        return false;
	    }

	    public List<Task> viewTasks() {
	        tasks.sort(Comparator.comparing(Task::getStartTime));
	        return Collections.unmodifiableList(tasks);
	    }

	    public List<Task> viewTasksByPriority(String priority) {
	        List<Task> priorityTasks = new ArrayList<>();
	        for (Task task : tasks) {
	            if (task.getPriority().equalsIgnoreCase(priority)) {
	                priorityTasks.add(task);
	            }
	        }
	        return Collections.unmodifiableList(priorityTasks);
	    }

	    private boolean isConflict(Task newTask) {
	        for (Task task : tasks) {
	            if (isOverlapping(task, newTask)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private boolean isOverlapping(Task existingTask, Task newTask) {
	        return !(newTask.getEndTime().compareTo(existingTask.getStartTime()) <= 0 ||
	                 newTask.getStartTime().compareTo(existingTask.getEndTime()) >= 0);
	    }
	}