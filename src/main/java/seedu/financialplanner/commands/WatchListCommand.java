package seedu.financialplanner.commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

public class WatchListCommand extends AbstractCommand {
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
