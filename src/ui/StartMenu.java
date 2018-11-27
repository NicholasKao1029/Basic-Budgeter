package ui;

import Exceptions.IncorrectFigureException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Budgeting.Budget;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class StartMenu extends Application  {

    private int HSize = 800;
    private int WSize = 900;

    private Stage window;
    private Button ExButton;
    private Button IncButton;
    private Scene mainmenu, ExScene, IncScene;
    private ErrorBox alert;
    private Budget budget = new Budget();

    //REQUIRES: nothing
    //MODIFIES: budget
    //EFFECTS: creates budget and calls startMenu
    public static void main(String[] args) throws IOException, ParseException {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        budget.Initialize();

        // Layout 1 START MENU
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        window = primaryStage;
        window.setTitle("Budget App");

        ExButton = new Button();
        ExButton.setText("Enter Expense");
        grid.setConstraints(ExButton, 0 ,0 );

        IncButton = new Button();
        IncButton.setText("Enter Income");
        grid.setConstraints(IncButton, 0 ,1 );

        ExButton.setOnAction(e -> ExLayout());

        IncButton.setOnAction(e -> IncLayout());

        grid.getChildren().addAll(ExButton, IncButton);
        mainmenu = new Scene(grid, WSize, HSize);
        window.setScene(mainmenu);
        window.show();

        //TODO: need to make sure it's a int value for amount and for amount in expense
        //TODO: add functionality to button 2 and 3 for confirmation.



        //TODO: possible things to do
        //TODO: see list of categories in a list kinda thing List view object
        //TODO: when selecting from previous categories a comboBox to add a new one and or see alr created ones
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
        expense.setPromptText("Amount");
        category.setPromptText("Category");
        grid1.setConstraints(name, 1, 0);
        grid1.setConstraints(expense, 1, 2);
        grid1.setConstraints(category, 1, 4);

        Button button2 = new Button("Confirm");
        grid1.setConstraints(button2, 1, 6);
        button2.setOnAction(e -> System.out.println("asdf"));


        grid1.getChildren().addAll(name, expense, category, button2);
        ExScene = new Scene(grid1, WSize, HSize);
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
        grid2.setConstraints(amount, 1, 4);

        Button confirm = new Button("Confirm");
        grid2.setConstraints(confirm, 1, 6);
        confirm.setOnAction(e -> isInt(amount , amount.getText()));



        grid2.getChildren().addAll(amount, confirm);
        IncScene = new Scene(grid2, WSize, HSize);
        window.setScene(IncScene);
    }

    public void isInt(TextField input, String text){
            try {
                int amount = Integer.parseInt(text);
                if (amount <= 0){
                    throw new IncorrectFigureException();
                }
                budget.incomeChoice(amount);
            }catch(NumberFormatException e){
                System.out.println("Please insert an integer greater than 0");
                alert.display("Error", "Please insert an integer for amount greater than 0");
            } catch (IncorrectFigureException e) {
                System.out.println("Please add a value greater than 0");
                alert.display("Error", "Please insert an integer for amount");
            }
    }

}
