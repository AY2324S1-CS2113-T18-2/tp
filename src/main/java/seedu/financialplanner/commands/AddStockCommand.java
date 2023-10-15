package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class AddStockCommand extends AbstractCommand {
    private final String stockCode;

    public AddStockCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("s")) {
            throw new IllegalArgumentException("Stock code cannot be empty");
        }
        stockCode = rawCommand.extraArgs.get("s");
        rawCommand.extraArgs.remove("s");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        Ui ui = Ui.INSTANCE;
        WatchList watchList = WatchList.INSTANCE;
        String stockName;
        try {
            stockName = watchList.addStock(stockCode);
            ui.printAddStock(stockName);
        } catch (FinancialPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
