package seedu.financialplanner.commands;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryTest {
    private Ui ui = Ui.INSTANCE;
    private FinancialList financialList = FinancialList.INSTANCE;
    private WatchList watchList = WatchList.INSTANCE;

    @Test
    void testExecute() {
        EntryCommand testEntry = new EntryCommand(Parser.parseRawCommand("add income /a 300 /t work /r 30"));
        testEntry.execute();
        assertEquals(300, testEntry.amount);
        assertEquals("work", testEntry.category);
        assertEquals(30, testEntry.recur);

        testEntry = new EntryCommand(Parser.parseRawCommand("add expense /a 15 /t double_mcspicy"));
        testEntry.execute();
        assertEquals(15, testEntry.amount);
        assertEquals("double_mcspicy", testEntry.category);
        assertEquals(0, testEntry.recur);
    }
}
