package Test;

import model.Files.IncomeJSON;
import model.Income.ConstantSalary;
import model.Income.Salary;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIncomeJson {

    IncomeJSON incomeParser = new IncomeJSON();

    @Test
    public void testsave() {
        Salary i0 = new ConstantSalary(1);
        Salary i1 = new ConstantSalary(2);
        Salary i2 = new ConstantSalary(3);
        Salary i3 = new ConstantSalary(5);
        Salary i4 = new ConstantSalary(10);

        List<Salary> salaries = new ArrayList<>();
        salaries.add(i0);
        salaries.add(i1);
        salaries.add(i2);
        salaries.add(i3);
        salaries.add(i4);

        incomeParser.saveTest(salaries);
    }

    @Test
    public void testload() throws IOException, ParseException {
        List<Salary> salaries = incomeParser.parseIncomeTest();
        assertEquals(salaries.get(0).getAmount(),1);

    }

}
