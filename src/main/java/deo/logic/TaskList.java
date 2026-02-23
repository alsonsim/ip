package logic;

import java.util.ArrayList;

import task.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

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
