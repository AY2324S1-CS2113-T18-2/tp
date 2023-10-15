package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.utils.Ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    public static Path testFolder;

    @Test
    public void loadValidData() throws FinancialPlannerException {
        Storage storage = Storage.INSTANCE;
        FinancialList test = FinancialList.INSTANCE;
        storage.load(test, Ui.INSTANCE, "src/test/testData/ValidData.txt");
        FinancialList expected = getTestData();
        assertEquals(expected.getList(), test.getList());
    }

    @Test
    public void loadInvalidData_userInputNo() {
        Storage storage = Storage.INSTANCE;
        FinancialList test = FinancialList.INSTANCE;
        ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
        System.setIn(in);
        assertThrows(FinancialPlannerException.class,
                () -> storage.load(test, Ui.INSTANCE, "src/test/testData/InvalidData.txt"));
    }

    @Test
    public void saveValidData() throws FinancialPlannerException, IOException {
        FinancialList expected = getTestData();
        Storage storage = Storage.INSTANCE;
        storage.save(expected, String.valueOf(testFolder.resolve("temp.txt")));
        assertEquals(Files.readAllLines(Path.of("src/test/testData/ValidData.txt")),
                Files.readAllLines(testFolder.resolve("temp.txt")));
    }

    @Test
    public void saveNonExistentFile() {
        FinancialList expected = getTestData();
        Storage storage = Storage.INSTANCE;
        assertThrows(FinancialPlannerException.class, () -> storage.save(expected, ""));
    }

    private FinancialList getTestData() {
        FinancialList list = FinancialList.INSTANCE;
        list.load(new Income(123.12, "allowance", 0));
        list.load(new Expense(100, "daily necessities", 30));
        return list;
    }
}
