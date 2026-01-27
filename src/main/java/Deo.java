import java.util.Scanner;

public class Deo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(" Hello! I'm Deo");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
            } else {
                System.out.println(" " + input);
            }
            System.out.println(line);
        }

        scanner.close();
    }
}
