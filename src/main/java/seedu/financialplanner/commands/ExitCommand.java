package seedu.financialplanner.commands;

import java.util.ArrayList;

public class ExitCommand extends AbstractCommand {
    public ExitCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
    }
}
