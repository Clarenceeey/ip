package processes;

import java.util.Scanner;
import java.util.ArrayList;
import tasks.Task;

/**
 * The task that deals with user inputs.
 * Formats the input for use in other classes, such as the TaskList class.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor for Ui object.
     * Creates new scanner to take in user input
     */
    public Ui () {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a line onto the terminal to separate lines of output text from the programme.
     */
    public static void printLine () {
        int length = 75;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
            if (i == length - 1) {
                System.out.println();
            }
        }
    }

    /**
     * Called when the programme just starts.
     * Prints the welcome message onto the terminal to greet the user.
     */
    public void showWelcomeMessage (String name) {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        printLine();
    }

    /**
     * Called when the programme is terminated.
     * Prints the goodbye message onto the terminal for the user.
     */
    public void showGoodbyeMessage () {
        System.out.println("Bye. Hope to see you again soon! \n");
        printLine();
    }

    /**
     * Takes in the user's input as one whole line, the pass it to other objects.
     *
     * @return The string of the user input.
     */
    public String readCommand () {
        return scanner.nextLine();
    }

    /**
     * Prints the list of tasks on the terminal for the user.
     *
     * @param taskList The current list of tasks
     */
    public void showTaskList (ArrayList<Task> taskList) {
        System.out.println("Your current tasks are: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    /**
     * Takes in a message and prints it onto the terminal, useful for showing exception messages.
     *
     * @param message The message that should be printed onto the terminal
     */
    public void showMessage (String message) {
        System.out.println(message);
        printLine();
    }

    /**
     * Called when the programme terminates.
     * No longer need to take in user input, can close the scanner
     */
    public void close () {
        scanner.close();
    }
}