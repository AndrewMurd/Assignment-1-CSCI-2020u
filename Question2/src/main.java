/*
    Andrew Murdoch
    100707816

    Question2: This program calculates the future value of an investement through inputing Investment Amount, Years and Annual Interest Rate.
    Outputing the future value of the investement.
*/
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();

        //Set distance between columns and rows
        grid.setHgap(10);
        grid.setVgap(5);

        //Initialize the text
        Text IAtxt = new Text("Investment Amount");
        Text Yearstxt = new Text("Years");
        Text AIRtxt = new Text("Annual Interest Rate");
        Text FVtxt = new Text("Future Value");

        //Initialize text fields
        TextField IAf = new TextField();
        TextField Yearsf = new TextField();
        TextField AIRf = new TextField();
        TextField FVf = new TextField();
        //Set text fields text to the right
        Yearsf.setAlignment(Pos.CENTER_RIGHT);
        AIRf.setAlignment(Pos.CENTER_RIGHT);
        FVf.setAlignment(Pos.CENTER_RIGHT);
        IAf.setAlignment(Pos.CENTER_RIGHT);

        //Initialize calculate button
        Button Calculate  = new Button("Calculate");
        Calculate.setPrefWidth(100);
        //handle button
        Calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Formula to calculate future value of investement
                double futureValue = Double.valueOf(IAf.getText()) * Math.pow(1 + (Double.valueOf(AIRf.getText())/100/12), Double.valueOf(Yearsf.getText()) * 12);
                //Format Output
                DecimalFormat df = new DecimalFormat("#.##");
                FVf.setText(String.valueOf(df.format(futureValue)));
            }
        });

        grid.add(IAtxt, 1, 2);
        grid.add(Yearstxt, 1, 4);
        grid.add(AIRtxt, 1, 6);
        grid.add(FVtxt, 1, 8);

        grid.add(IAf, 2, 2);
        grid.add(Yearsf, 2, 4);
        grid.add(AIRf, 2, 6);
        grid.add(FVf, 2, 8);

        grid.add(Calculate, 2, 9);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}