package model.Budgeting;

//import model.Files.CreateFile;
import Exceptions.zeroDollarException;
import model.Category;
import model.Expense;
import model.FileParser.CategoriesJSON;
import model.FileParser.IncomeJSON;
import model.Income.ConstantSalary;
import model.Income.Salary;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Budget {

    private List<Category> categories;
    private List<Salary> salaries;
    Scanner scanner = new Scanner(System.in);
    CategoriesJSON categoryParser = new CategoriesJSON();
    IncomeJSON incomeParser = new IncomeJSON();

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: initiates startMenu options to add expense

    public void startMenu() throws IOException, ParseException {
        // referenced from LittleLoggingCalculator
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
                expenseChoice(name, amount, scanner, category);
            } else if (choice.equals("2")) {
                incomeChoice(amount);

            } else if (choice.equals("3")){
                diagnostics(choice);
            }
            if (choice.equals("4")) {
                incomeParser.save(salaries);
                categoryParser.save(categories);
                break;
            }
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Adds an expense to expense and if category exists adds it to that category, if not new category is created
    public void expenseChoice (String name, int amount, Scanner scanner, String category){
            System.out.println("Add name of expense");
            name = scanner.nextLine();
            System.out.println("Add amount");
            while (true){
                try {
                    if ((amount = scanner.nextInt()) == 0) {
                        throw new zeroDollarException();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please insert an integer for amount");
                    scanner.nextLine();
                } catch (zeroDollarException e){
                    System.out.println("Please add an expense greater than 0");
                    scanner.nextLine();
                } finally{
                    System.out.println("Thanks for using expense feature!");
                }
            }
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
                        throw new zeroDollarException();
                    }
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Please insert an integer for amount");
                    scanner.nextLine();
                } catch (zeroDollarException e) {
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

    public int netIncome(){
        int totalExpense = 0;
        int totalIncome = 0;
        for(Category category: categories){
            totalExpense = totalExpense + category.totalExpenses();
        }
        for(Salary salary: salaries){
            totalIncome = totalIncome + salary.getAmount();
        }
        return (totalExpense - totalIncome);
    }
}

