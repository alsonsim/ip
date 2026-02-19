import java.util.Scanner;

import logic.DeoLogic;

public class Deo {
    private static final DeoLogic logic = new DeoLogic();

    private static final String ASCII_ART = "___________   _______________________________________^__\n"
                                    + " ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\\\n"
                                    + "|   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____\\\n"
                                    + "|___| |___|||| |___| |___| |___|  | O | O | |  |  |        \\\n"
                                    + "           |||                    |___|___| |  |__|         )\n"
                                    + "___________|||______________________________|______________/\n"
                                    + "           |||     I'd rather be at DisneyWorld...    /--------\n"
                                    + "-----------'''---------------------------------------'\n";

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(ASCII_ART);
        System.out.println(line);
        System.out.println(" Hello! I'm Deo");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine().trim();
                System.out.println(line);
                logic.handleCommand(input);
                if (logic.isExit()) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                }
                System.out.println(line);
            }
        }
    }
}
