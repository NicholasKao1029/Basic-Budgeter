package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {

    private List<Expense> expenses;
    private String name;

    public Category(String name){
        this.name = name;
        this.expenses = new ArrayList<Expense>();
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns name of category
    public String getName(){
        return this.name;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns list of Expense
    public List<Expense> getExpenses(){
        return this.expenses;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds an expense to expenses
    public void addNewExpense(Expense expense){
        this.expenses.add(expense);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: totals all expenses in expenses
    public int totalExpenses(){
        int total = 0;
        for(Expense e: this.expenses){
           total = total + e.getCost();
        }
        return total;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: returns true if expenses in category are same as c
    public Expense getFirstExpense(){
        return this.expenses.get(0);
    }



    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: when Category is being filtered use Name too compare values rather than Category as a whole
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
