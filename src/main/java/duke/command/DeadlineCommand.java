package main.java.duke.command;

import main.java.duke.*;
import main.java.duke.task.Deadline;

public class DeadlineCommand extends Command {

    String[] commandDetails;

    public DeadlineCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String day = commandDetails[1].split(" ", 2)[1];
        System.out.println(" Got it. I've added this task: ");
        Deadline deadline = new Deadline(commandDetails[0], day.trim());
        tasks.getTasks().add(deadline);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. ",
                deadline, tasks.getTasks().size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
