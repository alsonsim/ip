package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final LocalDate by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The task description.
     * @param by The due date in yyyy-MM-dd format.
     * @throws Exception If the date format is invalid.
     */
    public Deadline(String description, String by) throws Exception {
        super(description);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new Exception("Date must be in yyyy-MM-dd format e.g. 2019-12-01");
        }
    }

    /**
     * Returns the due date formatted as MMM dd yyyy for display.
     *
     * @return Formatted due date string.
     */
    public String getBy() {
        return by.format(OUTPUT_FORMAT);
    }

    /**
     * Returns the due date in yyyy-MM-dd format for file storage.
     *
     * @return Raw due date string.
     */
    public String getByRaw() {
        return by.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
