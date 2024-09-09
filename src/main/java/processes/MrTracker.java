package processes;

import java.util.ArrayList;
import java.util.Arrays;

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

        String res = null;

        PrefixString pref = parser.parseCommand(input);

        if (pref == null) {
            res = ui.showMessage("I am sorry, but I don't know what that means :-(");
            return res;
        }

        switch (pref) {
        case BYE:
            res = ui.showGoodbyeMessage();
            break;

        case LIST:
            res = ui.showTaskList(taskList.getTasks());
            break;

        case MARK:
            if (!parser.checkValidIndex(input.substring(5))) {
                String message = input.substring(5) + " is not a valid index";
                res = ui.showMessage(message);
                break;
            }

            int index = Integer.parseInt(input.substring(5));
            try {
                Task markedTask = taskList.markAndUnmark(index, true);
                res = ui.showMarked(markedTask);
            } catch (TaskOutOfBoundsError ex) {
                res = ui.showMessage(ex.getMessage());
            }
            break;

        case UNMARK:
            if (!parser.checkValidIndex(input.substring(7))) {
                String message = input.substring(7) + " is not a valid index";
                res = ui.showMessage(message);
                break;
            }

            index = Integer.parseInt(input.substring(7));
            try {
                Task unMarkedTask = taskList.markAndUnmark(index, false);
                res = ui.showUnmarked(unMarkedTask);
            } catch (TaskOutOfBoundsError ex) {
                res = ui.showMessage(ex.getMessage());
            }
            break;

        case TODO:
            try {
                Task newToDo = taskList.addToDo(input.substring(5));
                res = ui.addedTask(newToDo, taskList.getSize());
            } catch (InvalidTaskNameException e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case DEADLINE:
            try {
                Task newDeadLine = taskList.addDeadline(input.substring(9));
                res = ui.addedTask(newDeadLine, taskList.getSize());
            } catch (InvalidTaskNameException | InvalidDateException e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case EVENT:
            try {
                Task newEvent = taskList.addEvent(input.substring(6));
                res = ui.addedTask(newEvent, taskList.getSize());
            } catch (InvalidDateException | InvalidTaskNameException e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case DELETE:
            if (!parser.checkValidIndex(input.substring(7))) {
               String message = input.substring(7) + " is not a valid index";
                res = ui.showMessage(message);
                break;
            }
            index = Integer.parseInt(input.substring(7));
            try {
                Task deleted = taskList.deleteTask(index);
                res = ui.deletedTask(deleted, taskList.getSize());
            } catch (TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case FIND:
            String prompt = input.substring(5).trim();
            ArrayList<Task> output = taskList.find(prompt);
            res = ui.showMatchedTasks(output, prompt);
            break;

        case WELCOME:
            res = ui.showWelcomeMessage("MrTracker");
            break;

        case TAG:
            String[] inputs = input.substring(4).split("#");
            if (!parser.checkValidIndex(inputs[0])) {
                String message = inputs[0] + " is not a valid index";
                res = ui.showMessage(message);
                break;
            }
            try {
                Task taskToAddTags = taskList.tag(inputs);
                res = ui.showTaskTags(taskToAddTags);
            } catch(TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            }
            break;

        case REMOVETAGS:
            inputs = input.substring(12).split("#");
            if (!parser.checkValidIndex(inputs[0])) {
                String message = inputs[0] + " is not a valid index";
                res = ui.showMessage(message);
                break;
            }
            try {
                Task taskToRemoveTags = taskList.removeTags(inputs);
                res = ui.showRemoveTaskTags(taskToRemoveTags, Arrays.copyOfRange(inputs, 1, inputs.length));
            } catch(TaskOutOfBoundsError e) {
                res = ui.showMessage(e.getMessage());
            }
            break;


        default:
            res = ui.showMessage("I am sorry, but I don't know what that means :-(");
            break;
        }
        storage.save(taskList.getTasks());
        return res;
    }
}
