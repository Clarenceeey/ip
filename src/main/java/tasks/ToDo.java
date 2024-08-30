package tasks;

import exceptions.*;

import static java.lang.Integer.parseInt;

/**
 * Represents the ToDo task.
 * A <code>ToDo</code> task only has a name and a flag to show if it is done
 */
public class ToDo extends Task{

    /**
     * Constructor for a ToDo task by taking in a string with the relevant information
     *
     * @param inputStr The string containing information of the task
     * @throws InvalidTaskNameException If no name is provided.
     */
    public ToDo (String inputStr) throws InvalidTaskNameException {
        String name = inputStr;
        if (name.length() == 0) {
            throw new InvalidTaskNameException();
        }
        this.name = name;
    }

    /**
     * Another Constructor for a ToDo task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the ToDo task
     */
    public ToDo (String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1];
    }


    /**
     * Returns the string representation a ToDo task.
     * Contains the type and name of the ToDo task, as well as whether it is done.
     *
     * @return string representation of the ToDo task.
     */
    @Override
    public String toString () {
        String res = "[T]";
        res += super.toString();
        return res;
    }

    /**
     * Returns the string representation of how a ToDo task should be saved.
     * Contains the type and name of the ToDo task, as well as whether it is done.
     *
     * @return save format of ToDo task.
     */
    @Override
    public String toSave () {
        String res = "T|";
        res = res.concat(this.isDone ? "1|" : "0|");
        res = res.concat(this.name);
        return res;
    }
}
