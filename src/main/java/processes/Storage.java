package processes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that handles logic related to storing users' tasks in a file on their local machine
 */
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Storage {
    private String dirPath;
    private String filePath;

    /**
     * Constructor for Storage object.
     * Stores the directory and file paths of where the data should be stored.
     *
     * @param dirPath The directory path that the file containing the saved user inputs should be in
     * @param filePath The path to the file where user inputs are saved.
     */
    public Storage (String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * Does not return anything. Takes in a tasklist.
     * Goes to the dirPath and filePath locations to retrieve user info.
     * If the file or directory did not previously exist, create them.
     * If file or directory cannot be created, print reason onto the terminal
     *
     * @param taskList The location at which user info is stored for use in the programme
     */
    public void loadData (ArrayList<Task> taskList) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                boolean doesDirExists = dir.mkdir();
                if (!doesDirExists) {
                    System.out.println("Error: Directory " + dirPath + " could not be created" +
                            " to store your tasks!");
                    return;
                }
            }

            File file = new File (filePath);
            if (!file.exists()) {
                boolean doesFileExists = file.createNewFile();
                if (doesFileExists) {
                    System.out.println("File created to store your tasks!");
                } else {
                    System.out.println("Error: The file " + filePath + " could not be " +
                            "created to store your tasks!");
                }

                return;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String type = input.substring(0, 1);
                String[] args = input.substring(2).trim().split("\\|");

                Task newTask = null;
                switch (type) {
                case "T":
                    newTask = new ToDo(args);
                    break;
                case "D":
                    newTask = new DeadLine(args);
                    break;
                case "E":
                    newTask = new Event(args);
                    break;
                }
                taskList.add(newTask);
            }
            sc.close();
        } catch (IOException ex) {
            System.out.println("The file " + filePath + " could not be read from");
        }
    }

    /**
     * Does not return anything. Saves the user input into the file path specified in the constructor.
     *
     * @param taskList The user input to be saved.
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter file = new FileWriter(filePath);
            for (Task item : taskList) {
                file.write(item.toSave());
                file.write("\n");
            }
            file.close();
        } catch (IOException ex) {
            System.out.println("Sorry, your tasks could not be saved");
        }
    }
}
