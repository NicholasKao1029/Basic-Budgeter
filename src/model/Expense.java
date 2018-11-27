package model;

import Interfaces.CashFlow;

public class Expense implements CashFlow{

    private int amount;
    private String description;
    private Category category;
    private int date;

    public Expense(int amount, String description, Category category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns amount of Expense
    public int getAmount(){
        return this.amount;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns category of Expense
    public String getDescription(){
        return this.description;
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
        if ((this.getAmount() ==e.getAmount()) && (this.getDescription() == e.getDescription()) && (this.getCategory() == e.getCategory())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isItEnough(int n) {
        return (this.getAmount() >= n) ;
    }
}
