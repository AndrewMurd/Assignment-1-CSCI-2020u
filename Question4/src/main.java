import com.sun.marlin.stats.Histogram;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Group group = new Group();

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(550, 500);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        int[] charCount = new int[26];
        char[] chars = new char[26];
        final int sz = charCount.length;

        for (int j = 0; j < sz; j++){
            chars[j] = (char) ('A' + j);
        }

        TextField fileF = new TextField("Example: src/txt.txt");
        fileF.setLayoutX(90);
        fileF.setLayoutY(450);
        fileF.setPrefWidth(350);

        Text label = new Text("Filename: ");
        label.setX(20);
        label.setY(465);

        Text error = new Text();
        error.setX(90);
        error.setY(440);

        Button View  = new Button("View");
        View.setPrefWidth(70);
        View.setLayoutX(450);
        View.setLayoutY(450);

        group.getChildren().addAll(canvas, View, fileF, label, error);

        gc.strokeLine(20, 401, 533, 401);

        View.setOnAction(e -> {
            for (int j = 0; j < sz; j++){
                charCount[j] = 0;
            }

            gc.clearRect(0, 0, 550, 400);

            String txt = null;
            try {
                txt = countChars(fileF, error);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            for (int j = 0; j < txt.length(); j++){
                char ch = txt.charAt(j);
                if (ch >= 'A' && ch <= 'Z'){
                    charCount[ch - 'A']++;
                } else if (ch >= 'a' && ch <= 'z'){
                    charCount[ch - 'a']++;
                }
            }

            int widthBarGraph = 20;
            int factor = 1;
            for (int l = 0; l < sz; l++) {
                System.out.print(chars[l] + " : " + charCount[l] + " ");

                gc.setFill(Color.BLACK);
                gc.fillRect(widthBarGraph, (400 - charCount[l] * factor), 12, charCount[l] * factor);

                gc.setFill(Color.WHITE);
                gc.fillRect(widthBarGraph + 1, (400 - charCount[l] * factor) + 1, 10, (charCount[l] * factor) - 1);

                widthBarGraph += 20;
            }
            System.out.print("\n");
        });

        int charWidth = 22;
        for (int o = 0; o < sz; o++){
            String charsString = "";
            charsString += chars[o];
            Text xAxis = new Text(charsString);
            xAxis.setX(charWidth);
            xAxis.setY(415);
            charWidth += 20;
            group.getChildren().addAll(xAxis);
        }

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }

    public String countChars(TextField field, Text err) throws IOException {
        String fileName = "src/txt.txt";
        String everything = "";

        fileName = field.getText();

        File file = new File(fileName);
        if (file.exists()){
            FileReader fr = new FileReader(fileName);

            int i;
            while ((i=fr.read()) != -1){
                if ((char)i != ' '){
                    everything += (char)i;
                }
            }
            err.setText("");
            return everything;
        } else{
            err.setText("Error: File path not found!!!");
        }
        return "";
    }
}