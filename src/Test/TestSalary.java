package Test;

import model.Salary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSalary {

    @Test
    public void testIsItEnoughTrueBoundary(){
        Salary i = new Salary(10);
        assertTrue(i.isItEnough(10));
    }

    @Test
    public void testisitEnoughTrue(){
        Salary i = new Salary(10);
        assertTrue(i.isItEnough(9));
    }

    @Test
    public void testisitEnoughFalse(){
        Salary i = new Salary(10);
        assertFalse(i.isItEnough(11));
    }

    @Test
    public void testSurplusEquals0(){
        Salary i = new Salary(10);
        assertEquals(i.surplus(10), 0);
    }

    @Test
    public void testSurplusNegative(){
        Salary i = new Salary(10);
        assertEquals(i.surplus(100), -90);
    }

    @Test
    public void testSurplusPositive(){
        Salary i = new Salary(10);
        assertEquals(i.surplus(4), 6);
    }


}
