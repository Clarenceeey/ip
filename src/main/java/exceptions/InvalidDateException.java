package exceptions;

/**
 * Exception that indicates an error with the dates provided to the tasks.
 *
 */
public class InvalidDateException extends Exception{

    /**
     * Default error message to show users when no message is provided
     */
    public InvalidDateException () {
        super("Error: No date provided");
    }

    /**
    * Takes in a message, allowing for the customisation of error messages.
     * Message determines how the date is invalid and tells the user.
     *
     * @param message The message that explains why the date is invalid.
    */
    public InvalidDateException (String message) {
        super("Error: " + message);
    }
}
