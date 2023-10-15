package seedu.financialplanner;

import seedu.financialplanner.commands.AbstractCommand;
import seedu.financialplanner.commands.ExitCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.storage.Storage;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

public class FinancialPlanner {

    private static final String FILE_PATH = "data/data.txt";
    private final Storage storage = Storage.INSTANCE;
    private final Ui ui = Ui.INSTANCE;
    private final WatchList watchList = WatchList.INSTANCE;
    private final FinancialList financialList = FinancialList.INSTANCE;

    private FinancialPlanner() {
    }

    public static void main(String[] args) {
        new FinancialPlanner().run();
    }

    public void run() {
        try {
            storage.load(financialList, ui, FILE_PATH);
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }

        ui.welcomeMessage();
        String input;
        AbstractCommand command = null;

        while (!(command instanceof ExitCommand)) {
            input = ui.input();
            try {
                command = Parser.parseCommand(input);
                command.execute();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }

        save();
        ui.exitMessage();
    }

    public void save() {
        try {
            storage.save(financialList, FILE_PATH);
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
