package sample.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;

/**
 * Created by admin on 11.10.17.
 */
public class MyLine extends CubicCurve {

    public String startId = new String();
    public String endId = new String();
    public int startType;
    public int endType;

    public MyLine(Anchor start, Anchor temp, int rb) {
        //startId = start.parent.getId();
        //endId = temp.parent.getId();
        startType = start.type;
        endType = temp.type;
  Pane anchorPaneVisual = (Pane)super.getParent();
        if (start.type == 2 && temp.type == 4) {
            System.out.println("asd");
            System.out.println(start.layoutXProperty().getValue() + " "+ start.layoutYProperty().getValue());

            CubicCurve super1 = new CubicCurve();

            super1.startXProperty().bind(start.centerXProperty());
            super1.startYProperty().bind(start.centerYProperty());

            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(20.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super1.endXProperty().subtract(20));
            yp.bind(super1.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super1.controlX1Property().bind(start.centerXProperty().add(value));
            super1.controlY1Property().bind(start.centerYProperty());
            super1.controlX2Property().bind(start.centerXProperty());
            super1.controlY2Property().bind(temp.centerYProperty());

            super1.endXProperty().bind(temp.centerXProperty());
            super1.endYProperty().bind(temp.centerYProperty());
            super1.setStroke(Color.BLACK);
            if (rb == 2) {
                super1.setStyle("-fx-stroke-dash-array: 2;");
            }
            super1.setStrokeWidth(1);
            super1.setFill(null);

            if (rb == 3) {

                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

            //anchorPaneVisual.getChildren().add(super1);
        } else if (start.type == 2 && temp.type == 2) {
            //super super = new super();
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();

            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(-15.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().add(10));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);


            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().add(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(temp.centerXProperty().add(value));
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {

                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
            //anchorPaneVisual.getChildren().add(super);
        } else if (start.type == 2 && temp.type == 1) {
            Polygon trinagle = new Polygon();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(10.0);
            trinagle.getPoints().add(20.0);
            trinagle.setLayoutX(500);
            trinagle.setLayoutY(500);
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            if (rb == 3) {
               // anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

            // super super = new super();

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());

            super.controlX1Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY1Property().bind(start.centerYProperty());

            super.controlX2Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY2Property().bind(start.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            // anchorPaneVisual.getChildren().add(super);

        } else if (start.type == 3 && (temp.type == 4 || temp.type == 2)) {
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            // super super = new super();
            if (temp.type == 4) {

                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(15.0);
                trinagle.getPoints().add(20.0);
                trinagle.getPoints().add(10.0);
                DoubleProperty xp = new SimpleDoubleProperty();
                DoubleProperty yp = new SimpleDoubleProperty();
                xp.bind(super.endXProperty().subtract(20));
                yp.bind(super.endYProperty().subtract(10));
                trinagle.layoutXProperty().bind(xp);
                trinagle.layoutYProperty().bind(yp);

            } else {
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(15.0);
                trinagle.getPoints().add(-10.0);
                trinagle.getPoints().add(10.0);
                DoubleProperty xp = new SimpleDoubleProperty();
                DoubleProperty yp = new SimpleDoubleProperty();
                xp.bind(super.endXProperty().add(10));
                yp.bind(super.endYProperty().subtract(10));
                trinagle.layoutXProperty().bind(xp);
                trinagle.layoutYProperty().bind(yp);
            }


            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty());
            super.controlY1Property().bind(temp.centerYProperty());

            super.controlX2Property().bind(start.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);


            // anchorPaneVisual.getChildren().add(super);
            super.toBack();
            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
        } else if (start.type == 3 && temp.type == 1) {
            Polygon trinagle = new Polygon();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(10.0);
            trinagle.getPoints().add(20.0);
            trinagle.setLayoutX(500);
            trinagle.setLayoutY(500);
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
            // super super = new super();
            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty());
            super.controlY1Property().bind(start.centerYProperty().add(value));

            super.controlX2Property().bind(temp.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty().subtract(value));

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            // anchorPaneVisual.getChildren().add(super);

        } else if (start.type == 4 && temp.type == 4) {
            //super super = new super();
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(20.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().subtract(20));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().subtract(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(temp.centerXProperty().subtract(value));
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
            // anchorPaneVisual.getChildren().add(super);
        } else if (start.type == 4 && temp.type == 2) {
            // super super = new super();
            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());

            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(-15.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().add(10));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().subtract(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(start.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 1;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

            //anchorPaneVisual.getChildren().add(super);
        } else if (start.type == 4 && temp.type == 1) {
            Polygon trinagle = new Polygon();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(10.0);
            trinagle.getPoints().add(20.0);
            trinagle.setLayoutX(500);
            trinagle.setLayoutY(500);
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();

            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

            // super super = new super();

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());

            super.controlX1Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY1Property().bind(start.centerYProperty());

            super.controlX2Property().bind(start.centerXProperty().subtract(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY2Property().bind(start.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            // anchorPaneVisual.getChildren().add(super);
        }
    }


    public void switchByType(Anchor start, Anchor temp, int rb){
        Pane anchorPaneVisual = (Pane)super.getParent();
        if (start.type == 2 && temp.type == 4) {
            System.out.println("asd");
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(20.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().subtract(20));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().add(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(start.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {

                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

            //anchorPaneVisual.getChildren().add(super1);
        } else if (start.type == 2 && temp.type == 2) {
            //super super = new super();
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();

            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(-15.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().add(10));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);


            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().add(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(temp.centerXProperty().add(value));
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {

                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
            //anchorPaneVisual.getChildren().add(super);
        } else if (start.type == 2 && temp.type == 1) {
            Polygon trinagle = new Polygon();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(10.0);
            trinagle.getPoints().add(20.0);
            trinagle.setLayoutX(500);
            trinagle.setLayoutY(500);
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

           // super super = new super();

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());

            super.controlX1Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY1Property().bind(start.centerYProperty());

            super.controlX2Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY2Property().bind(start.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

           // anchorPaneVisual.getChildren().add(super);

        } else if (start.type == 3 && (temp.type == 4 || temp.type == 2)) {
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
           // super super = new super();
            if (temp.type == 4) {

                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(15.0);
                trinagle.getPoints().add(20.0);
                trinagle.getPoints().add(10.0);
                DoubleProperty xp = new SimpleDoubleProperty();
                DoubleProperty yp = new SimpleDoubleProperty();
                xp.bind(super.endXProperty().subtract(20));
                yp.bind(super.endYProperty().subtract(10));
                trinagle.layoutXProperty().bind(xp);
                trinagle.layoutYProperty().bind(yp);

            } else {
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(5.0);
                trinagle.getPoints().add(15.0);
                trinagle.getPoints().add(-10.0);
                trinagle.getPoints().add(10.0);
                DoubleProperty xp = new SimpleDoubleProperty();
                DoubleProperty yp = new SimpleDoubleProperty();
                xp.bind(super.endXProperty().add(10));
                yp.bind(super.endYProperty().subtract(10));
                trinagle.layoutXProperty().bind(xp);
                trinagle.layoutYProperty().bind(yp);
            }


            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty());
            super.controlY1Property().bind(temp.centerYProperty());

            super.controlX2Property().bind(start.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);


           // anchorPaneVisual.getChildren().add(super);
            super.toBack();
            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
        } else if (start.type == 3 && temp.type == 1) {
            Polygon trinagle = new Polygon();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(10.0);
            trinagle.getPoints().add(20.0);
            trinagle.setLayoutX(500);
            trinagle.setLayoutY(500);
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
           // super super = new super();
            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty());
            super.controlY1Property().bind(start.centerYProperty().add(value));

            super.controlX2Property().bind(temp.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty().subtract(value));

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

           // anchorPaneVisual.getChildren().add(super);

        } else if (start.type == 4 && temp.type == 4) {
            //super super = new super();
            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(20.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().subtract(20));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().subtract(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(temp.centerXProperty().subtract(value));
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }
           // anchorPaneVisual.getChildren().add(super);
        } else if (start.type == 4 && temp.type == 2) {
           // super super = new super();
            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());

            Polygon trinagle = new Polygon();
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(-15.0);
            trinagle.getPoints().add(10.0);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.bind(super.endXProperty().add(10));
            yp.bind(super.endYProperty().subtract(10));
            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);
            double value = 0;
            if (temp.getCenterX() > start.getCenterX()) {
                value = temp.getCenterX() - start.getCenterX();
            } else {
                value = start.getCenterX() - temp.getCenterX();
            }
            super.controlX1Property().bind(start.centerXProperty().subtract(value));
            super.controlY1Property().bind(start.centerYProperty());
            super.controlX2Property().bind(start.centerXProperty());
            super.controlY2Property().bind(temp.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 1;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

            //anchorPaneVisual.getChildren().add(super);
        } else if (start.type == 4 && temp.type == 1) {
            Polygon trinagle = new Polygon();
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(15.0);
            trinagle.getPoints().add(5.0);
            trinagle.getPoints().add(10.0);
            trinagle.getPoints().add(20.0);
            trinagle.setLayoutX(500);
            trinagle.setLayoutY(500);
            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();

            if (rb == 3) {
                anchorPaneVisual.getChildren().add(trinagle);
                trinagle.toBack();
            }

           // super super = new super();

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());

            super.controlX1Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY1Property().bind(start.centerYProperty());

            super.controlX2Property().bind(start.centerXProperty().subtract(temp.centerXProperty().subtract(start.centerXProperty())));
            super.controlY2Property().bind(start.centerYProperty());

            super.endXProperty().bind(temp.centerXProperty());
            super.endYProperty().bind(temp.centerYProperty());
            super.setStroke(Color.BLACK);
            if (rb == 2) {
                super.setStyle("-fx-stroke-dash-array: 2;");
            }
            super.setStrokeWidth(1);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

           // anchorPaneVisual.getChildren().add(super);
        }
    }


}
