package seedu.financialplanner.utils;

import seedu.financialplanner.commands.*;

import java.util.*;

public class Parser {
    private static final String EXIT_COMMAND_NAME = "exit";
    private static final String WATCHLIST_COMMAND_NAME = "watchlist";
    private static final String ADD_ENTRY_COMMAND_NAME = "add";
    private static final String ADD_STOCK_COMMAND_NAME = "addstock";
    private static final String FIND_COMMAND_NAME = "find";

    public static AbstractCommand parseCommand(String input) throws IllegalArgumentException {
        RawCommand rawCommand = parseRawCommand(input);
        return parseCommand(rawCommand);
    }

    public static RawCommand parseRawCommand(String input) throws IllegalArgumentException{
        Iterator<String> iterator = Arrays.stream(input.split(" ")).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        String commandName = iterator.next();
        List<String> args = new ArrayList<>();
        Map<String, String> extraArgs = new HashMap<>();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.contains("/")) {
                int indexOfDelimiter = next.indexOf("/");
                String extraArgumentName = next.substring(0, indexOfDelimiter);
                if (extraArgumentName.isEmpty()) {
                    throw new IllegalArgumentException("Extra argument name cannot be empty");
                }
                String extraArgumentContent = next.substring(indexOfDelimiter + 1);
                if (extraArgumentContent.isEmpty()) {
                    throw new IllegalArgumentException("Extra argument content cannot be empty");
                }
                if (extraArgs.containsKey(extraArgumentName)) {
                    throw new IllegalArgumentException("Duplicate extra argument name");
                } else {
                    extraArgs.put(extraArgumentName, extraArgumentContent);
                }
            } else {
                args.add(next);
            }
        }
        return new RawCommand(commandName, args, extraArgs);
    }

    public static AbstractCommand parseCommand(RawCommand rawCommand) throws IllegalArgumentException{
        switch (rawCommand.getCommandName()) {
            case EXIT_COMMAND_NAME: {
                return new ExitCommand();
            }
            case WATCHLIST_COMMAND_NAME: {
                return new WatchListCommand();
            }
            case ADD_ENTRY_COMMAND_NAME: {
                return new EntryCommand(rawCommand);
            }
            case ADD_STOCK_COMMAND_NAME: {
                return new AddStockCommand(rawCommand);
            }
            case FIND_COMMAND_NAME: {
                return new FindCommand(rawCommand);
            }
            default: {
                return new InvalidCommand();
            }
        }
    }
}
