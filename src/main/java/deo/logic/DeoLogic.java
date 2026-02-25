package logic;

import java.util.ArrayList;

import exception.DeoException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

/**
 * Contains the core business logic for handling Deo chatbot commands.
 */
public class DeoLogic {
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_FIND = "find";

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a DeoLogic instance with the required dependencies.
     *
     * @param tasks The task list to operate on.
     * @param storage The storage handler for saving tasks.
     * @param ui The UI handler for displaying output.
     */
    public DeoLogic(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the command represented by the parsed token array.
     *
     * @param parsed Token array where index 0 is the command keyword.
     * @throws DeoException If the command is invalid or arguments are
     * malformed.
     */
    public void handleCommand(String[] parsed) throws DeoException {
        switch (parsed[0]) {
        case CMD_LIST:
            listTasks();
            break;
        case CMD_MARK:
            markTask(parsed[1]);
            break;
        case CMD_UNMARK:
            unmarkTask(parsed[1]);
            break;
        case CMD_DELETE:
            deleteTask(parsed[1]);
            break;
        case CMD_TODO:
            addTodo(parsed[1]);
            break;
        case CMD_DEADLINE:
            addDeadline(parsed[1]);
            break;
        case CMD_EVENT:
            addEvent(parsed[1]);
            break;
        case CMD_FIND:
            findTasks(parsed[1]);
            break;
        default:
            throw new DeoException("I'm too stupid to understand what you mean");
        }
    }

    private void findTasks(String keyword) {
        ArrayList<Task> results = tasks.find(keyword);
        if (results.isEmpty()) {
            ui.showMessage("No matching tasks found.");
            return;
        }
        ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            ui.showMessage((i + 1) + "." + results.get(i));
        }
    }

    private void addTodo(String desc) throws DeoException {
        if (desc.isEmpty()) {
            throw new DeoException("The name cannot be empty dumb ahhhh");
        }
        addTask(new Todo(desc));
    }

    private void addDeadline(String deadlineArgs) throws DeoException {
        int split = deadlineArgs.indexOf(" /by ");
        if (split <= 0) {
            throw new DeoException("dummy Use: deadline DESCRIPTION /by BY");
        }
        String desc = deadlineArgs.substring(0, split).trim();
        String by = deadlineArgs.substring(split + 5).trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new DeoException("dummy Use: deadline DESCRIPTION /by BY");
        }
        try {
            addTask(new Deadline(desc, by));
        } catch (Exception e) {
            throw new DeoException(e.getMessage());
        }
    }

    private void addEvent(String eventArgs) throws DeoException {
        int fromIdx = eventArgs.indexOf(" /from ");
        int toIdx = eventArgs.indexOf(" /to ");
        if (fromIdx <= 0 || toIdx <= 0 || toIdx <= fromIdx) {
            throw new DeoException("dumb ahh Use: event DESCRIPTION /from FROM /to TO");
        }
        String desc = eventArgs.substring(0, fromIdx).trim();
        String from = eventArgs.substring(fromIdx + 7, toIdx).trim();
        String to = eventArgs.substring(toIdx + 5).trim();
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

    private void markTask(String taskNumberStr) throws DeoException {
        int index = parseIndex(taskNumberStr);
        tasks.get(index).markAsDone();
        storage.save(tasks.getAll());
        ui.showMessage("Nice! I've marked this task as done:", "  " + tasks.get(index));
    }

    private void unmarkTask(String taskNumberStr) throws DeoException {
        int index = parseIndex(taskNumberStr);
        tasks.get(index).markAsUndone();
        storage.save(tasks.getAll());
        ui.showMessage("OK, I've marked this task as not done yet:", "  " + tasks.get(index));
    }

    private void deleteTask(String taskNumberStr) throws DeoException {
        int index = parseIndex(taskNumberStr);
        Task removed = tasks.remove(index);
        storage.save(tasks.getAll());
        ui.showMessage("Noted. I've removed this task:", "  " + removed,
                                        "Now you have " + tasks.size() + " tasks in the list.");
    }

    private int parseIndex(String taskNumberStr) throws DeoException {
        try {
            int idx = Integer.parseInt(taskNumberStr.trim()) - 1;
            if (idx < 0 || idx >= tasks.size()) {
                throw new DeoException("dumb ahh Please give a valid task number.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new DeoException("dumb ahh Please give a valid task number.");
        }
    }
}
