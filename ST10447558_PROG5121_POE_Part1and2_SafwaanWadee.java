/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.progpart1and2;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Safwaan
 */
public class ST10447558_PROG5121_POE_Part1and2_SafwaanWadee {
    // Store user credentials
    private static String registeredUsername;
    private static String registeredPassword;
    private static String firstName;
    private static String lastName;

    // Task management
    private static final List<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // User Registration
        // User Registration
        registerUser();
        // User Login
        if (loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
            manageTasks();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed.");
        }
    }

    // Method for user registration
    public static void registerUser() {
        boolean validInput = false;

        // Username input and validation
        while (!validInput) {
            String username = JOptionPane.showInputDialog("Enter username:");
            if (checkUserName(username)) {
                registeredUsername = username;
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                validInput = true;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Ensure it contains an underscore and is no more than five characters in length.");
            }
        }

        validInput = false;

        // Password input and validation
        while (!validInput) {
            String password = JOptionPane.showInputDialog("Enter password:");
            if (checkPasswordComplexity(password)) {
                registeredPassword = password;
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                validInput = true;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Ensure it contains at least 8 characters, a capital letter, a number, and a special character.");
            }
        }

        // First name and last name input
        firstName = JOptionPane.showInputDialog("Enter your first name:");
        lastName = JOptionPane.showInputDialog("Enter your last name:");

        JOptionPane.showMessageDialog(null, "Registration successful.");
    }

    // Method for user login
    public static boolean loginUser() {
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password = JOptionPane.showInputDialog("Enter your password:");

        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Task management after successful login
    public static void manageTasks() {
        while (true) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1) Add tasks\n2) Show report (Coming Soon)\n3) Quit");

            switch (choice) {
                case "1" -> addTasks();
                case "2" -> JOptionPane.showMessageDialog(null, "Coming Soon");
                case "3" -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice, please try again.");
            }
        }
    }

    // Add tasks based on user input
    public static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you wish to enter?"));

        for (int i = 0; i < numTasks; i++) {
            Task task = new Task();
            taskCount++; // Increment the task number

            String taskName = JOptionPane.showInputDialog("Enter task name:");
            task.setTaskName(taskName);

            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
            if (task.checkTaskDescription(taskDescription)) {
                task.setTaskDescription(taskDescription);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                continue; // Skip to the next task
            }

            String developerDetails = JOptionPane.showInputDialog("Enter developer details (First Last):");
            task.setDeveloperDetails(developerDetails);

            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));
            task.setTaskDuration(taskDuration);

            // Task ID generation
            task.createTaskID(taskCount);
            task.setTaskStatus(selectTaskStatus());

            // Add task to list and display details
            tasks.add(task);
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

        // Calculate and display total hours
        int totalHours = returnTotalHours();
        JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours);
    }

    // Select task status
    private static String selectTaskStatus() {
        String statusChoice = JOptionPane.showInputDialog("Select task status:\n1) To Do\n2) Done\n3) Doing");
        return switch (statusChoice) {
            case "1" -> "To Do";
            case "2" -> "Done";
            case "3" -> "Doing";
            default -> "To Do"; // Default status
        };
    }

    // Validate username based on the conditions
    public static boolean checkUserName(String username) {
        // Username must contain an underscore and be no more than 5 characters
        return username.contains("_") && username.length() <= 5;
    }

    // Validate password based on the conditions
    public static boolean checkPasswordComplexity(String password) {
        // Password must be at least 8 characters long, contain a capital letter, a number, and a special character
        if (password.length() < 8) {
            return false;
        }

        Pattern capitalLetter = Pattern.compile("[A-Z]");
        Pattern number = Pattern.compile("[0-9]");
        Pattern specialChar = Pattern.compile("[^a-zA-Z0-9]");

        Matcher hasCapital = capitalLetter.matcher(password);
        Matcher hasNumber = number.matcher(password);
        Matcher hasSpecial = specialChar.matcher(password);

        return hasCapital.find() && hasNumber.find() && hasSpecial.find();
    }

    // Return total hours from all tasks
    public static int returnTotalHours() {
        int total = 0;
        for (Task task : tasks) {
            total += task.getTaskDuration();
        }
        return total;
    }
}

// Task class to manage individual tasks
class Task {
    private String taskName;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;
    private int taskNumber;

    // Check task description length
    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Create task ID based on the specifications
    public void createTaskID(int taskNumber) {
        String firstTwoLetters = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String lastThreeLetters = developerDetails.split(" ")[1].length() >= 3 ? developerDetails.split(" ")[1].substring(developerDetails.split(" ")[1].length() - 3).toUpperCase() : developerDetails.split(" ")[1].toUpperCase();
        this.taskID = firstTwoLetters + ":" + taskNumber + ":" + lastThreeLetters;
    }

    // Print full task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskID + "\n" +
               "Duration: " + taskDuration + " hours";
    }

    // Getters and setters for the task attributes
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDeveloperDetails() {
        return developerDetails;
    }

    public void setDeveloperDetails(String developerDetails) {
        this.developerDetails = developerDetails;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}