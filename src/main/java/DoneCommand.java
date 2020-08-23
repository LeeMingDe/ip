package main.java;

public class DoneCommand extends Command {

    String[] commandDetails;

    public DoneCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.getTasks().isEmpty() && taskNumber < tasks.getTasks().size()) {
            tasks.getTasks().get(taskNumber).doneTask();
            System.out.println(" Nice! Target Eliminated: \n   "
                    + tasks.getTasks().get(taskNumber).toString());
        } else {
            throw new DukeException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}