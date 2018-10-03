package model.Files;

import model.Expense;

import java.io.*;
import java.lang.*;
import java.util.*;

public class CreateFile {

    private Formatter x;

    public void openFile(){
        try{
            x = new Formatter("input.txt");
        }
        catch(Exception e){
            System.out.println("you have an error");
        }
    }

    public void addExpenseRecords(Expense e){
        x.format("%s%s%s", e.getName(), e.getCost(), e.getCategory().getName() );
    }


    public void closeFile(){
        x.close();
    }
}