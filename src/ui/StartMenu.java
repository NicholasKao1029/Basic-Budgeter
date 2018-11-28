package ui;

import Exceptions.IncorrectFigureException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Budgeting.Budget;
import model.Budgeting.Categories;
import model.Budgeting.Salaries;
import model.Category;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartMenu extends Application  {

    private int HSize = 500;
    private int WSize = 800;

    private Stage window;
    private Button ExButton;
    private Button IncButton;
    private Scene mainmenu, ExScene, IncScene;
    private Budget budget = new Budget();
    private AlertBox alert;
    private TreeView<String> categ;

    //REQUIRES: nothing
    //MODIFIES: budget
    //EFFECTS: creates budget and calls startMenu
    public static void main(String[] args) throws IOException, ParseException {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        budget.Initialize();


        TreeItem<String> root;
        root = new TreeItem<>();
        root.setExpanded(true);
        createTree(root);
        categ = new TreeView<>(root);
        categ.setShowRoot(false);

        GridPane tree = new GridPane();
        tree.getChildren().addAll( categ);


        //totals of categories and salaries
        GridPane top = new GridPane();

        Text t = new Text("Categories of Expenses");
        t.setFont(Font.font("Century Gothic", FontWeight.BOLD, 20));
        Text CTotal = initializeCTotal();
        Text STotal = initializeSTotal();
        top.setConstraints(CTotal, 5 ,1 );
        top.setConstraints(STotal, 5,2 );
        top.setConstraints(t,0, 2 );
        top.getChildren().addAll(t, CTotal, STotal);
        top.setPadding(new Insets(10,10,10,10));
        top.setVgap(8);
        top.setHgap(10);




        //netincome
        GridPane mid = new GridPane();

        mid.getChildren().add(initializeNetTotal());
        mid.setPadding(new Insets(40,15,15,15));
        mid.setVgap(8);
        mid.setHgap(10);


        // Layout 1 START MENU
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        window = primaryStage;
        window.setTitle("Budget App");

        ExButton = new Button();
        ExButton.setText("Enter Expense");
        grid.setConstraints(ExButton, 0 ,3);

        IncButton = new Button();
        IncButton.setText("Enter Income");
        grid.setConstraints(IncButton, 0 ,6 );

        ExButton.setOnAction(e -> ExLayout());

        IncButton.setOnAction(e -> IncLayout());

        grid.getChildren().addAll(ExButton, IncButton);

        BorderPane bp = new BorderPane();
        bp.setRight(grid);
        bp.setCenter(mid);
        bp.setTop(top);
        bp.setLeft(tree);


        mainmenu = new Scene(bp, WSize, HSize);
        mainmenu.getStylesheets().add("viper.css");
        window.setScene(mainmenu);
        window.show();




        //TODO: possible things to do
        //TODO: see list of categories in a list kinda thing List view object
        //TODO: when selecting from previous categories a comboBox to a dd a new one and or see alr created ones
        //TODO: find way to load categories as options into the combobox forall loop iterting through the options.
        //

    }

    public void ExLayout(){

        //Layout 2 EXPENSE OPTION

        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10, 10, 10, 10));
        grid1.setVgap(8);
        grid1.setHgap(10);

        TextField name, expense, category;

        name = new TextField();
        expense = new TextField();
        category = new TextField();
        name.setPromptText("Name");
        name.setPrefWidth(400);
        expense.setPromptText("Amount");
        expense.setPrefWidth(400);
        category.setPromptText("Category");
        category.setPrefWidth(400);
        grid1.setConstraints(name, 20, 16);
        grid1.setConstraints(expense, 20, 18);
        grid1.setConstraints(category, 20, 20);


        Button confirm = new Button("Confirm");
        grid1.setConstraints(confirm, 20, 22);
        confirm.setOnAction(e -> expenseChoice(name, expense, category));
        Button goBack = new Button("Return to Main Menu");
        grid1.setConstraints(goBack, 20, 24);
        goBack.setOnAction(e -> window.setScene(mainmenu));
        goBack.getStyleClass().add("button-exit");


        grid1.getChildren().addAll(name, expense, category, confirm, goBack);
        ExScene = new Scene(grid1, WSize, HSize);
        ExScene.getStylesheets().add("viper.css");
        window.setScene(ExScene);
    }

    public void IncLayout(){
        //Layout 3 INCOME OPTION

        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 10, 10, 10));
        grid2.setVgap(8);
        grid2.setHgap(10);

        TextField amount;

        //TODO: need to make sure it's a int value for amount and for amoutn in expense
        //TODO: add functionality to button 2 and 3 for confirmation.

        amount = new TextField();
        amount.setPromptText("Amount");
        amount.setPrefWidth(400);
        grid2.setConstraints(amount, 20, 16);

        Button confirm = new Button("Save");
        grid2.setConstraints(confirm, 20, 18);
        confirm.setOnAction(e -> incomeChoice(amount , amount.getText()));
        Button goBack = new Button("Return to Main Menu");
        grid2.setConstraints(goBack, 20, 20);
        goBack.setOnAction(e -> window.setScene(mainmenu));
        goBack.getStyleClass().add("button-exit");

        grid2.getChildren().addAll(amount, confirm, goBack);
        IncScene = new Scene(grid2, WSize, HSize);
        IncScene.getStylesheets().add("viper.css");
        window.setScene(IncScene);
    }


    public void incomeChoice(TextField input, String text){
            try {
                int amount = Integer.parseInt(text);
                if (amount <= 0){
                    throw new IncorrectFigureException();
                }
                budget.incomeChoice(amount);
                AlertBox.display("Success", "Salary of amount " + amount + " has been added!");
                initializeNetTotal();
                start(window);

            }catch(NumberFormatException e){
                System.out.println("Please insert an integer greater than 0");
                alert.display("Error", "Please insert an integer of amount greater than 0");
            } catch (IncorrectFigureException e) {
                System.out.println("Please add a value greater than 0");
                alert.display("Error", "Please insert an integer for amount");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void expenseChoice(TextField name, TextField expense, TextField category){
        String n = name.getText();
        String a = expense.getText();
        String c = category.getText();

            if (!isValid(a)) {
                alert.display("Error", "Please insert an integer of amount greater than 0");
            }
        else{
                int amount = Integer.parseInt(a);
                budget.expenseChoice(n, amount, c);
                alert.display("Success", "Your new expense " + n + " has been added for an amount of \n" + a + " into the category " + c);
                window.setScene(mainmenu);
                try {
                    start(window);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

    // checks to see if valid int
    public boolean isValid(String figure){
        try {
            int amount = Integer.parseInt(figure);
            if (amount <= 0){
                throw new IncorrectFigureException();
            }
            return true;
        }catch(NumberFormatException e){
            return false;
        } catch (IncorrectFigureException e) {
            return false;
        }

    }

    public Text initializeNetTotal(){

        int net = budget.netIncome();
        String netS = Integer.toString(net);
        String end = "Your net income is " + netS+ " dollars";
        Text T = new Text(end);
        T.setFont(Font.font("Century Gothic", FontWeight.BOLD, 20));

        return T;
    }

    public Text initializeCTotal(){
        Categories categories = budget.getCategories();
        int cTotal = categories.totalExpenses();
        String C = Integer.toString(cTotal);
        String end = "          $ " +C+ " Total Expense";
        Text totalExpenses = new Text(end);
        totalExpenses.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));

        return totalExpenses;

    }
    public Text initializeSTotal(){
        Salaries salaries = budget.getSalaries();
        int sTotal = salaries.total();
        String S = Integer.toString(sTotal);
        String end = "          $ " +S+ " Total Salary";
        Text totalIncome = new Text(end);
        totalIncome.setFont(Font.font("Century Gothic", FontWeight.MEDIUM, 15));

        return totalIncome;

    }

    public void createTree(TreeItem<String> root ){
        List<Category> categories = budget.getCategories().getCategories();
        for(Category c: categories){
            List<String> l = c.getNamesOfExpenses();
            TreeItem<String> n = makeBranch(c.getName(), root);
            for(String s: l){
                makeBranch(s,n);
            }
        }
    }

    //Create branches
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    }

