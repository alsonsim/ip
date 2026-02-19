package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks; // first run, no file yet
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task t = parseLine(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println(" Warning: could not read save file.");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                for (Task t : tasks) {
                    bw.write(encode(t));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(" Warning: could not save tasks.");
        }
    }

    private String encode(Task t) {
        if (t instanceof Todo) {
            return "T | " + (t.isDone() ? "1" : "0") + " | " + t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + (d.isDone() ? "1" : "0") + " | " + d.getDescription() + " | " + d.getBy();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + (e.isDone() ? "1" : "0") + " | " + e.getDescription() + " | " + e.getFrom() + " | "
                                            + e.getTo();
        }
        return "";
    }

    private Task parseLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0].trim();
            boolean done = parts[1].trim().equals("1");
            switch (type) {
            case "T":
                Todo todo = new Todo(parts[2].trim());
                if (done)
                    todo.markAsDone();
                return todo;
            case "D":
                Deadline d = new Deadline(parts[2].trim(), parts[3].trim());
                if (done)
                    d.markAsDone();
                return d;
            case "E":
                Event e = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                if (done)
                    e.markAsDone();
                return e;
            default:
                return null; // corrupted line — skip it
            }
        } catch (Exception e) {
            return null; // corrupted line — skip it
        }
    }
}
