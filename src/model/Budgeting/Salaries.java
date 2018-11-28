package model.Budgeting;

import Exceptions.IncorrectFigureException;
import model.Files.IncomeJSON;
import model.Income.ConstantSalary;
import model.Income.Salary;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Salaries  {


    private IncomeJSON incomeParser = new IncomeJSON();
    private List<Salary> salaries = incomeParser.parseIncome();
    private Scanner scanner = new Scanner(System.in);


    public Salaries() throws IOException, ParseException {
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: Adds an income of Type Constant Income
    public void incomeChoice (int amount ){
        Salary salary = new ConstantSalary(amount);
        salaries.add(salary);
        System.out.println("Your salary of amount " + amount + " has been added");
    }

    public List<Salary> getSalaries(){
        return salaries;
    }

    public void save(){
        incomeParser.save(salaries);
    }

    public int total(){
        int total = 0;
        for(Salary s: salaries){
            total += s.getAmount();
        }
        return total;
    }
}
