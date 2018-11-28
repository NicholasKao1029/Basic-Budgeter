package model.Budgeting;

import Exceptions.IncorrectFigureException;
import Observe.MyObserver;
import Observe.Subject;
import model.Category;
import model.Expense;
import model.Files.CategoriesJSON;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Categories extends Subject {

    CategoriesJSON categoryParser = new CategoriesJSON();
    private List<Category> categories = categoryParser.parseCategories();
    private Scanner scanner = new Scanner(System.in);


    public Categories() throws IOException, ParseException {
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: Adds an expense to expense and if category exists adds it to that category, if not new category is created
    public void expenseChoice (String name, int amount, String category){
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

    public List<Category> getCategories(){
        return categories;
    }

    public void save (){
        categoryParser.save(categories);
    }

    public void notify(MyObserver observer){
        notifyObservers(observer);
    }

    public int totalExpenses(){
        int total = 0;
        for(Category c: categories){
            total += c.totalExpenses();
        }
        return total;
    }
}
