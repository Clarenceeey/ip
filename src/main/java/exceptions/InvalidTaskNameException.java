package exceptions;

/**
 * Exception that indicates an error with the name provided to the tasks.
 *
 */
public class InvalidTaskNameException extends Exception {

    /**
     * Takes in a name, allowing for the customisation of error messages.
     * Informs the user that the name cannot be used.
     *
     * @param name The invalid name that was provided.
     */
    public InvalidTaskNameException (String name) {
        super("Error: The name " + name + " is invalid!");
    }

    /**
     * Default error message to show users when no name is provided to the tasks
     */
    public InvalidTaskNameException () {
        super("Error: Please provide a name!");
    }
}
