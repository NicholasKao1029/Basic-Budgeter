package model.Files;


import model.Income.PartTimeIncome;
import model.Income.Salary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IncomeJSON {

    public void save(List<Salary> salaries) {
        JSONObject obj = new JSONObject();
        JSONArray sal = new JSONArray();
        for (Salary salary : salaries) {
            JSONObject salaryObj = new JSONObject();
            salaryObj.put("amount", salary.getAmount());
            System.out.println(salary.getAmount());
            sal.add(salaryObj);
        }

        obj.put("Salaries", sal);

        try (FileWriter file = new FileWriter("src\\model\\Files\\incomes.json")) {
            file.write(obj.toJSONString());
            System.out.println("\nJSON Object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Salary> parseIncome() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src\\model\\Files\\incomes.json"));
        JSONObject jsonObject = (JSONObject) obj;
        List<Salary> salaries = new ArrayList<>();

        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            Object o1 = jsonObject.get(key);
            JSONArray o2 = (JSONArray) o1;
            for (Object o : o2) {
                JSONObject jsonobject1 = (JSONObject) o;
                int amount = ((Number) jsonobject1.get("amount")).intValue();
                Salary salary = new PartTimeIncome(amount);
                salaries.add(salary);
            }
        }
        return salaries;
    }






    public void saveTest (List<Salary> salaries) {
        JSONObject obj = new JSONObject();
        JSONArray sal = new JSONArray();
        for (Salary salary : salaries) {
            JSONObject salaryObj = new JSONObject();
            salaryObj.put("amount", salary.getAmount());
            System.out.println(salary.getAmount());
            sal.add(salaryObj);
        }

        obj.put("Salaries", sal);

        try (FileWriter file = new FileWriter("src\\Test\\TestIncome.json")) {
            file.write(obj.toJSONString());
            System.out.println("\nJSON Object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Salary> parseIncomeTest() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src\\Test\\TestIncomes.json"));
        JSONObject jsonObject = (JSONObject) obj;
        List<Salary> salaries = new ArrayList<>();

        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            Object o1 = jsonObject.get(key);
            JSONArray o2 = (JSONArray) o1;
            for (Object o : o2) {
                JSONObject jsonobject1 = (JSONObject) o;
                int amount = ((Number) jsonobject1.get("amount")).intValue();
                Salary salary = new PartTimeIncome(amount);
                salaries.add(salary);
            }
        }
        return salaries;
    }
}

