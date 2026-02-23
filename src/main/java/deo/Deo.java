import exception.DeoException;
import logic.DeoLogic;
import logic.Parser;
import logic.Storage;
import logic.TaskList;
import ui.Ui;

/**
 * Main class for the Deo chatbot application.
 */
public class Deo {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Deo instance with the given file path for storage.
     *
     * @param filePath Path to the save file.
     */
    public Deo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    /**
     * Runs the main loop of the chatbot, reading and executing commands until
     * exit.
     */
    public void run() {
        ui.showWelcome();
        DeoLogic logic = new DeoLogic(tasks, storage, ui);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                String[] parsed = Parser.parse(fullCommand);
                if (parsed[0].equals("bye")) {
                    ui.showBye();
                    isExit = true;
                } else {
                    logic.handleCommand(parsed);
                }
            } catch (DeoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Deo("data" + java.io.File.separator + "deo.txt").run();
    }
}
