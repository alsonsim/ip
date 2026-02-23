package deo;

import exception.DeoException;
import logic.DeoLogic;
import logic.Parser;
import logic.Storage;
import logic.TaskList;
import ui.Ui;

public class Deo {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Deo("data" + java.io.File.separator + "deo.txt").run();
    }
}
