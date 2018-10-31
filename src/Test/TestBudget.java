package Test;


import model.Budget;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TestBudget {



    @Test
    public void testZeroDollarExpense() throws IOException, ParseException {
        ByteArrayInputStream choice = new ByteArrayInputStream("1".getBytes());
        System.setIn(choice);

        Budget b = new Budget();

        b.startMenu();
        testZDEChoiceHelper();
        testZDEHelper1();
    }

    private void testZDEChoiceHelper (){

        ByteArrayInputStream expenseName = new ByteArrayInputStream("a".getBytes());
        System.setIn(expenseName);
    }

    private void testZDEHelper1(){
        ByteArrayInputStream expenseAmount = new ByteArrayInputStream("a".getBytes());
        System.setIn(expenseAmount);
    }

    private void testZDEHelper2(){

    }
}
