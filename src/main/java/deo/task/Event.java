package task;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event task with the given description, start, and end
     * times.
     *
     * @param description The task description.
     * @param from The start time.
     * @param to The end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The from string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The to string.
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
