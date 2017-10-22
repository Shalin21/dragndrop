package sample;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Created by admin on 21.10.17.
 */
public class MyEvent extends Polygon {
    Rectangle process = new Rectangle();

    public MyEvent(double x, double y, double size, int type) {

        makeDrag();
    }

    public void switchByType(double x, double y, double size, int type) {
        switch (type){
            case 1:{
                typeOne(x, y, size);
            break;
        }
            case 2:{
                typeTwo(x, y, size);
            break;
        }
            case 3:
            typeThree(x, y, size);
        }
    }

    private void typeThree(double x, double y, double size) {
        super.getPoints().addAll(new Double[]{
                x, y,
                x + size, y,
                x + size * 1.5, y + size / 2,
                x + size, y + size,
                x, y + size,
                x - size / 2, y + size / 2});
        super.setFill(new Color(0.55, 0.98, 0.49, 1));
        super.setStroke(Color.BLACK);
        super.setStrokeWidth(1);
        process.setLayoutX(x-size*0.7);
        process.setLayoutY(y-size*0.2);
        process.setHeight(size);
        process.setWidth(size*1.7);
        process.setFill(new Color(0.55, 0.98, 0.49, 1));
        process.setStroke(Color.BLACK);
        process.setStrokeWidth(1);
        AnchorPane parent = (AnchorPane) super.getParent();
        parent.getChildren().addAll(process);
    }

    private void typeTwo(double x, double y, double size) {
        super.getPoints().addAll(new Double[]{
                x, y,
                x + size, y,
                x + size * 1.5, y + size / 2,
                x + size, y + size,
                x, y + size,
                x - size / 2, y + size / 2});
        super.setFill(new Color(0.6, 0.89, 0.98, 1.0));
        super.setStroke(Color.BLACK);
        super.setStrokeWidth(1);
    }

    private void typeOne(double x, double y, double size) {
        super.getPoints().addAll(new Double[]{
                x, y,
                x + size * 1.5, y,
                x + size * 2, y + size / 2,
                x + size * 1.5, y + size,
                x, y + size,
                x + size / 2, y + size / 2});
        super.setFill(new Color(0.52, 0.67, 0.98, 1.0));
        super.setStroke(Color.BLACK);
        super.setStrokeWidth(1);
    }


    private final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    private void makeDrag(){
        super.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        super.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            super.setLayoutX(super.getLayoutX() + deltaX);
            super.setLayoutY(super.getLayoutY() + deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

            process.setLayoutX(super.getLayoutX()+process.getHeight()*3.3);
            process.setLayoutY(super.getLayoutY()+process.getHeight()*3.8);
        });
        process.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        process.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            process.setLayoutX(process.getLayoutX() + deltaX);
            process.setLayoutY(process.getLayoutY() + deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

            super.setLayoutX(process.getLayoutX()-process.getHeight()*3.3);
            super.setLayoutY(process.getLayoutY()-process.getHeight()*3.8);
        });
    }


}
