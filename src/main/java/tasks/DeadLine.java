package tasks;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

/**
 * Represents the DeadLine task.
 * A <code>DeadLine</code> task has a name, end date and a flag to show if it is done
 */
public class DeadLine extends Task {

    protected LocalDate endDate;

    /**
     * Constructor for a DeadLine task by taking in a string with the relevant information.
     * It includes the name and end date of the DeadLine task.
     *
     * @param inputString The string containing information of the task
     * @throws InvalidTaskNameException If no name is provided.
     * @throws InvalidDateException If invalid date is provided.
     *
     */
    public DeadLine (String inputString) throws InvalidTaskNameException, InvalidDateException {
        inputString = inputString.trim();
        if (inputString.contains("/by ")) {
            int index = inputString.indexOf("/by ");
            String taskName = inputString.substring(0, index).trim();
            String byDate = inputString.substring(index + 4);
            if (taskName.length() == 0) {
                throw new InvalidTaskNameException();
            }

            if (byDate.length() == 0) {
                throw new InvalidDateException();
            }

            this.name = taskName;
            try {
                this.endDate = LocalDate.parse(byDate);
            } catch (DateTimeParseException ex) {
                throw new InvalidDateException("Invalid date format given");
            }

        } else {
            throw new InvalidDateException();
        }
    }

    /**
     * Another Constructor for a DeadLine task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the DeadLine task
     */
    public DeadLine (String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1].trim();
        this.endDate = LocalDate.parse(input[2].trim());
    }

    /**
     * Returns the string representation a DeadLine task.
     * Contains the type, name and end date of the DeadLine task, as well as whether it is done.
     *
     * @return string representation of the DeadLine task.
     */
    @Override
    public String toString () {
        String res = "[D]";
        res += super.toString();
        res += " (by: " + this.endDate.toString() + ")";
        return res;
    }

    /**
     * Returns the string representation of how a DeadLine task should be saved.
     * Contains the type, name and end date of the DeadLine task, as well as whether it is done.
     *
     * @return save format of DeadLine task.
     */
    @Override
    public String toSave () {
        String res = "D|";
        res = res.concat(this.isDone ? "1|" : "0|");
        res = res.concat(this.name);
        res = res.concat("|");
        res = res.concat(this.endDate.toString());
        return res;
    }
}
