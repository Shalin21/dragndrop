package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by admin on 11.10.17.
 */
public class MyLine extends Line {


    public MyLine( Circle c1, Circle c2) {

        super.startXProperty().bind(c1.centerXProperty());
        super.startYProperty().bind(c1.centerYProperty());
        super.endXProperty().bind(c2.centerXProperty());
        super.endYProperty().bind(c2.centerYProperty());
        super.setStrokeWidth(1.5);

        super.setOnMouseClicked(event -> {
            if(event.getClickCount() ==3){
                AnchorPane pane = (AnchorPane) super.getParent();
                pane.getChildren().remove(event.getSource());
            }
        });

    }

}
