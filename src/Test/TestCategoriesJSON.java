package Test;

import model.Category;
import model.Expense;
import model.Files.CategoriesJSON;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestCategoriesJSON {

    CategoriesJSON categoryParser = new CategoriesJSON();

    @Test
    public void testSave() throws IOException, ParseException {
        List<Category> categories = new ArrayList<>();
        Category c = new Category("food");
        Category c1 = new Category("misc");
        Expense e0 = new Expense(10, "peppers", c);
        Expense e1 = new Expense(11, "meat", c);
        Expense e2 = new Expense(9, "sauce", c);
        Expense e3 = new Expense(3, "eggs", c);
        c.addNewExpense(e0);
        c.addNewExpense(e1);
        c.addNewExpense(e2);
        c.addNewExpense(e3);
        Expense e4 = new Expense(1, "water", c1);
        Expense e5 = new Expense(2, "chair", c1);
        Expense e6 = new Expense(3, "jacket", c1);
        c1.addNewExpense(e4);
        c1.addNewExpense(e5);
        c1.addNewExpense(e6);
        categories.add(c1);
        categories.add(c);
        categoryParser.saveTest(categories);
    }

    @Test
    public void testLoad() throws IOException, ParseException {
        List<Category> categories = categoryParser.parseCategoriesTest();
        assertEquals(categories.get(0).getNamesOfExpenses().get(0),"peppers");
        assertEquals(categories.get(1).getNamesOfExpenses().get(0),"water");
        assertEquals(categories.get(0).getNamesOfExpenses().get(3),"eggs");


    }

}




