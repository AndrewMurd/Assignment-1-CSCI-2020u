/*
    Andrew Murdoch
    100707816

    Question1: Display three cards randomly from all 54 cards.
*/
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        GridPane grid = new GridPane();

        int rand1 = (int)(Math.random()*((54-1)+1))+1;
        int rand2 = (int)(Math.random()*((54-1)+1))+1;
        int rand3 = (int)(Math.random()*((54-1)+1))+1;

        String InputDir1 = "/Cards/" + rand1 + ".png";
        String InputDir2 = "/Cards/" + rand2 + ".png";
        String InputDir3 = "/Cards/" + rand3 + ".png";

        Image image1 = new Image(InputDir1);
        ImageView card1 = new ImageView(image1);

        Image image2 = new Image(InputDir2);
        ImageView card2 = new ImageView(image2);

        Image image3 = new Image(InputDir3);
        ImageView card3 = new ImageView(image3);

        grid.add(card1, 10, 10, 10, 10);
        grid.add(card2, 50, 10, 10, 10);
        grid.add(card3, 100, 10, 10, 10);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }
}