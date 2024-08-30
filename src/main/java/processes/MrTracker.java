package processes;

/**
 * The main file of the programme. Contains the main method.
 * Run the main method to start the task tracker
 */
public class MrTracker {

    public static final String FILEPATH = "./data/tasks.txt";
    public static final String DIRPATH = "./data";

    /**
     * Entry point of the programme. Starts all necessary processes for user to use the task tracker.
     *
     * @param args No parameters are passed
     */
    public static void main (String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(DIRPATH, FILEPATH);
        Parser parser = new Parser();
        TaskList taskList = new TaskList();

        storage.loadData(taskList.getTasks());
        ui.showTaskList(taskList.getTasks());

        String name = "Mr Tracker";
        ui.showWelcomeMessage(name);

        mainLoop:
        while (true) {
            String input = ui.readCommand();


            PrefixString pref = parser.parseCommand(input);
            if (pref != null) {
                switch (pref) {
                case BYE:
                    ui.close();
                    break mainLoop;
                case LIST:
                    ui.showTaskList(taskList.getTasks());
                    break;
                case MARK:
                    if (parser.checkValidIndex(input, 5)) {
                        int index = parser.checkIndex(input, 5);
                        taskList.markAndUnmark(index, true);
                    } else {
                        ui.showMessage(input.substring(5) + " is not a valid index");
                    }
                    break;
                case UNMARK:
                    if (parser.checkValidIndex(input, 7)) {
                        int index = parser.checkIndex(input, 7);
                        taskList.markAndUnmark(index, false);
                    } else {
                        ui.showMessage(input.substring(7) + " is not a valid index");
                    }
                    break;
                case TODO:
                    taskList.addTodo(input.substring(5));
                    break;
                case DEADLINE:
                    taskList.addDeadline(input.substring(9));
                    break;
                case EVENT:
                    taskList.addEvent(input.substring(6));
                    break;
                case DELETE:
                    int index = parser.checkIndex(input, 7);
                    taskList.deleteTask(index);
                    break;
                case FIND:
                    String prompt = input.substring(5).trim();
                    taskList.find(prompt);
                    break;
                default:
                    ui.showMessage("I am sorry, but I don't know what that means :-(");
                    break;
                }
            } else {
                ui.showMessage("I am sorry, but I don't know what that means :-(");
            }
            storage.save(taskList.getTasks());
            ui.printLine();

        }

        ui.showGoodbyeMessage();
    }
}
