import java.util.Scanner;

public class Deo {
    private static DeoLogic logic = new DeoLogic();

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(" Hello! I'm Deo");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println(line);
            logic.handleCommand(input);
            if (logic.isExit()) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
        }
        scanner.close();
    }
}

class DeoLogic {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;
    private boolean exit = false;

    void handleCommand(String input) {
        if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("mark ")) {
            markTask(input.substring(5));
        } else if (input.startsWith("unmark ")) {
            unmarkTask(input.substring(7));
        } else if (input.equals("bye")) {
            exit = true;
        } else {
            addTask(input);
        }
    }

    boolean isExit() {
        return exit;
    }

    private void addTask(String task) {
        if (taskCount < 100) {
            tasks[taskCount] = new Task(task);
            taskCount++;
            System.out.println(" added: " + task);
        }
    }

    private void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        if (taskCount == 0) {
            System.out.println(" No tasks");
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toStringList());
        }
    }

    private void markTask(String numStr) {
        try {
            int index = Integer.parseInt(numStr.trim()) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index].toStringStatus());
            }
        } catch (NumberFormatException ignored) {
        }
    }

    private void unmarkTask(String numStr) {
        try {
            int index = Integer.parseInt(numStr.trim()) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].markAsUndone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index].toStringStatus());
            }
        } catch (NumberFormatException ignored) {
        }
    }
}

class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String toStringList() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }

    String toStringStatus() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }

    void markAsDone() {
        isDone = true;
    }

    void markAsUndone() {
        isDone = false;
    }
}
