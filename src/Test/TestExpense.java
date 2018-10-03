package Test;

import model.Category;
import model.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestExpense {

    @Test
    public void testConstructor() {
        Category c = new Category("food");
        Expense e = new Expense(10, "chicken", c);
        assertEquals(e.getCost(), 10);
        assertEquals(e.getName(), "chicken");
        assertEquals(e.getCategory(), c);

    }

    @Test
    public void testCompareExpenseFalse(){
        Category c = new Category("food");
        Expense e0 = new Expense(10, "chicken", c);
        Expense e1 = new Expense(11, "chicken", c);

        assertFalse(e1.compareExpense(e0));
    }

    @Test
    public void testCompareExpenseTrue(){
        Category c = new Category("food");
        Expense e0 = new Expense(10, "chicken", c);
        Expense e1 = new Expense(10, "chicken", c);

        assertTrue(e1.compareExpense(e0));
    }

    @Test
    public void testIsItEnoughTrueBoundary(){
        Category c = new Category("food");
        Expense e = new Expense(10, "chicken", c);
        assertTrue(e.isItEnough(10));
    }

    @Test
    public void testIsItEnoughTrue(){
        Category c = new Category("food");
        Expense e = new Expense(10, "chicken", c);
        assertTrue(e.isItEnough(9));
    }

    @Test
    public void testIsItEnoughFalse(){
        Category c = new Category("food");
        Expense e = new Expense(10, "chicken", c);
        assertFalse(e.isItEnough(11));
    }
}
