package ui;

import model.Budget;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class StartMenu {

    //REQUIRES: nothing
    //MODIFIES: budget
    //EFFECTS: creates budget and calls startMenu
    public static void main(String[] args) throws IOException, ParseException {
        //possible place for load method
        Budget budget = new Budget();
        budget.startMenu();
    }
}
