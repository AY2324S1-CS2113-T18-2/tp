package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.CashflowCategory;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.utils.Ui;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashflowList {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    private static CashflowList cashflowList = null;
    public final ArrayList<Cashflow> list = new ArrayList<>();
    protected Ui ui = Ui.getInstance();
    private CashflowList() {
    }

    public static CashflowList getInstance() {
        if (cashflowList == null) {
            cashflowList = new CashflowList();
        }
        return cashflowList;
    }

    public void addIncome(double value, IncomeType type, int recur, String description) {
        try {
            logger.log(Level.INFO, "Adding income");
            int existingListSize = list.size();

            Income toAdd = new Income(value, type, recur, description);
            addToList(toAdd);
            ui.printAddedCashflow(toAdd);

            int newListSize = list.size();
            assert newListSize == existingListSize + 1;
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }

    private void addToList(Cashflow toAdd) {
        list.add(toAdd);
    }

    public void addExpense(double value, ExpenseType type, int recur, String description) {
        try {
            logger.log(Level.INFO, "Adding expense");
            int existingListSize = list.size();

            Expense toAdd = new Expense(value, type, recur, description);
            addToList(toAdd);
            ui.printAddedCashflow(toAdd);

            int newListSize = list.size();
            assert newListSize == existingListSize + 1;
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public double deleteCashflowWithoutCategory(int index) {
        int existingListSize = list.size();
        int listIndex = index - 1;

        Cashflow toRemove = list.get(listIndex);
        list.remove(listIndex);
        toRemove.deleteCashflowValue();
        ui.printDeletedCashflow(toRemove);

        int newListSize = list.size();
        assert newListSize == existingListSize - 1;
        return toRemove.getAmount();
    }

    public void deleteRecurWithoutCategory(int index) {
        int listIndex = index - 1;

        Cashflow toRemoveRecur = list.get(listIndex);
        if (toRemoveRecur.getRecur() == 0 || toRemoveRecur.hasRecurred) {
            ui.showMessage("Cashflow is already not recurring or has already recurred");
        } else {
            toRemoveRecur.setDate(null);
            toRemoveRecur.setRecur(0);
            list.set(listIndex, toRemoveRecur);
            ui.printDeletedRecur(toRemoveRecur);
        }
    }
    //helper method to find the index of a given cashflow in the overall list
    //given its index in its respective list. e.g. "income 3" is the third income
    //in the overall list
    private int cashflowIndexFinder(CashflowCategory category, int cashflowIndex) {
        assert category.equals(CashflowCategory.INCOME) || category.equals(CashflowCategory.EXPENSE)
                || category.equals(CashflowCategory.RECURRING);

        switch (category) {
        case INCOME:
            return findCashflowIndexFromIncomeIndex(cashflowIndex);
        case EXPENSE:
            return findCashflowIndexFromExpenseIndex(cashflowIndex);
        case RECURRING:
            return findCashflowIndexFromRecurIndex(cashflowIndex);
        default:
            return -1;
        }
    }

    private int findCashflowIndexFromIncomeIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry instanceof Income) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }

    private int findCashflowIndexFromExpenseIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry instanceof Expense) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }
    private int findCashflowIndexFromRecurIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry.getRecur() > 0 && !entry.getHasRecurred()) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }
    public void deleteRecurWithCategory(CashflowCategory category, int index) {
        int listIndex = cashflowIndexFinder(category, index);

        Cashflow toRemoveRecur = list.get(listIndex);
        if (toRemoveRecur.getRecur() == 0 || toRemoveRecur.hasRecurred) {
            ui.showMessage("Cashflow is already not recurring or has already recurred");
        } else {
            toRemoveRecur.setDate(null);
            toRemoveRecur.setRecur(0);
            list.set(listIndex, toRemoveRecur);
            ui.printDeletedRecur(toRemoveRecur);
        }
    }
    public double deleteCashflowWithCategory(CashflowCategory category, int index) {
        int existingListSize = list.size();
        int listIndex = cashflowIndexFinder(category, index);

        Cashflow toRemove = list.get(listIndex);
        list.remove(listIndex);
        toRemove.deleteCashflowValue();
        ui.printDeletedCashflow(toRemove);

        int newListSize = list.size();
        assert newListSize == existingListSize - 1;
        return toRemove.getAmount();
    }

    public void load(Cashflow entry) {
        addToList(entry);
    }

    public String getList() {
        StringBuilder output = new StringBuilder();
        for (Cashflow entry : list) {
            output.append(entry).append("\n");
        }
        return output.toString();
    }
}
