package seedu.financialplanner.commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class WatchListCommand extends AbstractCommand {
    public WatchListCommand(RawCommand rawCommand) throws IllegalArgumentException{
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }
    @Override
    public void execute() {
        JSONArray stocks = WatchList.INSTANCE.fetchFMPStockPrices();
        Ui.INSTANCE.printWatchListHeader();
        for (Object o : stocks) {
            JSONObject stock = (JSONObject) o;
            Ui.INSTANCE.printStockInfo(stock);
        }
    }
}
