package Test;

import model.Income.ConstantSalary;
import model.Income.PartTimeIncome;
import model.Income.Salary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConstantSalary {

    @Test
    public void testIsItEnoughTrueBoundary(){
        Salary i = new ConstantSalary(10);
        assertTrue(i.isItEnough(10));
    }

    @Test
    public void testisitEnoughTrue(){
        Salary i = new ConstantSalary(10);
        assertTrue(i.isItEnough(9));
    }

    @Test
    public void testisitEnoughFalse(){
        Salary i = new ConstantSalary(10);
        assertFalse(i.isItEnough(11));
    }

    @Test
    public void testSurplusEquals0(){
        Salary i = new ConstantSalary(10);
        assertEquals(i.surplus(10), 0);
    }

    @Test
    public void testSurplusNegative(){
        Salary i = new ConstantSalary(10);
        assertEquals(i.surplus(100), -90);
    }

    @Test
    public void testSurplusPositive(){
        Salary i = new ConstantSalary(10);
        assertEquals(i.surplus(4), 6);
    }


}
