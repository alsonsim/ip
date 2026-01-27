import java.util.Scanner;

public class Deo {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;

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
            if (input.equals("list")) {
                listTasks();
            } else if (!input.equals("bye")) {
                addTask(input);
            } else {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
        }
        scanner.close();
    }

    private static void addTask(String task) {
        if (taskCount < 100) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(" added: " + task);
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println(" No tasks");
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
    }
}
