package tasks;

/**
 * Represents the general form of a task.
 * A <code>Task</code> object minimally has a name and a flag to show if it is done
 */
public abstract class Task {
    protected String name;
    protected boolean isDone = false;

    public String getName () {
        return this.name;
    }

    /**
     * Returns the string representation of how a task should be saved
     *
     * @return save format of task.
     */
    public abstract String toSave ();

    /**
     * Marks the task as done by setting flag to true
     */
    public void mark () {
        this.isDone = true;
    }

    /**
     * Unmarks the task by setting flag to false
     */
    public void unMark () {
        this.isDone = false;
    }


    /**
     * Returns the string representation a task, including all its relevant information
     *
     * @return string representation of a task.
     */
    @Override
    public String toString () {
        String res = "";
        if (this.isDone) {
            res += "[X] ";
        } else {
            res += "[ ] ";
        }
        res += this.name;
        return res;
    }
}
