package sample;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private Polygon triangle;

    @FXML
    private Rectangle square;

    @FXML
    private Circle circle;

    @FXML
    private AnchorPane anchorPaneVisual;
    @FXML
    private RadioButton radioBtn;
    @FXML
    private Label label;

    public boolean flag = false;



    double x, y, x1, y1;
    Anchor start = null;
    int clickCount=0;
    public void initialize() {
        anchorPaneVisual.setOnMouseClicked(event ->{
            if(event.getClickCount() == 2) {
//                Point2D point = new Point2D(event.getX(), event.getY());
//                Hexagon xa = new Hexagon(point,30);
//                anchorPaneVisual.getChildren().add(xa);
//                anchorPaneVisual.getChildren().add(xa.top);
//                anchorPaneVisual.getChildren().add(xa.bot);
//                anchorPaneVisual.getChildren().add(xa.left);
//                anchorPaneVisual.getChildren().add(xa.right);
//                anchorPaneVisual.getChildren().add(xa.position);
//                Rectangle rectangle = new Rectangle();
//                rectangle.setX(event.getX());
//                rectangle.setY(event.getY());
//                rectangle.setWidth(40);
//                rectangle.setHeight(40);
//                DragResizeMod.makeResizable(rectangle);
//                anchorPaneVisual.getChildren().add(rectangle);
                Random random = new Random();
                int answer = random.nextInt(3) + 1;
                MyGroup group = new MyGroup("asd", event.getX(), event.getY(), 100, answer);
                anchorPaneVisual.getChildren().add(group);


            }
        });

        radioBtn.setOnAction(event -> {
            for (Node c:anchorPaneVisual.getChildren()) {
                if(c instanceof MyCircle){
                    ((MyCircle) c).changeVisible();
//                    ((MyCircle)c) .top.toFront();
//                    ((MyCircle)c).bot.toFront();
//                    ((MyCircle)c).left.toFront();
//                    ((MyCircle)c).right.toFront();
                    for (Anchor temp:((MyCircle)c).getConnectionPoints()) {
                       temp.setOnMouseClicked(event1 -> {
                           clickCount++;
                           if(clickCount==2) {
                               clickCount = 0;
                               if (((MyCircle) c).containConnectPoint(start) != ((MyCircle) c).containConnectPoint(temp)) {


                                   if (start instanceof Anchor && temp instanceof Anchor) {
                                       if (start.type == 2 && temp.type == 4) {
                                           CubicCurve cubicCurve = new CubicCurve();
                                           cubicCurve.startXProperty().bind(start.centerXProperty());
                                           cubicCurve.startYProperty().bind(start.centerYProperty());

                                           cubicCurve.controlX1Property().bind(start.centerXProperty().add(temp.getCenterX() - start.getCenterX()));
                                           cubicCurve.controlY1Property().bind(start.centerYProperty());
                                           cubicCurve.controlX2Property().bind(start.centerXProperty());
                                           cubicCurve.controlY2Property().bind(temp.centerYProperty());

                                           cubicCurve.endXProperty().bind(temp.centerXProperty());
                                           cubicCurve.endYProperty().bind(temp.centerYProperty());
                                           cubicCurve.setStroke(Color.BLACK);
//                                   cubicCurve.setStyle("-fx-stroke-dash-array: 2;");
                                           cubicCurve.setStrokeWidth(1);
                                           cubicCurve.setFill(null);


                                           anchorPaneVisual.getChildren().add(cubicCurve);
                                       } else if (start.type == 2 && temp.type == 1) {
                                           CubicCurve cubicCurve = new CubicCurve();
                                           cubicCurve.startXProperty().bind(start.centerXProperty());
                                           cubicCurve.startYProperty().bind(start.centerYProperty());

                                           cubicCurve.controlX1Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
                                           cubicCurve.controlY1Property().bind(start.centerYProperty());

                                           cubicCurve.controlX2Property().bind(start.centerXProperty().add(temp.centerXProperty().subtract(start.centerXProperty())));
                                           cubicCurve.controlY2Property().bind(start.centerYProperty());

                                           cubicCurve.endXProperty().bind(temp.centerXProperty());
                                           cubicCurve.endYProperty().bind(temp.centerYProperty());
                                           cubicCurve.setStroke(Color.BLACK);
//                                   cubicCurve.setStyle("-fx-stroke-dash-array: 2;");=
                                           cubicCurve.setStrokeWidth(1);
                                           cubicCurve.setFill(null);


                                           anchorPaneVisual.getChildren().add(cubicCurve);

                                       } else if (start.type == 3 && (temp.type == 4 || temp.type == 2)) {
                                           CubicCurve cubicCurve = new CubicCurve();
                                           cubicCurve.startXProperty().bind(start.centerXProperty());
                                           cubicCurve.startYProperty().bind(start.centerYProperty());

                                           cubicCurve.controlX1Property().bind(start.centerXProperty());
                                           cubicCurve.controlY1Property().bind(start.centerYProperty().add(temp.centerYProperty().subtract(start.centerYProperty())));

                                           cubicCurve.controlX2Property().bind(start.centerXProperty());
                                           cubicCurve.controlY2Property().bind(start.centerYProperty().add(temp.centerYProperty().subtract(start.centerYProperty())));

                                           cubicCurve.endXProperty().bind(temp.centerXProperty());
                                           cubicCurve.endYProperty().bind(temp.centerYProperty());
                                           cubicCurve.setStroke(Color.BLACK);
//                                   cubicCurve.setStyle("-fx-stroke-dash-array: 2;");=
                                           cubicCurve.setStrokeWidth(1);
                                           cubicCurve.setFill(null);


                                           anchorPaneVisual.getChildren().add(cubicCurve);
                                       } else if (start.type == 3 && temp.type == 1) {
                                           CubicCurve cubicCurve = new CubicCurve();
                                           cubicCurve.startXProperty().bind(start.centerXProperty());
                                           cubicCurve.startYProperty().bind(start.centerYProperty());

                                           cubicCurve.controlX1Property().bind(start.centerXProperty());
                                           cubicCurve.controlY1Property().bind(start.centerYProperty().add(temp.centerYProperty().subtract(start.centerYProperty())));

                                           cubicCurve.controlX2Property().bind(temp.centerXProperty());
                                           cubicCurve.controlY2Property().bind(temp.centerYProperty().subtract(temp.centerYProperty().subtract(start.centerYProperty())));

                                           cubicCurve.endXProperty().bind(temp.centerXProperty());
                                           cubicCurve.endYProperty().bind(temp.centerYProperty());
                                           cubicCurve.setStroke(Color.BLACK);
//                                   cubicCurve.setStyle("-fx-stroke-dash-array: 2;");=
                                           cubicCurve.setStrokeWidth(1);
                                           cubicCurve.setFill(null);


                                           anchorPaneVisual.getChildren().add(cubicCurve);

                                       }
//
                                   }
                               }
                           }
                           else if(clickCount == 1){
                               start=(Anchor) event1.getSource();
                           }

                       });
                    }
                }
            }
        });
        //anchorPaneVisual.getChildren().add(drawArrow(50,50,200,200));
        //anchorPaneVisual.setOnMouseClicked(new MouseHandle(anchorPaneVisual));
//        MouseHandle mh = new MouseHandle(anchorPaneVisual);
//        anchorPaneVisual.setOnMouseClicked(mh);
//        anchorPaneVisual.setOnMouseMoved(mh);
       // System.out.println(anchorPaneVisual.getId());

//        Hexagon hex = new Hexagon();
//        anchorPaneVisual.getChildren().add(hex.getPolygon());

        anchorPaneVisual.setOnMouseMoved(event -> {
            label.setText(event.getX()+":"+event.getY());
        });

        x = circle.getLayoutX();
        y = circle.getLayoutY();


        circle.setOnMousePressed(event1 -> {
            flag = true;
        });
        circle.setOnMouseDragged(event1 -> {

                circle.setCenterY(event1.getY());
                circle.setCenterX(event1.getX());
                label.setText("x:" + circle.getCenterX() + ";y:" + circle.getCenterY() + "\n" +
                        "xL:" + circle.getLayoutX() + ";yL:" + circle.getLayoutY());

        });
        circle.setOnMouseReleased(event1 -> {
            flag = false;
            if (circle.getCenterX() > 100) {
                MyCircle circle1 = new MyCircle(event1.getX() - circle.getRadius() * 2.7, event1.getY() + circle.getRadius() * 2.3, circle.getRadius(), 1);
//                Random rand = new Random();
//                float r = rand.nextFloat();
//                float g = rand.nextFloat();
//                float b = rand.nextFloat();
//                Color randomColor = new Color(r, g, b, 1.0);
                circle1.setFill(Color.GREY);
                circle1.setStroke(Color.BLACK);
                circle1.setStrokeWidth(2);
                circle1.setId("asd" + (getNodeCount() + 1));

                setDraggable1(circle1);
                anchorPaneVisual.getChildren().add(circle1);
                anchorPaneVisual.getChildren().add(circle1.text);

                anchorPaneVisual.getChildren().add(circle1.bot);
                anchorPaneVisual.getChildren().add(circle1.top);
                anchorPaneVisual.getChildren().add(circle1.left);
                anchorPaneVisual.getChildren().add(circle1.right);
                circle1.toFront();
                circle1.text.toFront();
                circle1.top.toFront();
                circle1.left.toFront();
                circle1.right.toFront();
                circle1.bot.toFront();
                circle.setLayoutX(x);
                circle.setLayoutY(y);
                circle.setCenterX(0);
                circle.setCenterY(0);
            } else {
                circle.setLayoutX(x);
                circle.setLayoutY(y);
                circle.setCenterX(0);
                circle.setCenterY(0);
            }
            label.setText("x:" + circle.getCenterX() + ";y:" + circle.getCenterY() + "\n" +
                    "xL:" + circle.getLayoutX() + ";yL:" + circle.getLayoutY());
        });


        triangle.setOnMousePressed(event1 -> {
            if (!radioBtn.isPressed()) {
                flag = true;
            }
        });
        triangle.setOnMouseDragged(event1 -> {
            if (flag == true) {
                triangle.setLayoutX(event1.getY());
                triangle.setLayoutY(event1.getX());
            }
        });

        triangle.setOnMouseReleased(event1 -> {
            flag = false;

        });


        square.setOnMousePressed(event1 -> {
            x1 = square.getX();
            y1 = square.getY();
            flag = true;
        });
        square.setOnMouseDragged(event1 -> {
            if (flag == true) {
                square.setY(event1.getY() - square.getHeight() / 2);
                square.setX(event1.getX() - square.getHeight() / 2);
            }
        });
        square.setOnMouseReleased(event1 -> {
            flag = false;

            if (square.getX() > 100) {
                Rectangle rectangle = new Rectangle(square.getWidth(), square.getHeight());
                rectangle.setY(square.getY() + square.getHeight() * 2.6);
                rectangle.setX(square.getX() - square.getHeight() * 1.8);
                anchorPaneVisual.getChildren().add(rectangle);
                square.setX(x1);
                square.setY(y1);

            }
        });

        triangle.setOnMouseClicked(event -> {
            for (Node n : anchorPaneVisual.getChildren()
                    ) {
                Circle a = (Circle) n;
                System.out.println(a.getId());

            }
        });


    }

    double x11, y11;

    public void setDraggable1(MyCircle circle1) {

        circle1.setOnMousePressed(event1 -> {
            circle1.setCursor(Cursor.CLOSED_HAND);
            circle1.toFront();
            circle1.text.toFront();
            circle1.top.toFront();
            circle1.bot.toFront();
            circle1.left.toFront();
            circle1.right.toFront();


        });
        circle1.setOnMouseDragged(event1 -> {
//            if (flag == true) {
            if(!radioBtn.isSelected()) {
                circle1.setCenterY(event1.getY());
                circle1.setCenterX(event1.getX());
            }
            //}
        });

//        circle1.setOnMouseReleased(event1 -> {
//        circle1.setCursor(Cursor.HAND);
////            flag = false;
//            if(radioBtn.isSelected()) {
//                for (Node c:anchorPaneVisual.getChildren()) {
//
//                        Circle asd = (Circle) c;
//                        if(event1.getX()>asd.getCenterX()-asd.getRadius() && event1.getX()<asd.getCenterX()+asd.getRadius() && event1.getY()>asd.getCenterY()-asd.getRadius() && event1.getY()<asd.getCenterX()+asd.getRadius()){
//                            //MyLine line = new MyLine(circle1, asd);
//                            CubicCurve cubicCurve = new CubicCurve();
//                            cubicCurve.setStartX(circle1.getCenterX());
//                            cubicCurve.setStartY(circle1.getCenterY());
//                            cubicCurve.setEndX(asd.getCenterX());
//                            cubicCurve.setEndY(asd.getCenterY());
//                            cubicCurve.setControlX1(circle1.getCenterX());
//                            cubicCurve.setControlY1(circle1.getCenterY()+150);
//                            anchorPaneVisual.getChildren().add(cubicCurve);
//                            //line.toBack();
//
//                    }
//
//                }
////
//
//            }
//
//        });




    }



    public int getNodeCount() {
        int i = 0;
        for (Node n : anchorPaneVisual.getChildren()
                ) {
            i++;
        }
        return i;
    }
}



