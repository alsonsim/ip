package logic;

import java.util.ArrayList;

import task.Task;

/**
 * Manages the in-memory list of tasks and provides operations to manipulate it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList from an existing list of tasks.
     *
     * @param tasks The existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i Zero-based index of the task.
     * @return The task at index i.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param i Zero-based index of the task.
     * @return The removed task.
     */
    public Task remove(int i) {
        return tasks.remove(i);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if the task list is empty.
     *
     * @return True if no tasks exist.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return All tasks as an ArrayList.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Returns all tasks whose descriptions contain the given keyword
     * (case-insensitive).
     *
     * @param keyword The search keyword.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(t);
            }
        }
        return results;
    }
}
