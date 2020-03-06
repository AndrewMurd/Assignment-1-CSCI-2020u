/*
    Andrew Murdoch
    100707816

    Question3: This program creates a circle with three randomly generates points on the perimeter.
    The points are able to move by dragging by mouse. Between the points the points lines are generated and angles are showed.
*/
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {

    //Initialize points for perimeter
    Circle[] circlePoints = {new Circle(0, 0, 6), new Circle(0, 0, 6), new Circle(0, 0, 6)};
    //Initialize lines between points
    Line line1 = new Line();
    Line line2 = new Line();
    Line line3 = new Line();
    Text[] text = {new Text(), new Text(), new Text()};

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        //Initialize circle
        Circle circle = new Circle(200, 200, 100);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        //Initialize random locations for points and set color to red
        for (int i = 0; i < 3; i++){
            getRandLoc(circlePoints[i], circle);
            circlePoints[i].setFill(Color.RED);
        }
        //Call function to initialize line locations and angles
        setLines();

        //Handle first point
        circlePoints[0].setOnMouseDragged(e -> {
            //Get the counterclockwise angle in radians at point x, y from the x axis
            double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
            //Get current x,y position and add the required angle to move along the radius of the circle
            double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
            double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);
            //Set new x,y coordinates
            circlePoints[0].setCenterX(x_cod);
            circlePoints[0].setCenterY(y_cod);
            //Update angle and lines
            setLines();
        });

        circlePoints[1].setOnMouseDragged(e -> {
            double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

            double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
            double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

            circlePoints[1].setCenterX(x_cod);
            circlePoints[1].setCenterY(y_cod);
            setLines();
        });

        circlePoints[2].setOnMouseDragged(e -> {
            double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

            double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
            double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

            circlePoints[2].setCenterX(x_cod);
            circlePoints[2].setCenterY(y_cod);
            setLines();
        });

        pane.getChildren().addAll(circle, line1, line2, line3, text[0], text[1], text[2], circlePoints[0], circlePoints[1], circlePoints[2]);

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setLines() {

        //Initialize 2D points for angle calculations
        double a = new Point2D(circlePoints[2].getCenterX(), circlePoints[2].getCenterY()).distance(circlePoints[1].getCenterX(), circlePoints[1].getCenterY());
        double b = new Point2D(circlePoints[2].getCenterX(), circlePoints[2].getCenterY()).distance(circlePoints[0].getCenterX(), circlePoints[0].getCenterY());
        double c = new Point2D(circlePoints[1].getCenterX(), circlePoints[1].getCenterY()).distance(circlePoints[0].getCenterX(), circlePoints[0].getCenterY());

        //Calculate Angles
        double[] angle = new double[3];
        angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
        angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
        angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        //Display angles
        for (int i = 0; i < 3; i++) {
            text[i].setX(circlePoints[i].getCenterX());
            text[i].setY(circlePoints[i].getCenterY() - 10);
            text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
        }

        //Set Start and End x,y coordinates of lines
        line1.setStartX(circlePoints[0].getCenterX());
        line1.setStartY(circlePoints[0].getCenterY());
        line1.setEndX(circlePoints[1].getCenterX());
        line1.setEndY(circlePoints[1].getCenterY());
        line2.setStartX(circlePoints[0].getCenterX());
        line2.setStartY(circlePoints[0].getCenterY());
        line2.setEndX(circlePoints[2].getCenterX());
        line2.setEndY(circlePoints[2].getCenterY());
        line3.setStartX(circlePoints[1].getCenterX());
        line3.setStartY(circlePoints[1].getCenterY());
        line3.setEndX(circlePoints[2].getCenterX());
        line3.setEndY(circlePoints[2].getCenterY());
    }

    //Get random x,y coordinates along the perimeter of circle
    public void getRandLoc(Circle point, Circle circle){
        double angle = Math.random() * 360;
        double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
        double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));
        point.setCenterX(x_cod);
        point.setCenterY(y_cod);
    }

    public static void main(String[] args) { launch(args); }
}