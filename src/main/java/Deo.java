import java.util.Scanner;

public class Deo {
    private static final DeoLogic logic = new DeoLogic();

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
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;
    private boolean exit = false;

    boolean isExit() {
        return exit;
    }

    void handleCommand(String input) {
        if (input.equals("list")) {
            listTasks();
            return;
        }
        if (input.startsWith("mark ")) {
            markTask(input.substring(5));
            return;
        }
        if (input.startsWith("unmark ")) {
            unmarkTask(input.substring(7));
            return;
        }
        if (input.equals("bye")) {
            exit = true;
            return;
        }

        if (input.startsWith("todo ")) {
            addTodo(input.substring(5).trim());
            return;
        }
        if (input.startsWith("deadline ")) {
            addDeadline(input.substring(9).trim());
            return;
        }
        if (input.startsWith("event ")) {
            addEvent(input.substring(6).trim());
            return;
        }

        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void addTodo(String desc) {
        if (desc.isEmpty()) {
            System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        addTask(new Todo(desc));
    }

    private void addDeadline(String rest) {
        int split = rest.indexOf(" /by ");
        if (split <= 0) {
            System.out.println(" ☹ OOPS!!! Use: deadline DESCRIPTION /by BY");
            return;
        }
        String desc = rest.substring(0, split).trim();
        String by = rest.substring(split + 5).trim();
        if (desc.isEmpty() || by.isEmpty()) {
            System.out.println(" ☹ OOPS!!! Use: deadline DESCRIPTION /by BY");
            return;
        }
        addTask(new Deadline(desc, by));
    }

    private void addEvent(String rest) {
        int fromIdx = rest.indexOf(" /from ");
        int toIdx = rest.indexOf(" /to ");
        if (fromIdx <= 0 || toIdx <= 0 || toIdx <= fromIdx) {
            System.out.println(" ☹ OOPS!!! Use: event DESCRIPTION /from FROM /to TO");
            return;
        }
        String desc = rest.substring(0, fromIdx).trim();
        String from = rest.substring(fromIdx + 7, toIdx).trim();
        String to = rest.substring(toIdx + 5).trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(" ☹ OOPS!!! Use: event DESCRIPTION /from FROM /to TO");
            return;
        }
        addTask(new Event(desc, from, to));
    }

    private void addTask(Task t) {
        if (taskCount >= tasks.length) {
            System.out.println(" ☹ OOPS!!! Task list is full.");
            return;
        }
        tasks[taskCount++] = t;
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    private void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        if (taskCount == 0) {
            System.out.println(" No tasks");
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
    }

    private void markTask(String numStr) {
        int index = parseIndex(numStr);
        if (index == -1) {
            System.out.println(" ☹ OOPS!!! Please give a valid task number.");
            return;
        }
        tasks[index].markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index]);
    }

    private void unmarkTask(String numStr) {
        int index = parseIndex(numStr);
        if (index == -1) {
            System.out.println(" ☹ OOPS!!! Please give a valid task number.");
            return;
        }
        tasks[index].markAsUndone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index]);
    }

    private int parseIndex(String numStr) {
        try {
            int idx = Integer.parseInt(numStr.trim()) - 1;
            if (idx < 0 || idx >= taskCount) {
                return -1;
            }
            return idx;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

abstract class Task {
    protected final String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    void markAsDone() {
        isDone = true;
    }

    void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + statusIcon() + "] " + description;
    }
}

class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
