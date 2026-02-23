package logic;

import exception.DeoException;

public class Parser {
    public static String[] parse(String input) throws DeoException {
        if (input.equals("list"))
            return new String[] { "list" };
        if (input.equals("bye"))
            return new String[] { "bye" };
        if (input.startsWith("mark "))
            return new String[] { "mark", input.substring(5).trim() };
        if (input.startsWith("unmark "))
            return new String[] { "unmark", input.substring(7).trim() };
        if (input.startsWith("delete "))
            return new String[] { "delete", input.substring(7).trim() };
        if (input.startsWith("todo "))
            return new String[] { "todo", input.substring(5).trim() };
        if (input.startsWith("deadline "))
            return new String[] { "deadline", input.substring(9).trim() };
        if (input.startsWith("event "))
            return new String[] { "event", input.substring(6).trim() };
        if (input.startsWith("find "))
            return new String[] { "find", input.substring(5).trim() };
        throw new DeoException("I'm too stupid to understand what you mean");
    }
}
