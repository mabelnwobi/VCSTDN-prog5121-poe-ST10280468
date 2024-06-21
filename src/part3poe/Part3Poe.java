
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package part3poe;
import javax.swing.JOptionPane;
/**
 *
 * @author mabel
 */

public class Part3Poe {
    private static int maxTasks;
    private static int taskCount = 0;

    public static void main(String[] args) {
        // Start the login process
        Login.login();

        // Display welcome message
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        // Ask the user for the number of tasks they wish to enter
        String input = JOptionPane.showInputDialog("How many tasks would you like to enter?");
        maxTasks = Integer.parseInt(input);

        // Menu loop
        boolean task = true;
        while (task) {
            String menuOption = JOptionPane.showInputDialog(null,
                    "Please choose an option:\n" +
                            "1) Add tasks\n" +
                            "2) Show report\n" +
                            "3) Delete Tasks\n" +
                            "4) Search for tasks\n" +
                            "5) Display Report\n" +
                            "6) Quit");

            switch (menuOption) {
                case "1":
                    if (maxTasks > taskCount) {
                        displayTaskManager();
                    } else {
                        JOptionPane.showMessageDialog(null, "You have reached the maximum number of tasks.");
                    }
                    break;
                case "2":
                    Task.Tasks.printDetails();
                    break;
                case "3":
                    String taskNameToDelete = JOptionPane.showInputDialog("Enter the task name to delete:");
                    Task.Tasks.deleteTask(taskNameToDelete);
                    break;
                case "4":
                    String taskNameToSearch = JOptionPane.showInputDialog("Enter the task name to search:");
                    searchTask(taskNameToSearch);
                    break;
                case "5":
                    Task.Tasks.displayLongestTask();
                    break;
                case "6":
                    task = false;
                    JOptionPane.showMessageDialog(null, "Exiting EasyKanban.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void displayTaskManager() {
        // Displaying and creating task details
        String taskName = JOptionPane.showInputDialog("Enter the task name:");
        String taskDescription = JOptionPane.showInputDialog("Enter the task description (50 characters max):");

        if (taskDescription.length() > 50) {
            JOptionPane.showMessageDialog(null, "Task description is too long. Please enter up to 50 characters.");
            return;
        }

        String getTaskStatus = JOptionPane.showInputDialog(null,
                "Please enter the task status:\n" +
                        "1) To Do\n" +
                        "2) Doing\n" +
                        "3) Done");

        String taskStatus;
        switch (getTaskStatus) {
            case "1":
                taskStatus = "To Do";
                break;
            case "2":
                taskStatus = "Doing";
                break;
            case "3":
                taskStatus = "Done";
                break;
            default:
                displayTaskManager(); // Recursive call to show the dialog again
                return; // Exit the method after the recursive call
        }

        String developerDetails = JOptionPane.showInputDialog("Enter the developer details:");
        String durationInput = JOptionPane.showInputDialog("Enter the task duration in hours:");

        int taskDuration;
        try {
            taskDuration = Integer.parseInt(durationInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration. Please enter a valid number.");
            return;
        }

        Task.Tasks.populateTasks(developerDetails, taskName, taskDuration, taskStatus);
        taskCount++;

        // Display total hours after all tasks have been entered
        JOptionPane.showMessageDialog(null, "Total hours: " + Task.Tasks.returnTotalHours());
    }

    private static void searchTask(String taskName) {
        int index = Task.Tasks.taskNames.indexOf(taskName);
        if (index != -1) {
            String details = "Task Name: " + Task.Tasks.taskNames.get(index) +
                    "\nDeveloper: " + Task.Tasks.developers.get(index) +
                    "\nTask ID: " + Task.Tasks.taskIDs.get(index) +
                    "\nDuration: " + Task.Tasks.taskDurations.get(index) + " hours\n" +
                    "Status: " + Task.Tasks.taskStatuses.get(index);
            JOptionPane.showMessageDialog(null, details);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }
}
