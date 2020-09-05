package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeFileNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Takes care of file related operation.
 */
public class Storage {

    private File file;

    /**
     * Creates a new instance of Storage.
     * Creates a file directory if it is missing.
     * Creates a file if it does not exist.
     *
     * @param filePath Path of the file.
     */
    public Storage(String filePath) {
        try {
            Path directory = Paths.get(filePath).getParent();
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
                assert Files.exists(directory);
            }
            file = new File(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the tasks in a file and convert them into an ArrayList.
     *
     * @return ArrayList of Task.
     * @throws DukeFileNotFoundException If file is not found.
     */
    public ArrayList<Task> load() throws DukeFileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                switch (task[0].trim()) {
                case "T":
                    ToDo todo = new ToDo(task[2].trim());
                    if (task[1].equals("1")) {
                        todo.doneTask();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(task[2].trim(), task[3].trim());
                    if (task[1].equals("1")) {
                        deadline.doneTask();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(task[2].trim(), task[3].trim());
                    if (task[1].equals("1")) {
                        event.doneTask();
                    }
                    taskList.add(event);
                    break;
                default:
                    assert false : "Unknown format of Data";
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException();
        }
        return taskList;
    }

    /**
     * Saves the TaskList to a file.
     *
     * @param taskList ArrayList of Task.
     */
    public void save(ArrayList<Task> taskList) {
        try {
            StringBuilder content = new StringBuilder();
            FileWriter fw = new FileWriter(file.getPath());
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    String taskDetails = String.format("T | %d | %s", task.isTaskDone() ? 1 : 0, task.getDescription());
                    content.append(taskDetails).append("\n");
                } else if (task instanceof Deadline) {
                    String taskDetails = String.format("T | %d | %s |%s",
                            task.isTaskDone() ? 1 : 0, task.getDescription(), ((Deadline) task).getDate());
                    content.append(taskDetails).append("\n");
                } else {
                    String taskDetails = String.format("T | %d | %s |%s",
                            task.isTaskDone() ? 1 : 0, task.getDescription(), ((Event) task).getAt());
                    content.append(taskDetails).append("\n");
                }
            }
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
