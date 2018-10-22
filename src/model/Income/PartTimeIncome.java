package model.Income;

import Interfaces.Income;

public class PartTimeIncome extends Salary implements Income {

    public PartTimeIncome(int amount) {
        super(amount);
    }

    @Override
    public int annualIncome(int amount, String time) {
        return amount;
    }
}
