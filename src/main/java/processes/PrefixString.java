package processes;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;

/**
 * PrefixString enum that contains the inputs the programme can handle.
 * Helps match user inputs to one of the predefined, valid inputs
 */
public enum PrefixString {
    BYE("bye"),
    LIST("list"),
    MARK("mark "),
    UNMARK("unmark "),
    TODO("todo "),
    DEADLINE("deadline "),
    EVENT("event "),
    DELETE("delete "),
    FIND("find ");

    private final String prefix;


    /**
     * Constructor for PrefixString enums. Stores the prefixes that valid instructions should contain.
     *
     * @param prefix The prefix string that user inputs should match.
     *
     */
    PrefixString (String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the PrefixString that matches the user's input.
     * Checks all existing PrefixString enums. If user input matches none of the PrefixStrings, null is returned
     *
     * @param input The user input that is being matched with a PrefixString enum
     * @return The prefix string of the user input, if they match
     *
     */
    public static PrefixString checkPrefixString (String input) {
        for (PrefixString item: PrefixString.values()) {

            if (item.equals(BYE)) {
                if (input.equals("bye")) {
                    return item;
                }
            } else if (item.equals(LIST)) {
                if (input.equals("list")) {
                    return item;
                }
            } else {
                if (input.startsWith(item.prefix)) {
                    return item;
                }
            }
        }
        return null;
    }

}