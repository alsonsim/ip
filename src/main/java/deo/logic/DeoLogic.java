package logic;

import exception.DeoException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class DeoLogic {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;
    private boolean exit = false;

    public boolean isExit() {
        return exit;
    }

    public void handleCommand(String input) {
        try {
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

            throw new DeoException("I'm too stupid to understand what you mean");

        } catch (DeoException e) {
            System.out.println(" NO " + e.getMessage());
        }
    }

    private void addTodo(String desc) throws DeoException {
        if (desc.isEmpty()) {
            throw new DeoException("The name cannot be empty dumb ahhhh");
        }
        addTask(new Todo(desc));
    }

    private void addDeadline(String rest) throws DeoException {
        int split = rest.indexOf(" /by ");
        if (split <= 0) {
            throw new DeoException("dummy Use: deadline DESCRIPTION /by BY");
        }
        String desc = rest.substring(0, split).trim();
        String by = rest.substring(split + 5).trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new DeoException("dummy Use: deadline DESCRIPTION /by BY");
        }
        addTask(new Deadline(desc, by));
    }

    private void addEvent(String rest) throws DeoException {
        int fromIdx = rest.indexOf(" /from ");
        int toIdx = rest.indexOf(" /to ");
        if (fromIdx <= 0 || toIdx <= 0 || toIdx <= fromIdx) {
            throw new DeoException("dumb ahh Use: event DESCRIPTION /from FROM /to TO");
        }
        String desc = rest.substring(0, fromIdx).trim();
        String from = rest.substring(fromIdx + 7, toIdx).trim();
        String to = rest.substring(toIdx + 5).trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DeoException("dumb ahh Use: event DESCRIPTION /from FROM /to TO");
        }
        addTask(new Event(desc, from, to));
    }

    private void addTask(Task t) throws DeoException {
        if (taskCount >= tasks.length) {
            throw new DeoException("dumb ahh Task list is full.");
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

    private void markTask(String numStr) throws DeoException {
        int index = parseIndex(numStr);
        if (index == -1) {
            throw new DeoException("dumb ahh Please give a valid task number.");
        }
        tasks[index].markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index]);
    }

    private void unmarkTask(String numStr) throws DeoException {
        int index = parseIndex(numStr);
        if (index == -1) {
            throw new DeoException("dumb ahh Please give a valid task number.");
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
