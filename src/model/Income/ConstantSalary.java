package model.Income;

import Interfaces.Income;

public class ConstantSalary extends Salary implements Income{

    private String date;

    public ConstantSalary(int amount) {
        super(amount);
    }

    @Override
    public int annualIncome(int amount, String time)
    {
        if (time == "daily") {
            return amount * 365;
        } else if (time == "monthly") {
            return amount * 12;
        } else
            return amount;
    }
}
