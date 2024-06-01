package org.todolist;

import java.util.*;

class Task {
    private String title;
    private String description;
    private Date dueDate;
    private int priority;

    public Task(String title, String description, Date dueDate, int priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                '}';
    }
}

class ToDoList {
    private Map<String, List<Task>> taskLists;

    public ToDoList() {
        this.taskLists = new HashMap<>();
    }

    public void addTask(String category, Task task) {
        if (!taskLists.containsKey(category)) {
            taskLists.put(category, new ArrayList<>());
        }
        taskLists.get(category).add(task);
    }

    public void deleteTask(String category, int index) {
        if (taskLists.containsKey(category)) {
            List<Task> tasks = taskLists.get(category);
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
            }
        }
    }

    public void updateTask(String category, int index, Task newTask) {
        if (taskLists.containsKey(category)) {
            List<Task> tasks = taskLists.get(category);
            if (index >= 0 && index < tasks.size()) {
                tasks.set(index, newTask);
            }
        }
    }

    public void viewTasks() {
        for (Map.Entry<String, List<Task>> entry : taskLists.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println(i + ". " + entry.getValue().get(i));
            }
            System.out.println();
        }
    }
}

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date (yyyy-mm-dd): ");
                    String dueDateStr = scanner.nextLine();
                    Date dueDate = java.sql.Date.valueOf(dueDateStr);
                    System.out.print("Enter priority: ");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Task newTask = new Task(title, description, dueDate, priority);
                    toDoList.addTask(category, newTask);
                    break;
                case 2:
                    System.out.print("Enter category: ");
                    String updateCategory = scanner.nextLine();
                    System.out.print("Enter task index: ");
                    int updateIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter updated title: ");
                    String updatedTitle = scanner.nextLine();
                    System.out.print("Enter updated description: ");
                    String updatedDescription = scanner.nextLine();
                    System.out.print("Enter updated due date (yyyy-mm-dd): ");
                    String updatedDueDateStr = scanner.nextLine();
                    Date updatedDueDate = java.sql.Date.valueOf(updatedDueDateStr);
                    System.out.print("Enter updated priority: ");
                    int updatedPriority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Task updatedTask = new Task(updatedTitle, updatedDescription, updatedDueDate, updatedPriority);
                    toDoList.updateTask(updateCategory, updateIndex, updatedTask);
                    break;
                case 3:
                    System.out.print("Enter category: ");
                    String deleteCategory = scanner.nextLine();
                    System.out.print("Enter task index: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    toDoList.deleteTask(deleteCategory, deleteIndex);
                    break;
                case 4:
                    toDoList.viewTasks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

