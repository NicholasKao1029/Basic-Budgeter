package model;

import Interfaces.CashFlow;

public class Expense implements CashFlow {

    private int cost;
    private String name;
    private Category category;

    public Expense(int cost, String name, Category category) {
        this.cost = cost;
        this.name = name;
        this.category = category;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns cost of Expense
    public int getCost(){
        return this.cost;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns category of Expense
    public String getName(){
        return this.name;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the Category
    public Category getCategory() {
        return category;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns true if e is same as expense
    public boolean compareExpense(Expense e) {
        if ((this.getCost() ==e.getCost()) && (this.getName() == e.getName()) && (this.getCategory() == e.getCategory())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isItEnough(int n) {
        return (this.getCost() >= n) ;
    }
}
