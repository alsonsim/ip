package exception;

/**
 * Represents exceptions specific to the Deo chatbot application.
 */
public class DeoException extends Exception {

    /**
     * Constructs a DeoException with the given error message.
     *
     * @param message The error message.
     */
    public DeoException(String message) {
        super(message);
    }
}
