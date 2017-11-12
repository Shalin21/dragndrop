package sample.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseButton;
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
    public int lineType;

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int lineType) {
        this.lineType = lineType;
    }

    public Polygon trinagle = new Polygon();
    public MyLine(Anchor start, Anchor temp, int rb) {
        this.setOnMouseClicked(event -> {
            if(event.getClickCount()==2 )
            {Pane pane = (Pane)this.getParent();
                pane.getChildren().remove(event.getSource());
                if(lineType==3){
                    pane.getChildren().remove(((MyLine)event.getSource()).trinagle);
                }
            }
        });
        startId = start.getPatentId();
        endId = temp.getPatentId();
        startType = start.type;
        endType = temp.type;
        endType = temp.type;
        lineType=rb;
  Pane anchorPaneVisual = (Pane)super.getParent();
        if (start.type == 2 && temp.type == 4) {
            System.out.println("asd");
            System.out.println(start.layoutXProperty().getValue() + " "+ start.layoutYProperty().getValue());

            CubicCurve super1 = new CubicCurve();

            super1.startXProperty().bind(start.centerXProperty());
            super1.startYProperty().bind(start.centerYProperty());

            //Polygon trinagle = new Polygon();
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
            super1.setStrokeWidth(1.5);
            super1.setFill(null);

        } else if (start.type == 2 && temp.type == 2) {
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
            super.setStrokeWidth(1.5);
            super.setFill(null);
        } else if (start.type == 2 && temp.type == 1) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);



        } else if (start.type == 3 && (temp.type == 4 || temp.type == 2)) {

            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();

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
            super.setStrokeWidth(1.5);
            super.setFill(null);



            super.toBack();

        } else if (start.type == 3 && temp.type == 1) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);



        } else if (start.type == 4 && temp.type == 4) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);


        } else if (start.type == 4 && temp.type == 2) {

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());


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
            super.setStrokeWidth(1.5);
            super.setFill(null);

        } else if (start.type == 4 && temp.type == 1) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);

        }
    }


    public void switchByType(Anchor start, Anchor temp, int rb){
        Pane anchorPaneVisual = (Pane)super.getParent();
        if (start.type == 2 && temp.type == 4) {
            System.out.println("asd");

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
            super.setStrokeWidth(1.5);
            super.setFill(null);

        } else if (start.type == 2 && temp.type == 2) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);

        } else if (start.type == 2 && temp.type == 1) {
            //Polygon trinagle = new Polygon();
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
            super.setStrokeWidth(1.5);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);



        } else if (start.type == 3 && (temp.type == 4 || temp.type == 2)) {

            trinagle.layoutYProperty().unbind();
            trinagle.layoutXProperty().unbind();

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
            super.setStrokeWidth(1.5);
            super.setFill(null);



            super.toBack();

        } else if (start.type == 3 && temp.type == 1) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);
            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);



        } else if (start.type == 4 && temp.type == 4) {

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
            super.setStrokeWidth(1.5);
            super.setFill(null);

        } else if (start.type == 4 && temp.type == 2) {

            super.startXProperty().bind(start.centerXProperty());
            super.startYProperty().bind(start.centerYProperty());


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
            super.setStrokeWidth(1.5);
            super.setFill(null);

        } else if (start.type == 4 && temp.type == 1) {

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
                super.setStyle("-fx-stroke-dash-array: 1;");
            }
            super.setStrokeWidth(1.5);
            super.setFill(null);

            DoubleProperty xp = new SimpleDoubleProperty();
            DoubleProperty yp = new SimpleDoubleProperty();
            xp.unbind();
            yp.unbind();
            xp.bind(super.endXProperty().subtract(10));
            yp.bind(super.endYProperty().subtract(20));

            trinagle.layoutXProperty().bind(xp);
            trinagle.layoutYProperty().bind(yp);


        }

    }
    public void drawTriangle(){
        if(lineType==3){
            Pane anchorPaneVisual = (Pane)super.getParent();
            anchorPaneVisual.getChildren().add(trinagle);
            trinagle.toBack();
        }
    }
    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }

    public String getEndId() {
        return endId;
    }

    public void setEndId(String endId) {
        this.endId = endId;
    }

    public int getStartType() {
        return startType;
    }

    public void setStartType(int startType) {
        this.startType = startType;
    }

    public int getEndType() {
        return endType;
    }

    public void setEndType(int endType) {
        this.endType = endType;
    }
}
