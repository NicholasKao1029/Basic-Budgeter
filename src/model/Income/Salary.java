package model.Income;

import Interfaces.CashFlow;
import Interfaces.Income;

public abstract class Salary implements CashFlow, Income {

    protected int amount;

    public Salary(int amount) {
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }


    @Override
    public boolean isItEnough( int n) {
        return (this.getAmount() >= n) ;
    }

    @Override
    public int surplus(int n) {
        return (getAmount() - n);
    }

    //REQUIRES: a time of either daily, monthly or yearly
    //MODIFIES: nothing
    //EFFECTS: returns the annual gained dependent on the time periods in which the amount is gotten
    public abstract int annualIncome(int amount,String time);
}

// ideas: category as well