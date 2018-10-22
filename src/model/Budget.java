package model;

//import model.Files.CreateFile;
import model.Files.CategoriesJSON;
import model.Files.IncomeJSON;
import model.Income.ConstantSalary;
import model.Income.PartTimeIncome;
import model.Income.Salary;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Budget {

    private List<Category> categories;
    private List<Salary> salaries;
    Scanner scanner = new Scanner(System.in);
//    CreateFile file = new CreateFile();
    CategoriesJSON categoryParser = new CategoriesJSON();
    IncomeJSON incomeParser = new IncomeJSON();

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: initiates startMenu options to add expense

    public void startMenu() throws IOException, ParseException {
        // referenced frorm LittleLoggingCalculator
        String choice = "";
        String name = "";
        String category = "";
        int amount = 0;
        categories = categoryParser.parseCategories();
        salaries = incomeParser.parseIncome();

        while (true) {
            System.out.println(" What would you like to do " +
                    "[1] Enter 1 for Expense" +
                    "[2] Enter 2 for Salary" +
                    "[3] Enter 3 for Diagnostics on categories" +
                    "[4] Enter 4 to Quit");
            choice = scanner.nextLine();
            System.out.println("you have selected: " + choice);
            if (choice.equals("1")) {
                System.out.println("Add name of expense");
                name = scanner.nextLine();
                System.out.println("Add amount");
                amount = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Add category");
                category = scanner.nextLine();
                Category newCategory = new Category(category);
                if (categories.contains(newCategory)) {
                    Category foundCategory = categories.get(categories.indexOf(newCategory));
                    Expense expense = new Expense(amount, name, foundCategory);
                    foundCategory.addNewExpense(expense);
                    categories.add(foundCategory);
                    System.out.println("Your new expense " + name + " has been added for an amount of " + amount + " into the existing category " + category);
                    System.out.println(foundCategory.getNamesOfExpenses());
                } else {
                    Expense expense = new Expense(amount, name, newCategory);
                    newCategory.addNewExpense(expense);
                    categories.add(newCategory);
                    System.out.println("Your new expense " + name + " has been added for an amount of " + amount + " into the new category " + category);
                    System.out.println(newCategory.getNamesOfExpenses());
                }

            } else if (choice.equals("2")) {
                System.out.println("[1] Enter 1 for Constant Salary [2] Enter 2 for PartTime Income");
                choice = scanner.nextLine();
                if (choice.equals("1")) {
                System.out.println("Add amount of Constant Salary");
                amount = scanner.nextInt();
                scanner.nextLine();
                Salary salary = new ConstantSalary(amount);
                salaries.add(salary);
                System.out.println("Your salary of amount " + amount + " has been added");
                } else if (choice.equals("2")){
                    System.out.println("Add amount of Constant Salary");
                    amount = scanner.nextInt();
                    scanner.nextLine();
                    Salary salary = new PartTimeIncome(amount);
                    salaries.add(salary);
                    System.out.println("Your salary of amount " + amount + " has been added");
                }


            }else if (choice.equals("3")){
                while(true) {
                    System.out.println
                            ("[1] display information about first category in categories " +
                            "[2] display information about the second category in categories " +
                            "[3] break information getting loop and return to normal");
                    choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        System.out.println("The first Category in Categories is called " + categories.get(0).getName());
                        System.out.println("The names of the expenses in this category are " + categories.get(0).getNamesOfExpenses());
                    }else if (choice.equals("2")){
                        System.out.println("The second Category in Categories is called " + categories.get(1).getName());
                        System.out.println("The names of the expenses in this Category are " + categories.get(1).getNamesOfExpenses());
                    }else if (choice.equals("3")){
                        break;
                    }
                }
            }
            if (choice.equals("4")) {
                incomeParser.save(salaries);
                categoryParser.save(categories);
                break;
            }
        }
    }
}

