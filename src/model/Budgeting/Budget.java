package model.Budgeting;

import Observe.MyObserver;
import model.Category;
import model.Income.Salary;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.ProtectionDomain;
import java.util.Scanner;


public class Budget implements MyObserver {

    private Categories categories;
    private Salaries salaries;
    Scanner scanner = new Scanner(System.in);


    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: initiates startMenu options to add expense

    public void Initialize() throws IOException, ParseException {
        categories = new Categories();
        salaries = new Salaries();
    }

    public void startMenu() throws IOException, ParseException {
        // referenced from LittleLoggingCalculator
        String choice = "";
        String name = "";
        String category = "";
        int amount = 0;
        categories = new Categories();
        salaries = new Salaries();

        while (true) {
            System.out.println(" What would you like to do \n" +
                    "[1] Enter 1 for Expense \n" +
                    "[2] Enter 2 for Salary\n" +
                    "[3] Enter 3 for Net Income\n" +
                    "[4] Enter 4 to Quit");
            choice = scanner.nextLine();
            System.out.println("you have selected: " + choice);
            if (choice.equals("1")) {
                categories.addObserver(this);
                categories.expenseChoice(name, amount, category);
                categories.notifyObservers(this);
            } else if (choice.equals("2")) {
                salaries.incomeChoice(amount);

            } else if (choice.equals("3")){
                System.out.println(netIncome());

//            } else if (choice.equals("3")){
//                diagnostics(choice);4

            }
            if (choice.equals("4")) {
                salaries.save();
                categories.save();
                break;
            }
        }
    }

    public void expenseChoice(String name, int amount, String category){
        categories.expenseChoice(name, amount, category);
        categories.save();

    }

    public void incomeChoice(int amount ){
        salaries.incomeChoice(amount);
        salaries.save();
    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: displays information about categories (currently)
    public void diagnostics(String choice){
        while(true) {
            System.out.println
                    ("[1] display information about first category in categories " +
                            "[2] display information about the second category in categories " +
                            "[3] break information getting loop and return to normal");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("The first Category in Categories is called " + categories.getCategories().get(0).getName());
                System.out.println("The names of the expenses in this category are " + categories.getCategories().get(0).getNamesOfExpenses());
            }else if (choice.equals("2")){
                System.out.println("The second Category in Categories is called " + categories.getCategories().get(1).getName());
                System.out.println("The names of the expenses in this Category are " + categories.getCategories().get(1).getNamesOfExpenses());
            }else if (choice.equals("3")){
                break;
            }
        }
    }

    public int netIncome(){
        int totalExpense = 0;
        int totalIncome = 0;
        for(Category category: categories.getCategories()){
            totalExpense = totalExpense + category.totalExpenses();
        }
        for(Salary salary: salaries.getSalaries()){
            totalIncome = totalIncome + salary.getAmount();
        }
        return ( totalIncome - totalExpense);
    }

    @Override
    public void update (MyObserver observer){
        System.out.println("Categories has been updated");
    }

    public Categories getCategories() {
        return categories;
    }

    public Salaries getSalaries(){
        return salaries;
    }

}

