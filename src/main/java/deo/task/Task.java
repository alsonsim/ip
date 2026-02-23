package task;

/**
 * Abstract base class representing a task with a description and completion
 * status.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description, initially marked as not
     * done.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "X" if done, " " otherwise.
     */
    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the task is marked as done.
     *
     * @return True if done.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + statusIcon() + "] " + description;
    }
}
