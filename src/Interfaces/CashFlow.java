package Interfaces;

public interface CashFlow {

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns true if given CashFlow amount is larger than n, if not returns false
    public boolean isItEnough(int n);

}
