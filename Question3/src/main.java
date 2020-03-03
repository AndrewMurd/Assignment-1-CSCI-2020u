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
    double radius = 10;

    Circle[] circles = {new Circle(0, 0, 6),
            new Circle(0, 0, 6), new Circle(0, 0, 6)};

    Line line1 = new Line();
    Line line2 = new Line();
    Line line3 = new Line();
    Text[] text = {new Text(), new Text(), new Text()};

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        setLines();

        Circle circle = new Circle(200, 200, 100);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        for (int i = 0; i < 3; i++){
            getRandLoc(circles[i], circle);
            circles[i].setFill(Color.RED);
        }

        pane.getChildren().addAll(circle, line1, line2, line3, text[0], text[1], text[2], circles[0], circles[1], circles[2]);

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        setLines();

        circles[0].setOnMouseDragged(e -> {
            if (circles[0].contains(e.getX(), e.getY())) {
                double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

                double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
                double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

                circles[0].setCenterX(x_cod);
                circles[0].setCenterY(y_cod);

                setLines();
            }
        });

        circles[1].setOnMouseDragged(e -> {
            if (circles[1].contains(e.getX(), e.getY())) {
                double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

                double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
                double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

                circles[1].setCenterX(x_cod);
                circles[1].setCenterY(y_cod);
                setLines();
            }
        });

        circles[2].setOnMouseDragged(e -> {
            if (circles[2].contains(e.getX(), e.getY())) {
                double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

                double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
                double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

                circles[2].setCenterX(x_cod);
                circles[2].setCenterY(y_cod);
                setLines();
            }
        });
    }

    private void setLines() {
        line1.setStartX(circles[0].getCenterX());
        line1.setStartY(circles[0].getCenterY());
        line1.setEndX(circles[1].getCenterX());
        line1.setEndY(circles[1].getCenterY());
        line2.setStartX(circles[0].getCenterX());
        line2.setStartY(circles[0].getCenterY());
        line2.setEndX(circles[2].getCenterX());
        line2.setEndY(circles[2].getCenterY());
        line3.setStartX(circles[1].getCenterX());
        line3.setStartY(circles[1].getCenterY());
        line3.setEndX(circles[2].getCenterX());
        line3.setEndY(circles[2].getCenterY());


        double a = new Point2D(circles[2].getCenterX(), circles[2].getCenterY()).
                distance(circles[1].getCenterX(), circles[1].getCenterY());
        double b = new Point2D(circles[2].getCenterX(), circles[2].getCenterY()).
                distance(circles[0].getCenterX(), circles[0].getCenterY());
        double c = new Point2D(circles[1].getCenterX(), circles[1].getCenterY()).
                distance(circles[0].getCenterX(), circles[0].getCenterY());
        double[] angle = new double[3];
        angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
        angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
        angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        for (int i = 0; i < 3; i++) {
            text[i].setX(circles[i].getCenterX());
            text[i].setY(circles[i].getCenterY() - radius);
            text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
        }
    }

    public void getRandLoc(Circle point, Circle circle){
        double angle = Math.random() * 360;
        double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
        double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));
        point.setCenterX(x_cod);
        point.setCenterY(y_cod);
    }

    public static void main(String[] args) { launch(args); }
}