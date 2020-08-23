package main.java.duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void doneTask() {
        this.isDone = true;
    }

    public void undoTask() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean checkDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
