package ui;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget {

    private List<Expense> expenses;
    private List<Income> incomes;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Budget budget = new Budget();

        budget.startMenu();
    }

    public void startMenu() {
        String choice = "";
        String name = "";
        String category = "";
        int amount = 0;
        while (true) {
            System.out.println(" What would you like to do " +
                    "[1] Enter Expense" +
                    "[2] Enter Income" +
                    "[3] Check Balance" +
                    "[4] Quit");
            choice = scanner.nextLine();
            System.out.println("you have selected: " + choice);
            if (choice.equals("1")) {
                System.out.println("Add name of expense");
                name = scanner.nextLine();
                System.out.println("Add amount");
                amount = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Add category");
                category = scanner.nextLine();
                System.out.println("Your new expense " + name + " has been added for an amount of " + amount + " into the category " + category);

            } else if (choice.equals("4")) {
                break;
            }
        }
    }
}