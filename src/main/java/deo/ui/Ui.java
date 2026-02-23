package ui;

import java.util.Scanner;

/**
 * Handles all user-facing input and output for the Deo chatbot.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String ASCII_ART = "___________   _______________________________________^__\n"
                                    + " ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\\\n"
                                    + "|   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____\\\n"
                                    + "|___| |___|||| |___| |___| |___|  | O | O | |  |  |        \\\n"
                                    + "           |||                    |___|___| |  |__|         )\n"
                                    + "___________|||______________________________|______________/\n"
                                    + "           |||     I'd rather be at DisneyWorld...    /--------\n"
                                    + "-----------'''---------------------------------------'\n";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message and ASCII art on startup.
     */
    public void showWelcome() {
        System.out.println(ASCII_ART);
        showLine();
        System.out.println(" Hello! I'm Deo");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Prints a horizontal divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a full line of input from the user.
     *
     * @return The trimmed input string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays one or more messages to the user, each prefixed with a space.
     *
     * @param lines The lines of text to display.
     */
    public void showMessage(String... lines) {
        for (String l : lines) {
            System.out.println(" " + l);
        }
    }

    /**
     * Displays an error message prefixed with "NO".
     *
     * @param msg The error message to display.
     */
    public void showError(String msg) {
        System.out.println(" NO " + msg);
    }

    /**
     * Displays a warning that the save file could not be loaded.
     */
    public void showLoadingError() {
        System.out.println(" Warning: could not read save file. Starting fresh.");
    }

    /**
     * Displays the goodbye message.
     */
    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
