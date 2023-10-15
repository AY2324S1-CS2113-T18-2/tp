package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

public class InvalidCommand extends AbstractCommand {
    public InvalidCommand() {
    }

    @Override
    public void execute() {
        Ui.INSTANCE.showMessage("Unknown command. Please try again.");
    }
}
