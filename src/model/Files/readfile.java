package model.Files;

import java.io.*;
import java.lang.*;
import java.util.*;

public class readfile {

    private Scanner x;

    public void openFile(){
        try{
            x = new Scanner("input.txt");
        }
        catch(Exception e){
            System.out.println("file not found");
        }
    }

    public void readFile(){
        while(x.hasNext()){
            String name = x.next();
            String cost = x.next();
            String category = x.next();

            System.out.printf("%s %s %s\n", name,cost,category);

        }
    }

    public void closeFile(){
        x.close();
    }


}

