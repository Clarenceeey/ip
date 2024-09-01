package processes;

import java.util.ArrayList;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;
import exceptions.TaskOutOfBoundsError;
import tasks.Task;


/**
 * The main file of the program. Contains the main method.
 * Run the main method to start the task tracker
 */
public class MrTracker {
    public static final String FILEPATH = "./data/tasks.txt";

    public static final String DIRPATH = "./data";

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;


    /**
     * Constructor for the chatbot class. Loads data into memory and creates necessary objects for the program to run.
     *
     */
    public MrTracker() {
        this.ui = new Ui();
        this.storage = new Storage(DIRPATH, FILEPATH);
        this.parser = new Parser();
        this.taskList = new TaskList();

        storage.loadData(taskList.getTasks());
    }



    /**
     * Function that brings all processes together to make chatbot function.
     * Takes in the user's input and produces the output.
     *
     * @param input The user input.
     * @return The response by the chatbot
     */

    public String getResponse(String input) {

        ui.showTaskList(taskList.getTasks());

        String res;

        PrefixString pref = parser.parseCommand(input);
        if (pref != null) {
            switch (pref) {
            case BYE:
                res = ui.showGoodbyeMessage();
                break;
            case LIST:
                res = ui.showTaskList(taskList.getTasks());
                break;
            case MARK:
                if (parser.checkValidIndex(input, 5)) {
                    int index = parser.checkIndex(input, 5);
                    try {
                        Task markedTask = taskList.markAndUnmark(index, true);
                        res = ui.showMarked(markedTask);
                    } catch (TaskOutOfBoundsError ex) {
                        res = ui.showMessage(ex.getMessage());
                    }
                } else {
                    res = ui.showMessage(input.substring(5) + " is not a valid index");
                }
                break;
            case UNMARK:
                if (parser.checkValidIndex(input, 7)) {
                    int index = parser.checkIndex(input, 7);
                    try {
                        Task unMarkedTask = taskList.markAndUnmark(index, false);
                        res = ui.showUnmarked(unMarkedTask);
                    } catch (TaskOutOfBoundsError ex) {
                        res = ui.showMessage(ex.getMessage());
                    }
                } else {
                    res = ui.showMessage(input.substring(7) + " is not a valid index");
                }
                break;
            case TODO:
                try {
                    Task newToDo = taskList.addToDo(input.substring(5));
                    res = ui.addedTask(newToDo, this.taskList.getTasks().size());
                } catch (InvalidTaskNameException e) {
                    res = ui.showMessage(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    Task newDeadLine = taskList.addDeadline(input.substring(9));
                    res = ui.addedTask(newDeadLine, this.taskList.getTasks().size());
                } catch (InvalidTaskNameException | InvalidDateException e) {
                    res = ui.showMessage(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    Task newEvent = taskList.addEvent(input.substring(6));
                    res = ui.addedTask(newEvent, this.taskList.getTasks().size());
                } catch (InvalidDateException | InvalidTaskNameException e) {
                    res = ui.showMessage(e.getMessage());
                }
                break;
            case DELETE:
                int index = parser.checkIndex(input, 7);
                try {
                    Task deleted = taskList.deleteTask(index);
                    res = ui.deletedTask(deleted, this.taskList.getTasks().size());
                } catch (TaskOutOfBoundsError e) {
                    res = ui.showMessage(e.getMessage());
                }
                break;
            case FIND:
                String prompt = input.substring(5).trim();
                ArrayList<Task> output = taskList.find(prompt);
                res = ui.showMatchedTasks(output);
                break;
            case WELCOME:
                res = ui.showWelcomeMessage("MrTracker");
                break;
            default:
                res = ui.showMessage("I am sorry, but I don't know what that means :-(");
                break;
            }
        } else {
            res = ui.showMessage("I am sorry, but I don't know what that means :-(");
        }
        storage.save(taskList.getTasks());
        return res;
    }
}
