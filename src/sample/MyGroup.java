package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

/**
 * Created by admin on 17.10.17.
 */
public class MyGroup extends Group {
    Label text = new Label();
    Rectangle rectangle = new Rectangle();



    public MyGroup(String text, double x, double y, double size, int type) {
        this.text.setText(text);
        this.rectangle.setLayoutX(x);
        this.rectangle.setLayoutY(y);
        this.rectangle.setHeight(size);
        this.rectangle.setWidth(size*2);

        rectangle.setStroke(Color.BLACK);
        this.text.layoutXProperty().bind(this.rectangle.layoutXProperty().add(size*0.7-text.length()*6));
        this.text.layoutYProperty().bind(this.rectangle.layoutYProperty().add(size/2.9));

        switch (type){
            case 1:{
                typeOne(size);
                break;

            }
            case 2:{
                typeTwo(size);
                break;
            }
            case 3:{
                typeThree();
                break;
            }
        }

        makeDrag();
    }

    private void typeThree() {
        rectangle.setFill(Color.YELLOW);
        super.getChildren().removeAll();
        super.getChildren().addAll( this.rectangle, this.text);
    }

    private void typeTwo(double size) {
        Line line = new Line();
        line.startXProperty().bind(rectangle.layoutXProperty().add(5));
        line.startYProperty().bind(rectangle.layoutYProperty());
        line.endXProperty().bind(rectangle.layoutXProperty().add(5));
        line.endYProperty().bind(rectangle.layoutYProperty().add(size));
        rectangle.setFill(Color.CYAN);

        super.getChildren().removeAll();
        super.getChildren().addAll( this.rectangle, this.text,  line);
    }

    private void typeOne(double size) {
        Line line1 = new Line();
        line1.startXProperty().bind(rectangle.layoutXProperty().add(5));
        line1.startYProperty().bind(rectangle.layoutYProperty());
        line1.endXProperty().bind(rectangle.layoutXProperty().add(5));
        line1.endYProperty().bind(rectangle.layoutYProperty().add(size));
        Line line2 = new Line();
        line2.startXProperty().bind(rectangle.layoutXProperty().add(10));
        line2.startYProperty().bind(rectangle.layoutYProperty());
        line2.endXProperty().bind(rectangle.layoutXProperty().add(10));
        line2.endYProperty().bind(rectangle.layoutYProperty().add(size));
        Line line3 = new Line();
        line3.startXProperty().bind(rectangle.layoutXProperty().add(size*2-5));
        line3.startYProperty().bind(rectangle.layoutYProperty());
        line3.endXProperty().bind(rectangle.layoutXProperty().add(size*2-5));
        line3.endYProperty().bind(rectangle.layoutYProperty().add(size));
        Line line4 = new Line();
        line4.startXProperty().bind(rectangle.layoutXProperty().add(size*2-10));
        line4.startYProperty().bind(rectangle.layoutYProperty());
        line4.endXProperty().bind(rectangle.layoutXProperty().add(size*2-10));
        line4.endYProperty().bind(rectangle.layoutYProperty().add(size));
        rectangle.setFill(Color.LIGHTBLUE);
        super.getChildren().removeAll();
        super.getChildren().addAll( this.rectangle, this.text,  line1, line2, line3, line4);
    }

    private final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    private void makeDrag() {
        super.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        super.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            super.setLayoutX(super.getLayoutX() + deltaX);
            super.setLayoutY(super.getLayoutY() + deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

        });
        text.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Split");
            dialog.setHeaderText("After how many characters should be splitted?");
            Optional<String> result = dialog.showAndWait();
            if(result.get().length()<45) {
                if (result.get().length() > 15) {
                    if (result.get().length() >= 30) {
                        text.setText(result.get().substring(0, 15) + "\n" + result.get().substring(15, 30) + "\n" + result.get().substring(30));
                    } else {
                        text.setText(result.get().substring(0, 15) + "\n" + result.get().substring(15));
                    }
                } else if (result.get().length() < 15) {
                    text.setText(result.get());
                }
            }

        });

    }
}
