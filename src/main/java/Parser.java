package main.java;

public class Parser {

    enum Input {
        LIST, BYE, DONE, TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] command = fullCommand.split(" ", 2);
        if (inputExist(command[0])) {
            switch (Input.valueOf(command[0].toUpperCase())) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ByeCommand();
            case DONE:
                return parseDone(command);
            case TODO:
                return parseToDo(command);
            case DEADLINE:
                return parseDeadline(command);
            case EVENT:
                return parseEvent(command);
            case DELETE:
                return parseDelete(command);
            }
        } else {
            throw new DukeException("~\n ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN \n~");
        }
        return null;
    }

    private static boolean inputExist(String input) {
        for (Input i : Input.values()) {
            if (input.toUpperCase().equals(i.toString())) {
                return true;
            }
        }
        return false;
    }

    public static DoneCommand parseDone(String[] commandDetails) throws DukeException {
        if (commandDetails.length > 1 && Character.isDigit(commandDetails[1].charAt(0))) {
            return new DoneCommand(commandDetails);
        } else {
            throw new DukeException("~\n ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN \n~");
        }
    }

    public static ToDoCommand parseToDo(String[] commandDetails) throws DukeException {
            if (commandDetails.length > 1) {
                return new ToDoCommand(commandDetails);
            } else {
                throw new DukeException("~\n ERROR... TODO DESCRIPTION EMPTY. \n PLEASE TRY AGAIN \n~");
            }
    }

    public static DeadlineCommand parseDeadline(String[] commandDetails) throws DukeException {
        if (commandDetails.length > 1) {
            String[] stringArray = commandDetails[1].split("/", 2);
            if (stringArray.length > 1 && stringArray[1].split(" ", 2).length > 1) {
                return new DeadlineCommand(stringArray);
            } else {
                throw new DukeException("~\n ERROR... DEADLINE DATE EMPTY. \n PLEASE TRY AGAIN \n~");
            }
        } else {
            throw new DukeException("~\n ERROR... DEADLINE DESCRIPTION EMPTY . \n PLEASE TRY AGAIN \n~");
        }
    }

    public static EventCommand parseEvent(String[] commandDetails) throws DukeException {
        if (commandDetails.length > 1) {
            String[] stringArray = commandDetails[1].split("/", 2);
            if (stringArray.length > 1 && stringArray[1].split(" ", 2).length > 1) {
                return new EventCommand(stringArray);
            } else {
                throw new DukeException("~\n ERROR... EVENT DATE EMPTY. \n PLEASE TRY AGAIN \n~");
            }
        } else {
            throw new DukeException("~\n ERROR... EVENT DESCRIPTION EMPTY. \n PLEASE TRY AGAIN \n~");
        }
    }

    public static DeleteCommand parseDelete(String[] commandDetails) throws DukeException {
        if (commandDetails.length > 1 && Character.isDigit(commandDetails[1].charAt(0))) {
            return new DeleteCommand(commandDetails);
        } else {
            throw new DukeException("~\n ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN \n~");
        }
    }
}
