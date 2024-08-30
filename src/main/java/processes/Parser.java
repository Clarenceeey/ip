package processes;

/**
 * Parser class receives input from a Ui object and makes sense of it
 * A <code>Parser</code> object outputs useful information from user input
 */
public class Parser {

    /**
     * Returns the PrefixString enum that matches the input
     * Receives input, then calls the checkPrefixString method from the PrefixString enum.
     *
     * @param input The input string to compare with the PrefixString enum
     * @return The enum that matches the input string
     */
    public PrefixString parseCommand (String input) {
        return PrefixString.checkPrefixString(input);
    }


    /**
     * Returns whether the command contains an int that can be used as an index to track tasks.
     *
     * @param command The input string that may or may not contain a valid index.
     * @param start The position that the integer index should be
     * @return Whether the command has a valid integer index.
     */
    public boolean checkValidIndex(String command, int start) {
        String trimmed = command.substring(start).trim();
        try {
            Integer.parseInt(trimmed);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public int checkIndex (String command, int start) {
        String trimmed = command.substring(start);
        try {
            return Integer.parseInt(trimmed);
        } catch (Exception ex) {
            return Integer.MAX_VALUE;
        }
    }
}
