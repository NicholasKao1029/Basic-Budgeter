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
//                System.out.println("[1] Enter 1 for Constant Salary [2] Enter 2 for PartTime Income");
//                choice = scanner.nextLine();
        System.out.println("Add amount of Constant Salary");
        while(true){
            try {
                if ((amount = scanner.nextInt()) == 0){
                    throw new IncorrectFigureException();
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Please insert an integer for amount");
                scanner.nextLine();
            } catch (IncorrectFigureException e) {
                System.out.println("Please add a value greater than 0");
                scanner.nextLine();
            } finally{
                System.out.println("Thanks for using salary feature!");
            }
        }
        scanner.nextLine();
        Salary salary = new ConstantSalary(amount);
        salaries.add(salary);
        System.out.println("Your salary of amount " + amount + " has been added");
//             } else if (choice.equals("2")){
//            System.out.println("Add amount of Constant Salary");
//            amount = scanner.nextInt();
//            scanner.nextLine();
//            Salary salary = new PartTimeIncome(amount);
//            salaries.add(salary);
//            System.out.println("Your salary of amount " + amount + " has been added");
    }

    public List<Salary> getSalaries(){
        return salaries;
    }

    public void save(){
        incomeParser.save(salaries);
    }
}
