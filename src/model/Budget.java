package model;

import model.Files.CreateFile;

import java.util.List;
import java.util.Scanner;


public class Budget {

    private List<Category> categories;
    private List<Salary> salaries;
    Scanner scanner = new Scanner(System.in);

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: initiates startMenu options to add expense

    public void startMenu() {
        // referenced frorm LittleLoggingCalculator
        String choice = "";
        String name = "";
        String category = "";
        int amount = 0;
        CreateFile file = new CreateFile();
        while (true) {
            System.out.println(" What would you like to do " +
                    "[1] Enter 1 for Expense" +
                    "[2] Enter 2 for Salary" +
                    "[3] Enter 3 for Check Balance" +
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
//                    file.addExpenseRecords(expense);
                    System.out.println("Your new expense " + name + " has been added for an amount of " + amount + " into the category " + category);
                } else {
                    Expense expense = new Expense(amount, name, newCategory);
                    newCategory.addNewExpense(expense);
//                    file.addExpenseRecords(expense);
                    System.out.println("Your new expense " + name + " has been added for an amount of " + amount + " into the new category " + category);
                }

            } else if (choice.equals("2")) {
                System.out.println("Add amount of Salary");
                amount = scanner.nextInt();
                scanner.nextLine();
                Salary salary = new Salary(amount);
                salaries.add(salary);
                System.out.println("Your salary of amount " + amount + "has been added");

            }

            if (choice.equals("4")) {
//                savefile();
            }
            break;

            //REQUIRES: nothing
            //MODIFIES: file
            //EFFECTS: saves all of the newly added Categories and expenses from expenses
//        public void savefile(){
//            for (Category c : categories) {
//                for (Expense e : c.getExpenses()) {
//                    file.addExpenseRecords(e);
//                }
//            }
//            file.closeFile();
//        }
        }
    }
}

