package seedu.financialplanner.visualisations;

import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Expense;

import java.util.HashMap;
import java.util.Map;

public class Categorizer {
    public static Map<String, Double> sortExpenses(CashflowList financialList) {
        Map<String, Double> expensesByCat = new HashMap<>();
        for (Cashflow e: financialList.list) {
            if (e instanceof Expense) {
                String key = e.getType();
                double value = expensesByCat.getOrDefault(key, 0.0) + e.getValue();
                expensesByCat.put(key, value);
            }
        }
        return expensesByCat;
    }
}