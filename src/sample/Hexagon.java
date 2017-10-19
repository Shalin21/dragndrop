package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * Created by admin on 09.10.17.
 */
public class Hexagon extends Polygon {
    DoubleProperty centerXProperty = new SimpleDoubleProperty();
    DoubleProperty centerYProperty = new SimpleDoubleProperty();
    DoubleProperty sizeProperty = new SimpleDoubleProperty();
    Circle top = new Circle();
    Circle bot = new Circle();
    Circle left = new Circle();
    Circle right = new Circle();
    Label position = new Label();
    public Hexagon(Point2D center, int size) {

        position.setLayoutX(center.getX());
        position.setLayoutY(center.getY());
        super.fillProperty().setValue(Color.AQUAMARINE);

        super.getPoints().addAll(new Double[]{
                center.getX()-2*size, center.getY()-size,
                center.getX()+2*size, center.getY()-size,
                center.getX()+3*size, center.getY(),
                center.getX()+2*size, center.getY()+size,
                center.getX()-2*size, center.getY()+size,
                center.getX()-3*size, center.getY(),
        });
        top = new Circle(center.getX(), center.getY()-size, 4, Color.RED);
        bot = new Circle(center.getX(), center.getY()+size, 4, Color.RED);
        left = new Circle(center.getX()-size*3, center.getY(), 4, Color.RED);
        right = new Circle(center.getX()+size*3, center.getY(), 4, Color.RED);
        this.centerXProperty.set(center.getX());
        this.centerYProperty.set(center.getY());
        this.sizeProperty.set(size);
        makeDrag();

    }
    private final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    private void makeDrag(){
       super.setOnMousePressed(event -> {
           mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
       });
        super.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            super.setLayoutX(super.getLayoutX()+deltaX);
            super.setLayoutY(super.getLayoutY()+deltaY);
          // centerXProperty= new SimpleDoubleProperty(super.getPoints().get(0)+2*sizeProperty.get());
           //centerYProperty= new SimpleDoubleProperty(super.getPoints().get(1)+sizeProperty.get());

            centerXProperty.set(event.getSceneX());
            centerYProperty.set(event.getSceneY());

            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

            position.setText(super.getPoints().get(0)+":"+super.getPoints().get(1));
            position.layoutXProperty().bindBidirectional(centerXProperty);
            position.layoutYProperty().bindBidirectional(centerYProperty);
        });

        //DoubleProperty size = new SimpleDoubleProperty(sizeProperty.getValue()*3);
        bot.centerXProperty().bind(centerXProperty);
        bot.centerYProperty().bind(centerYProperty.subtract(sizeProperty.get()));

        top.centerXProperty().bind(centerXProperty);
        top.centerYProperty().bind(centerYProperty.add(sizeProperty.get()));

        left.centerXProperty().bind(centerXProperty.subtract(sizeProperty.get()*3));
        left.centerYProperty().bind(centerYProperty);

        right.centerXProperty().bind(centerXProperty.add(sizeProperty.get()*3));
        right.centerYProperty().bind(centerYProperty);
    }




}
