package Test;

import model.Category;
import model.Expense;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCategory {

    @Test
    public void testConstructor() {
        Category c = new Category("food");
        assertEquals(c.getName(), "food");
    }


    @Test
    public void testAddNewExpense(){
        Category c = new Category("food");
        Expense e0 = new Expense(10, "peppers", c);
        c.addNewExpense(e0);
        assertEquals(c.getFirstExpense(), e0);

        }

    @Test
    public void testTotalExpenses(){
        Category c = new Category("food");
        Expense e0 = new Expense(10, "peppers", c);
        Expense e1 = new Expense(11, "meat", c);
        Expense e2 = new Expense(9, "sauce", c);
        Expense e3 = new Expense(3, "eggs", c);
        c.addNewExpense(e0);
        c.addNewExpense(e1);
        c.addNewExpense(e2);
        c.addNewExpense(e3);

        assertEquals(c.totalExpenses(), 33);

    }

    @Test
    public void testRemoveExpense(){
        Category c = new Category("food");
        Expense e0 = new Expense(10, "peppers", c);
        Expense e1 = new Expense(11, "meat", c);
        Expense e2 = new Expense(9, "sauce", c);
        Expense e3 = new Expense(3, "eggs", c);
        c.addNewExpense(e0);
        c.addNewExpense(e1);
        c.addNewExpense(e2);
        c.addNewExpense(e3);

        assertEquals(c.totalExpenses(), 33);

        c.removeExpense(e0);
        assertEquals(c.totalExpenses(),23 );
    }



}
