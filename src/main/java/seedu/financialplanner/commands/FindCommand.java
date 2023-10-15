package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class FindCommand extends AbstractCommand {
    private final String description;

    public FindCommand(RawCommand rawCommand) {
        this.description = String.join(" ", rawCommand.args);
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        FinancialList financialList = FinancialList.INSTANCE;
        Ui ui = Ui.INSTANCE;
        WatchList watchList = WatchList.INSTANCE;
        ArrayList<String> foundedFinancialList = new ArrayList<>();
        ArrayList<String> foundedWatchList = new ArrayList<>();
        for (int i = 0; i < financialList.size(); i++) {
            if (financialList.get(i).formatString().contains(description)) {
                foundedFinancialList.add(financialList.get(i).formatString());
            }
        }
        if (!foundedFinancialList.isEmpty()) {
            ui.showMessage("Here are the matching financial records in your financial list:");
        } else {
            ui.showMessage("There is no matching financial record in your financial list.");
        }
        for (String foundedFinancialRecord : foundedFinancialList) {
            ui.showMessage(foundedFinancialRecord);
        }

        for (int i = 0; i < watchList.size(); i++) {
            if (watchList.get(i).toString().contains(description)) {
                foundedWatchList.add(watchList.get(i).toString());
            }
        }
        if (!foundedWatchList.isEmpty()) {
            ui.showMessage("Here are the matching stock records in your stock list:");
        } else {
            ui.showMessage("There is no matching stock record in your stock list.");
        }
        for (String foundedStockRecord : foundedWatchList) {
            ui.showMessage(foundedStockRecord);
        }
    }
}
