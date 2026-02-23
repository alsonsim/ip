package logic;

import exception.DeoException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

public class DeoLogic {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public DeoLogic(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void handleCommand(String[] parsed) throws DeoException {
        switch (parsed[0]) {
        case "list":
            listTasks();
            break;
        case "mark":
            markTask(parsed[1]);
            break;
        case "unmark":
            unmarkTask(parsed[1]);
            break;
        case "delete":
            deleteTask(parsed[1]);
            break;
        case "todo":
            addTodo(parsed[1]);
            break;
        case "deadline":
            addDeadline(parsed[1]);
            break;
        case "event":
            addEvent(parsed[1]);
            break;
        default:
            throw new DeoException("I'm too stupid to understand what you mean");
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
        try {
            addTask(new Deadline(desc, by));
        } catch (Exception e) {
            throw new DeoException(e.getMessage());
        }
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

    private void addTask(Task t) {
        tasks.add(t);
        storage.save(tasks.getAll());
        ui.showMessage("Got it. I've added this task:", "  " + t,
                                        "Now you have " + tasks.size() + " tasks in the list.");
    }

    private void listTasks() {
        ui.showMessage("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            ui.showMessage("No tasks");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i));
        }
    }

    private void markTask(String numStr) throws DeoException {
        int index = parseIndex(numStr);
        tasks.get(index).markAsDone();
        storage.save(tasks.getAll());
        ui.showMessage("Nice! I've marked this task as done:", "  " + tasks.get(index));
    }

    private void unmarkTask(String numStr) throws DeoException {
        int index = parseIndex(numStr);
        tasks.get(index).markAsUndone();
        storage.save(tasks.getAll());
        ui.showMessage("OK, I've marked this task as not done yet:", "  " + tasks.get(index));
    }

    private void deleteTask(String numStr) throws DeoException {
        int index = parseIndex(numStr);
        Task removed = tasks.remove(index);
        storage.save(tasks.getAll());
        ui.showMessage("Noted. I've removed this task:", "  " + removed,
                                        "Now you have " + tasks.size() + " tasks in the list.");
    }

    private int parseIndex(String numStr) throws DeoException {
        try {
            int idx = Integer.parseInt(numStr.trim()) - 1;
            if (idx < 0 || idx >= tasks.size()) {
                throw new DeoException("dumb ahh Please give a valid task number.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new DeoException("dumb ahh Please give a valid task number.");
        }
    }
}
