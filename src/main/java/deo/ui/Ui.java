package ui;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String ASCII_ART = "___________ _______________________________________^__\n"
                                    + " ___ ___ ||| ___ ___ ___ ___ ___ | __ ,----\\\n"
                                    + "| | | |||| | | | | | | | | | | | | |_____\\\n"
                                    + "|___| |___|||| |___| |___| |___| | O | O | | | | \\\n"
                                    + "           |||                   |___|___| | |__| )\n"
                                    + "___________|||______________________________|______________/\n"
                                    + "           ||| I'd rather be at DisneyWorld... /--------\n"
                                    + "-----------'''---------------------------------------'\n";

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(ASCII_ART);
        showLine();
        System.out.println(" Hello! I'm Deo");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String... lines) {
        for (String l : lines) {
            System.out.println(" " + l);
        }
    }

    public void showError(String msg) {
        System.out.println(" NO " + msg);
    }

    public void showLoadingError() {
        System.out.println(" Warning: could not read save file. Starting fresh.");
    }

    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
