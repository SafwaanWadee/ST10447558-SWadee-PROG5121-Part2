/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yourpackage.tasktests;
import java.util.function.BooleanSupplier;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Safwaan
 */
//Task class created to create unit tests
public class Task {
    private Task task1;
    private Task task2;

    @BeforeEach
    public void printTaskDetails() {
        task1 = new Task();
        task1.setTaskName("Login Feature");
        task1.setDeveloperDetails("Robyn Harrison");
        task1.setTaskDuration(10);
        task1.setTaskStatus("To Do");

        task2 = new Task();
        task2.setTaskName("Add Task Feature");
        task2.setDeveloperDetails("Mike Smith");
        task2.setTaskDuration(10);
        task2.setTaskStatus("Doing");
    }

    @Test
    public void checkTaskDescription() {
        // Successful case
        String validDescription = "Create Login to authenticate users";
        assertTrue(task1.checkTaskDescription(validDescription), "Task successfully captured");

        // Failure case
        String invalidDescription = "This description is way too long for the allowed limit of fifty characters.";
        assertFalse(task1.checkTaskDescription(invalidDescription), "Please enter a task description of less than 50 characters");
    }

    @Test
    public void createTaskID() {
        // Testing Task ID for task1
        task1.createTaskID(1);
        assertEquals("LO:1:SON", task1.getTaskID(), "Task ID should match the expected format.");

        // Testing Task ID for task2
        task2.createTaskID(2);
        assertEquals("AD:2:ITH", task2.getTaskID(), "Task ID should match the expected format.");
    }

    @Test
    public void returnTotalHours() {
        // Test with a series of tasks
        Task[] tasks = new Task[5];
        tasks[0] = new Task();
        tasks[0].setTaskDuration(10);
        tasks[1] = new Task();
        tasks[1].setTaskDuration(12);
        tasks[2] = new Task();
        tasks[2].setTaskDuration(55);
        tasks[3] = new Task();
        tasks[3].setTaskDuration(11);
        tasks[4] = new Task();
        tasks[4].setTaskDuration(1);

        int totalHours = 0;
        for (Task task : tasks) {
        }

        assertEquals(89, totalHours, "Total hours should equal 89 for the given task durations");
    }

    private void setTaskName(String task_Name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private void setDeveloperDetails(String developer_Details) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setTaskDuration(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setTaskStatus(String task_Status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private BooleanSupplier checkTaskDescription(String task_Description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void createTaskID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Object getTaskID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
