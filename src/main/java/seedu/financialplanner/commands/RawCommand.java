package seedu.financialplanner.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RawCommand {
    public final List<String> args = new ArrayList<>();
    public final Map<String, String> extraArgs = new HashMap<>();
    protected String commandName;

    public RawCommand(String name, List<String> args, Map<String, String> extraArgs) {
        this.commandName = name;
        this.args.addAll(args);
        this.extraArgs.putAll(extraArgs);
    }

    @SuppressWarnings("unused")
    public String getCommandName() {
        return commandName;
    }

    @SuppressWarnings("unused")
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
