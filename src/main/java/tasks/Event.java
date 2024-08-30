package tasks;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the Event task.
 * A <code>Event</code> task has a name, from date, to date and a flag to show if it is done
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructor for an Event task by taking in a string with the relevant information.
     * It includes the name, from date and to date of the Event task.
     *
     * @param inputString The string containing information of the task
     * @throws InvalidTaskNameException If no name is provided.
     * @throws InvalidDateException If invalid date is provided.
     *
     */
    public Event (String inputString) throws InvalidTaskNameException, InvalidDateException {

        if (inputString.contains("/from ")) {
            int fromIndex = inputString.indexOf("/from ");
            String taskName = inputString.substring(0, fromIndex).trim();
            if (taskName.length() == 0) {
                throw new InvalidTaskNameException();
            }

            if (inputString.contains("/to ")) {
                int toIndex = inputString.indexOf("/to ");
                if (toIndex < fromIndex) {
                    throw new InvalidDateException("Wrong date order provided!");
                }
                String fromDate = inputString.substring(fromIndex + 6, toIndex);
                String toDate = inputString.substring(toIndex + 4);
                if (fromDate.length() == 0) {
                    throw new InvalidDateException("From date not provided");
                } else if (toDate.length() == 0) {
                    throw new InvalidDateException("To date not provided");
                }
                this.name = taskName;
                try {
                    this.fromDate = LocalDate.parse(fromDate.trim());
                } catch (DateTimeParseException ex) {
                    throw new InvalidDateException("Invalid from date format given");
                }

                try {
                    this.toDate = LocalDate.parse(toDate.trim());
                } catch (DateTimeParseException ex) {
                    throw new InvalidDateException("Invalid to date format given");
                }

                if (this.toDate.isBefore(this.fromDate)) {
                    throw new InvalidDateException("To date is before from date");
                }
            } else {
                throw new InvalidDateException("To date is not provided!");
            }
        } else {
            throw new InvalidDateException("From date is not provided!");
        }
    }

    /**
     * Another Constructor for an Event task.
     * This constructor takes in an array of strings after they have been split.
     *
     * @param input The array of strings, each string contains a field of the Event task
     */
    public Event(String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1].trim();
        this.fromDate = LocalDate.parse(input[2].trim());
        this.toDate = LocalDate.parse(input[3].trim());
    }

    /**
     * Returns the string representation an Event task.
     * Contains the type, name, from date and to date of the Event task, as well as whether it is done.
     *
     * @return string representation of the Event task.
     */
    @Override
    public String toString () {
        String res = "[E]";
        res += super.toString();
        res += " (from: " + this.fromDate.toString() + " to: " + this.toDate.toString() + ")";
        return res;
    }

    /**
     * Returns the string representation of how an Event task should be saved.
     * Contains the type, name, from date and to date of the Event task, as well as whether it is done.
     *
     * @return save format of Event task.
     */
    @Override
    public String toSave () {
        String res = "E|";
        res = res.concat(this.isDone ? "1|" : "0|");
        res = res.concat(this.name);
        res = res.concat("|");
        res = res.concat(this.fromDate.toString());
        res = res.concat("|");
        res = res.concat(this.toDate.toString());
        return res;
    }
}
