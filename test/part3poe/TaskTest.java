package part3poe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TaskTest {

    @Before
    public void setUp() {
        Task.totalHours = 0;
        Task.taskCount = 0;
        Task.Tasks.developers.clear();
        Task.Tasks.taskNames.clear();
        Task.Tasks.taskIDs.clear();
        Task.Tasks.taskDurations.clear();
        Task.Tasks.taskStatuses.clear();

        // Populate tasks with the provided test data
        Task.Tasks.populateTasks("Mike Smith", "Create Login", 5, "To Do");
        Task.Tasks.populateTasks("Edward Harrison", "Create Add Features", 8, "Doing");
        Task.Tasks.populateTasks("Samantha Paulson", "Create Reports", 2, "Done");
        Task.Tasks.populateTasks("Glenda Oberholzer", "Add Arrays", 11, "To Do");
    }

    @Test
    public void testCheckTaskDescription() {
        Task taskWithValidDescription = new Task("Task1", 5, "A valid task description", "John Doe");
        Task taskWithInvalidDescription = new Task("Task2", 3, "An invalid task description that is way too long and exceeds the fifty character limit", "Jane Doe");
        
        assertTrue(taskWithValidDescription.checkTaskDescription());
        assertFalse(taskWithInvalidDescription.checkTaskDescription());
    }

    @Test
    public void testPrintTaskDetails() {
        Task instance = new Task("Task1", 5, "A valid task description", "John Doe");
        String expResult = "Task Status: To Do\n" +
                "Developer Details: John Doe\n" +
                "Task Number: 1\n" +
                "Task Name: Task1\n" +
                "Task Description: A valid task description\n" +
                "Task ID: TA:1:DOE\n" +
                "Duration: 5 hours";
        
        String result = instance.printTaskDetails();
        assertEquals(expResult, result);
    }

    @Test
    public void testReturnTotalHours() {
        int expResult = 26; // Sum of 5 + 8 + 2 + 11
        int result = Task.Tasks.returnTotalHours();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateStatus() {
        Task instance = new Task("Task1", 5, "A valid task description", "John Doe");
        
        instance.updateStatus("Doing");
        assertEquals("Doing", instance.taskStatus);

        instance.updateStatus("Done");
        assertEquals("Done", instance.taskStatus);

        instance.updateStatus("InvalidStatus");
        // The status should remain "Done" because "InvalidStatus" is not a valid status
        assertEquals("Done", instance.taskStatus);
    }

    @Test
    public void testPopulateTasks() {
        assertEquals(4, Task.Tasks.developers.size());
        assertEquals(4, Task.Tasks.taskNames.size());
        assertEquals(4, Task.Tasks.taskDurations.size());
        assertEquals(4, Task.Tasks.taskStatuses.size());
        assertEquals(4, Task.Tasks.taskIDs.size());
        assertEquals(26, Task.Tasks.returnTotalHours());

        assertEquals("Mike Smith", Task.Tasks.developers.get(0));
        assertEquals("Edward Harrison", Task.Tasks.developers.get(1));
        assertEquals("Samantha Paulson", Task.Tasks.developers.get(2));
        assertEquals("Glenda Oberholzer", Task.Tasks.developers.get(3));

        assertEquals("Create Login", Task.Tasks.taskNames.get(0));
        assertEquals("Create Add Features", Task.Tasks.taskNames.get(1));
        assertEquals("Create Reports", Task.Tasks.taskNames.get(2));
        assertEquals("Add Arrays", Task.Tasks.taskNames.get(3));
    }

    @Test
    public void testDeleteTask() {
        Task.Tasks.deleteTask("Create Reports");

        assertEquals(3, Task.Tasks.developers.size());
        assertEquals(3, Task.Tasks.taskNames.size());
        assertEquals(3, Task.Tasks.taskDurations.size());
        assertEquals(3, Task.Tasks.taskStatuses.size());
        assertEquals(3, Task.Tasks.taskIDs.size());
        assertEquals(24, Task.Tasks.returnTotalHours());
    }

    @Test
    public void testDisplayLongestTask() {
        int maxDuration = 0;
        int index = -1;
        for (int i = 0; i < Task.Tasks.taskDurations.size(); i++) {
            if (Task.Tasks.taskDurations.get(i) > maxDuration) {
                maxDuration = Task.Tasks.taskDurations.get(i);
                index = i;
            }
        }
        assertEquals(11, (int) Task.Tasks.taskDurations.get(index));
        assertEquals("Glenda Oberholzer", Task.Tasks.developers.get(index));
    }

    @Test
    public void testDisplayDoneTasks() {
        ArrayList<String> doneTasks = new ArrayList<>();
        for (int i = 0; i < Task.Tasks.taskStatuses.size(); i++) {
            if ("Done".equals(Task.Tasks.taskStatuses.get(i))) {
                doneTasks.add(Task.Tasks.developers.get(i) + ", " +
                              Task.Tasks.taskNames.get(i) + ", " +
                              Task.Tasks.taskDurations.get(i));
            }
        }
        assertEquals(1, doneTasks.size());
        assertEquals("Samantha Paulson, Create Reports, 2", doneTasks.get(0));
    }

    @Test
    public void testSearchTaskByName() {
        String taskName = "Create Login";
        int index = Task.Tasks.taskNames.indexOf(taskName);
        assertTrue(index != -1);
        String result = Task.Tasks.taskNames.get(index) + ", " + 
                        Task.Tasks.developers.get(index) + ", " + 
                        Task.Tasks.taskStatuses.get(index);
        String expResult = "Create Login, Mike Smith, To Do";
        assertEquals(expResult, result);
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developer = "Edward Harrison";
        ArrayList<String> tasks = new ArrayList<>();
        for (int i = 0; i < Task.Tasks.developers.size(); i++) {
            if (developer.equals(Task.Tasks.developers.get(i))) {
                tasks.add(Task.Tasks.taskNames.get(i) + ", " + Task.Tasks.taskStatuses.get(i));
            }
        }
        assertEquals(1, tasks.size());
        assertEquals("Create Add Features, Doing", tasks.get(0));
    }

    @Test
    public void testDisplayTaskReport() {
        StringBuilder details = new StringBuilder("Task Details:\n");
        for (int i = 0; i < Task.Tasks.taskNames.size(); i++) {
            details.append("Task Name: ").append(Task.Tasks.taskNames.get(i))
                   .append("\nDeveloper: ").append(Task.Tasks.developers.get(i))
                   .append("\nTask ID: ").append(Task.Tasks.taskIDs.get(i))
                   .append("\nDuration: ").append(Task.Tasks.taskDurations.get(i)).append(" hours\n")
                   .append("Status: ").append(Task.Tasks.taskStatuses.get(i)).append("\n\n");
        }
        String expResult = "Task Details:\n" +
                           "Task Name: Create Login\nDeveloper: Mike Smith\nTask ID: CR:1:ITH\nDuration: 5 hours\nStatus: To Do\n\n" +
                           "Task Name: Create Add Features\nDeveloper: Edward Harrison\nTask ID: CR:2:SON\nDuration: 8 hours\nStatus: Doing\n\n" +
                           "Task Name: Create Reports\nDeveloper: Samantha Paulson\nTask ID: CR:3:LON\nDuration: 2 hours\nStatus: Done\n\n" +
                           "Task Name: Add Arrays\nDeveloper: Glenda Oberholzer\nTask ID: AD:4:ZER\nDuration: 11 hours\nStatus: To Do\n\n";
        assertEquals(expResult, details.toString());
    }
}
