package model;

import Interfaces.CashFlow;
import Interfaces.Income;

public class Salary implements CashFlow, Income {

    private int amount;

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
}
// ideas: category as well