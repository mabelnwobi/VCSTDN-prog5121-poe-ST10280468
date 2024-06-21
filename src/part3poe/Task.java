/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part3poe;

import javax.swing.JOptionPane;
import java.util.ArrayList;




    public class Task {
    public static int totalHours = 0;
    public static int taskCount = 0;


    private final String taskName;
    private final int taskNumber;
    private final String taskDescription;
    private final String developerDetails;
    private final int taskDuration;
    private final String taskID;
    public String taskStatus;

    public Task(String taskName, int taskDuration, String taskDescription, String developerDetails) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskNumber = ++taskCount; // Increment and assign the current taskCount
        this.taskID = createTaskID();
        this.taskStatus = "To Do"; // Default status for testing
        totalHours += taskDuration;
    }

    public void updateStatus(String status) {
        if (status.equals("To Do") || status.equals("Doing") || status.equals("Done")) {
            this.taskStatus = status;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid status. Please choose 'To Do', 'Doing', or 'Done'.");
        }
    }

    public static class Tasks {
        public static ArrayList<String> developers = new ArrayList<>();
        public static ArrayList<String> taskNames = new ArrayList<>();
        public static ArrayList<String> taskIDs = new ArrayList<>();
        public static ArrayList<Integer> taskDurations = new ArrayList<>();
        public static ArrayList<String> taskStatuses = new ArrayList<>();

        public static void printDetails() {
            StringBuilder details = new StringBuilder("Task Details:\n");
            for (int i = 0; i < taskNames.size(); i++) {
                details.append("Task Name: ").append(taskNames.get(i))
                        .append("\nDeveloper: ").append(developers.get(i))
                        .append("\nTask ID: ").append(taskIDs.get(i))
                        .append("\nDuration: ").append(taskDurations.get(i)).append(" hours\n")
                        .append("Status: ").append(taskStatuses.get(i)).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, details.toString());
        }

        public static void populateTasks(String developer, String taskName, int taskDuration, String taskStatus) {
            developers.add(developer);
            taskNames.add(taskName);
            taskDurations.add(taskDuration);
            taskStatuses.add(taskStatus);
            taskIDs.add(createTaskID(taskName, taskCount + 1, developer)); // Use taskCount + 1 for correct numbering
            totalHours += taskDuration;
            taskCount++;
        }

        public static void deleteTask(String taskName) {
            int index = taskNames.indexOf(taskName);
            if (index != -1) {
                totalHours -= taskDurations.get(index);
                developers.remove(index);
                taskNames.remove(index);
                taskIDs.remove(index);
                taskDurations.remove(index);
                taskStatuses.remove(index);
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Task not found.");
            }
        }

        public static void displayLongestTask() {
            if (taskDurations.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tasks available.");
                return;
            }

            int longestDurationIndex = 0;
            for (int i = 1; i < taskDurations.size(); i++) {
                if (taskDurations.get(i) > taskDurations.get(longestDurationIndex)) {
                    longestDurationIndex = i;
                }
            }
            JOptionPane.showMessageDialog(null, "Developer: " + developers.get(longestDurationIndex) +
                    "\nDuration: " + taskDurations.get(longestDurationIndex) + " hours");
        }

        private static String createTaskID(String taskName, int taskNumber, String developerDetails) {
            String devInitials = developerDetails.substring(0, 2).toUpperCase();
            String devEnd = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
            return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + devEnd;
        }
        
        
        public static int returnTotalHours() {
            return totalHours;
        }
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    private String createTaskID() {
        String devInitials = developerDetails.substring(0, 2).toUpperCase();
        String devEnd = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + devEnd;
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Duration: " + taskDuration + " hours";
    }

   

}
