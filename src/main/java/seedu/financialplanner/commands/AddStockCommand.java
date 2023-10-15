package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

public class AddStockCommand extends AbstractCommand {
    private final String stockCode;

    public AddStockCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("s")) {
            throw new IllegalArgumentException("Stock code cannot be empty");
        }
        stockCode = rawCommand.extraArgs.get("s");
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
