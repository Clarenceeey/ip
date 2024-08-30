package processes;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


import java.util.ArrayList;

/**
 * TaskList class stores the state of the programme.
 * It stores user inputs in previous runs of the programme (provided they are loaded in).
 * Contains various methods to manipulate the tasks
 */
public class TaskList {

    private ArrayList<Task> taskList;


    /**
     * Constructor for TaskList object.
     * Creates the data structure used to store the user's tasks.
     * The data structure used is an ArrayList
     *
     */
    public TaskList () {
        this.taskList = new ArrayList<>();
    }

    /**
     * Getter method that returns the current list of tasks
     *
     * @return The current list of tasks
     *
     */
    public ArrayList<Task> getTasks () {
        return taskList;
    }

    /**
     * Does not return anything.
     * Taking in the user input from an Ui object, create a ToDo class and add it to the current list of tasks
     *
     * @param arg The string received from the Ui object to create a ToDo object.
     *
     */
    public void addTodo (String arg) {

        try {
            ToDo newToDo = new ToDo(arg);
            taskList.add(newToDo);
            System.out.println("I have added the task " + newToDo.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
        } catch (InvalidTaskNameException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Does not return anything.
     * Taking in the user input from an Ui object, create a DeadLine class and add it to the current list of tasks
     *
     * @param arg The string received from the Ui object to create a DeadLine object.
     *
     */
    public void addDeadline (String arg) {
        try {
            Task newDeadline = new DeadLine(arg);
            taskList.add(newDeadline);
            System.out.println("I have added the task " + newDeadline.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
        } catch (InvalidTaskNameException | InvalidDateException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Does not return anything.
     * Taking in the user input from an Ui object, create an Event class and add it to the current list of tasks
     *
     * @param arg The string received from the Ui object to create an Event object.
     *
     */
    public void addEvent (String arg) {
        try {
            Task newEvent = new Event(arg);
            taskList.add(newEvent);
            System.out.println("I have added the task " + newEvent.toString());
            System.out.println("You now have " + taskList.size() + " task(s)");
        } catch (InvalidTaskNameException | InvalidDateException ex) {
            System.out.println(ex.getMessage());
            return;
        }
    }

    /**
     * Does not return anything.
     * Removes the task at the specified index from the current list of tasks
     *
     * @param index The index of the object to be removed.
     *
     */
    public void deleteTask(int index) {
        if (index < 1 || index > taskList.size()) {
            System.out.println("Task " + index + " does not exist!");
            return;
        }
        index--;
        Task currTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("The task\n  " + currTask.toString() + "\nhas been removed! \nYou now have "
                + taskList.size() + " tasks left.");
    }

    /**
     * Does not return anything.
     * Receive index of task to mark and whether the programme should mark or unmark the task
     * If isMark is true, the task will be marked, and if isMark is false, the task will be unmarked.
     *
     * @param index The index of the task to mark/unmark.
     * @param isMark Determines whether the task should be marked or not.
     *
     */
    public void markAndUnmark (int index, boolean isMark) {
        if (index == Integer.MAX_VALUE) {
            throw new RuntimeException();
        } else if (index < 1 || index > taskList.size()) {
            System.out.println("task " + index + " does not exist");
        } else {
            index--;
            Task curr = taskList.get(index);
            if (isMark) {
                curr.mark();
                System.out.println("task\n  " + curr.toString() + "\nis marked!");
            } else {
                curr.unMark();
                System.out.println("task\n  " + curr.toString() + "\nis unmarked!");
            }
        }
    }

    /**
     * Does not return anything.
     * Receive the prompt from the user and searches the current list of tasks for task names that contain the prompt.
     * After getting the list of matching tasks, print them out to the terminal
     *
     * @param prompt The prompt provided by the user.
     *
     */
    public void find (String prompt) {
        Ui.printLine();
        ArrayList<Task> output = new ArrayList<>();
        for (Task item: this.taskList) {
            if (item.getName().contains(prompt)) {
                output.add(item);
            }
        }
        if (output.size() == 0) {
            System.out.println("There are no tasks in your list that match " + "'prompt'");
        } else {
            System.out.println("Here are the matching task(s) in your list: ");
            for (int i = 0; i < output.size(); i++) {
                System.out.println((i + 1) + ". " + output.get(i).toString());
            }
        }
    }
}
