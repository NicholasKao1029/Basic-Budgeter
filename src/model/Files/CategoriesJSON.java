package model.Files;

import model.Category;
import model.Expense;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CategoriesJSON {
    public void save(List<Category> categories) {
        JSONObject obj = new JSONObject();
        for (Category category : categories) {
            JSONArray expenses = new JSONArray();
            for (Expense expense : category.getExpenses()) {
                JSONObject expenseObj = new JSONObject();
                expenseObj.put("name", expense.getDescription());
                System.out.println(expense.getDescription());
                expenseObj.put("cost", expense.getAmount());
                System.out.println(expense.getAmount());
                expenses.add(expenseObj);
            }
            obj.put(category.getName(), expenses);
        }

        try (FileWriter file = new FileWriter("src\\model\\Files\\categories.json")) {
            file.write(obj.toJSONString());
            System.out.println("\nJSON Object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Category> parseCategories() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src\\model\\Files\\categories.json"));
        JSONObject jsonObject = (JSONObject) obj;
        List<Category> categories = new ArrayList<>();

        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            Category c1 = new Category(key);
            Object o1 = jsonObject.get(key);
            JSONArray o2 = (JSONArray) o1;
            for (Object o : o2) {
                JSONObject jsonObject1 = (JSONObject) o;
                String name = (String) jsonObject1.get("name");
                int cost =  ((Number) jsonObject1.get("cost")).intValue();
                Expense expense = new Expense(cost, name, c1);
                c1.addNewExpense(expense);

            }
            categories.add(c1);
        }
        return categories;
    }


    public void saveTest(List<Category> categories) {
        JSONObject obj = new JSONObject();
        for (Category category : categories) {
            JSONArray expenses = new JSONArray();
            for (Expense expense : category.getExpenses()) {
                JSONObject expenseObj = new JSONObject();
                expenseObj.put("name", expense.getDescription());
                System.out.println(expense.getDescription());
                expenseObj.put("cost", expense.getAmount());
                System.out.println(expense.getAmount());
                expenses.add(expenseObj);
            }
            obj.put(category.getName(), expenses);
        }

        try (FileWriter file = new FileWriter("src\\Test\\TestCategories.json")) {
            file.write(obj.toJSONString());
            System.out.println("\nJSON Object: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> parseCategoriesTest() throws IOException, ParseException {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src\\Test\\TestCategories.json"));
            JSONObject jsonObject = (JSONObject) obj;
            List<Category> categories = new ArrayList<>();

            for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                Category c1 = new Category(key);
                Object o1 = jsonObject.get(key);
                JSONArray o2 = (JSONArray) o1;
                for (Object o : o2) {
                    JSONObject jsonObject1 = (JSONObject) o;
                    String name = (String) jsonObject1.get("name");
                    int cost =  ((Number) jsonObject1.get("cost")).intValue();
                    Expense expense = new Expense(cost, name, c1);
                    c1.addNewExpense(expense);

                }
                categories.add(c1);
            }
            return categories;
        }
}


