package seedu.financialplanner.commands;

import seedu.financialplanner.enumerations.EntryCategory;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

public class EntryCommand extends AbstractCommand {
    protected double amount;
    protected EntryCategory category;
    protected String type;
    protected int recur = 0;

    public EntryCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String typeString = String.join(" ", rawCommand.args);
        try {
            category = EntryCategory.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Entry must be either income or expense");
        }

        if (!rawCommand.extraArgs.containsKey("a")) {
            throw new IllegalArgumentException("Entry must have an amount");
        }
        try {
            amount = Double.parseDouble(rawCommand.extraArgs.get("a"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Amount must be a number");
        }

        if (!rawCommand.extraArgs.containsKey("t")) {
            throw new IllegalArgumentException("Entry must have a type");
        }
        type = rawCommand.extraArgs.get("t");

        if (rawCommand.extraArgs.containsKey("r")) {
            try {
                recur = Integer.parseInt(rawCommand.extraArgs.get("r"));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Recurrence must be an integer");
            }
        }
    }

    @Override
    public void execute() {
        switch (category) {
        case INCOME:
            FinancialList.INSTANCE.addIncome(amount, type, recur);
            break;
        case EXPENSE:
            FinancialList.INSTANCE.addExpense(amount, type, recur);
            break;
        default:
            Ui.INSTANCE.showMessage("Unidentified entry.");
            break;
        }
    }
}
