package sample;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.*;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Circle;

import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;


public class Controller {




    @FXML
    private Circle circle;

    @FXML
    private Pane anchorPaneVisual;

    @FXML
    private RadioButton radioBtn;

    @FXML
    private AnchorPane elementPane;
    @FXML
    private Label label;
    @FXML
    private RadioButton rbLine;

    @FXML
    private RadioButton rbLine3;

    @FXML
    private RadioButton rbLine2;


    public boolean flag = false;

    ToggleGroup toggleGroup = new ToggleGroup();

    double x, y, x1, y1;
    Anchor start = null;
    int clickCount=0;
    public void initialize() {
        rbLine.setSelected(true);
        rbLine.setToggleGroup(toggleGroup);
        rbLine2.setToggleGroup(toggleGroup);
        rbLine3.setToggleGroup(toggleGroup);

   // anchorPaneVisual.setManaged(false);

        circle.setVisible(false);
        MyCircle xor = new MyCircle(circle.getLayoutX(), circle.getLayoutY(),20, 1);
        elementPane.getChildren().add(xor);
        elementPane.getChildren().add(xor.text);


        MyCircle and = new MyCircle(circle.getLayoutX(), circle.getLayoutY()+50,20, 2);
        elementPane.getChildren().add(and);
        elementPane.getChildren().add(and.text);


        MyCircle or = new MyCircle(circle.getLayoutX(), circle.getLayoutY()+100,20, 3);
        elementPane.getChildren().add(or);
        elementPane.getChildren().add(or.text);

        //or.text.layoutXProperty().bind(or.centerXProperty().subtract(14));
        //or.text.layoutYProperty().bind(or.centerYProperty().subtract(10));
        int pos = 90;
        for (int i=1; i<=4; i++) {

            pos = pos+50;
            MyGroup el1 = new MyGroup(circle.getLayoutX() - circle.getRadius(), circle.getLayoutY() + pos, 30, i);
            el1.text.setVisible(false);
            elementPane.getChildren().add(el1);
            el1.byType(i);
            setElementGDrag(el1);
        }


        setElementDrag(xor);
        setElementDrag(and);
        setElementDrag(or);

        anchorPaneVisual.setOnMouseClicked(event ->{
           // label.setText(Double.toString(event.getX()));
            if(event.getClickCount() == 2) {

                Random random = new Random();
                int answer = random.nextInt(4) + 1;
//                MyGroup group = new MyGroup( event.getX(), event.getY(), 100, answer);
//                anchorPaneVisual.getChildren().add(group);
//                group.makeDrag();
//                group.byType(group.type);
                MyEvent myEvent = new MyEvent(400, 400, 100, 1);

                anchorPaneVisual.getChildren().addAll(myEvent);
                myEvent.switchByType(400, 400, 100, 3);
            }
        });

        radioBtn.setOnAction(event -> {
            for (Node c:anchorPaneVisual.getChildren()) {

                if (c instanceof MyCircle) {
                    ((MyCircle) c).changeVisible();
                } else if (c instanceof MyGroup) {
                    ((MyGroup) c).changeVisible();
                }
                    for (Node temp1 : anchorPaneVisual.getChildren()) {
                        if (temp1 instanceof Anchor) {
                            Anchor temp = (Anchor) temp1;

                            temp.setOnMouseClicked(event1 -> {
                                clickCount++;
                                if (clickCount == 2) {
                                    clickCount = 0;
                                   // if (((MyCircle) c).containConnectPoint(start) != ((MyCircle) c).containConnectPoint(temp)) {
                                    if(start.parent != temp.parent){

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
                                                if(rbLine2.isSelected() == true) {
                                                cubicCurve.setStyle("-fx-stroke-dash-array: 2;");
                                                }
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
                                                if(rbLine2.isSelected() == true) {
                                                    cubicCurve.setStyle("-fx-stroke-dash-array: 2;");
                                                }
                                                cubicCurve.setStrokeWidth(1);
                                                cubicCurve.setFill(null);


                                                anchorPaneVisual.getChildren().add(cubicCurve);

                                            } else if (start.type == 3 && (temp.type == 4 || temp.type == 2)) {
                                                Polygon trinagle = new Polygon();
                                                trinagle.layoutYProperty().unbind();
                                                trinagle.layoutXProperty().unbind();
                                                CubicCurve cubicCurve = new CubicCurve();
                                                if(temp.type == 4) {

                                                    trinagle.getPoints().add(5.0);
                                                    trinagle.getPoints().add(5.0);
                                                    trinagle.getPoints().add(5.0);
                                                    trinagle.getPoints().add(15.0);
                                                    trinagle.getPoints().add(20.0);
                                                    trinagle.getPoints().add(10.0);
                                                    DoubleProperty xp = new SimpleDoubleProperty();
                                                    DoubleProperty yp = new SimpleDoubleProperty();
                                                    xp.bind(cubicCurve.endXProperty().subtract(20));
                                                    yp.bind(cubicCurve.endYProperty().subtract(10));
                                                    trinagle.layoutXProperty().bind(xp);
                                                    trinagle.layoutYProperty().bind(yp);

                                                }
                                                else
                                                {
                                                    trinagle.getPoints().add(5.0);
                                                    trinagle.getPoints().add(5.0);
                                                    trinagle.getPoints().add(5.0);
                                                    trinagle.getPoints().add(15.0);
                                                    trinagle.getPoints().add(-15.0);
                                                    trinagle.getPoints().add(10.0);
                                                    DoubleProperty xp = new SimpleDoubleProperty();
                                                    DoubleProperty yp = new SimpleDoubleProperty();
                                                    xp.bind(cubicCurve.endXProperty().add(10));
                                                    yp.bind(cubicCurve.endYProperty().subtract(10));
                                                    trinagle.layoutXProperty().bind(xp);
                                                    trinagle.layoutYProperty().bind(yp);

                                                }



                                                cubicCurve.startXProperty().bind(start.centerXProperty());
                                                cubicCurve.startYProperty().bind(start.centerYProperty());

                                                cubicCurve.controlX1Property().bind(start.centerXProperty());
                                                cubicCurve.controlY1Property().bind(start.centerYProperty().add(temp.centerYProperty().subtract(start.centerYProperty())));

                                                cubicCurve.controlX2Property().bind(start.centerXProperty());
                                                cubicCurve.controlY2Property().bind(start.centerYProperty().add(temp.centerYProperty().subtract(start.centerYProperty())));

                                                cubicCurve.endXProperty().bind(temp.centerXProperty());
                                                cubicCurve.endYProperty().bind(temp.centerYProperty());
                                                cubicCurve.setStroke(Color.BLACK);
                                                if(rbLine2.isSelected() == true) {
                                                    cubicCurve.setStyle("-fx-stroke-dash-array: 2;");
                                                }
                                                cubicCurve.setStrokeWidth(1);
                                                cubicCurve.setFill(null);


                                                anchorPaneVisual.getChildren().add(cubicCurve);
                                                anchorPaneVisual.getChildren().add(trinagle);
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
                                                if(rbLine3.isSelected() == false) {
                                                    anchorPaneVisual.getChildren().add(trinagle);
                                                }
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
                                                if(rbLine2.isSelected() == true) {
                                                    cubicCurve.setStyle("-fx-stroke-dash-array: 2;");
                                                }
                                                cubicCurve.setStrokeWidth(1);
                                                cubicCurve.setFill(null);
                                                DoubleProperty xp = new SimpleDoubleProperty();
                                                DoubleProperty yp = new SimpleDoubleProperty();
                                                xp.unbind();
                                                yp.unbind();
                                                xp.bind(cubicCurve.endXProperty().subtract(10));
                                                yp.bind(cubicCurve.endYProperty().subtract(20));

                                                trinagle.layoutXProperty().bind(xp);
                                                trinagle.layoutYProperty().bind(yp);

                                                anchorPaneVisual.getChildren().add(cubicCurve);

                                            }

                                        }
                                    }
                                } else if (clickCount == 1) {
                                    start = (Anchor) event1.getSource();
                                }

                            });
                        }
                    }
                }


        });

    }


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
            if((event1.getX() - circle1.getRadius()>0 && event1.getX()+circle1.getRadius()<anchorPaneVisual.getWidth()) &&
                    (event1.getY() - circle1.getRadius()>0 && event1.getY()+circle1.getRadius()<anchorPaneVisual.getHeight())) {
                circle1.setCenterY(event1.getY());
                circle1.setCenterX(event1.getX());
                circle1.text.setLayoutX(circle1.getCenterX() - 14);
                circle1.text.setLayoutY(circle1.getCenterY() - 10);
            }

        });

    }
    private final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    public void makeDrag(MyGroup super1) {

        super1.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        super1.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            if((super1.getLayoutX()+deltaX>0 && super1.getLayoutX()+deltaX+super1.getWidth()<anchorPaneVisual.getWidth()) &&
                    (super1.getLayoutY()+deltaY>0 && super1.getLayoutY()+deltaY+super1.getHeight()<anchorPaneVisual.getHeight())) {

                super1.setLayoutX(super1.getLayoutX() + deltaX);
                super1.setLayoutY(super1.getLayoutY() + deltaY);
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
                super1.text.setLayoutX(super1.getLayoutX() + ((super1.size * 0.9) + (super1.text.getText().length() * 3)));
                super1.text.setLayoutY(super1.getLayoutY() + super1.size / 2.9);
            }
        });
        super1.text.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Split");
            dialog.setHeaderText("After how many characters should be splitted?");
            Optional<String> result = dialog.showAndWait();
            if (result.get().length() < 45) {
                if (result.get().length() > 15) {
                    if (result.get().length() >= 30) {
                        super1.text.setText(result.get().substring(0, 15) + "\n" + result.get().substring(15, 30) + "\n" + result.get().substring(30));
                    } else {
                        super1.text.setText(result.get().substring(0, 15) + "\n" + result.get().substring(15));
                    }
                } else if (result.get().length() < 15) {
                    super1.text.setText(result.get());
                }
            }

        });



    }

    public void setElementDrag(MyCircle element){

        element.setOnMousePressed(event -> {
            x = element.getCenterX();
            y = element.getCenterY();
        });
        element.setOnMouseDragged(event -> {
            element.setCenterX(event.getX());
            element.setCenterY(event.getY());
           // element.text.setLayoutX(element.getCenterX()-14);
           // element.text.setLayoutY(element.getCenterY()-10);
        });
        element.setOnMouseReleased(event -> {
            if (element.getCenterX() > 100) {
                Random a = new Random();
                MyCircle circle1 = new MyCircle(event.getX() - circle.getRadius() * 2.7, event.getY() + circle.getRadius() * 2.3, 20, element.type);

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

            }

            element.setCenterX(x);
            element.setCenterY(y);


        });

    }
    public void setElementGDrag(MyGroup element) {
        ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();

        element.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            x = element.getLayoutX();
            y = element.getLayoutY();
        });
        element.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();

                element.setLayoutX(element.getLayoutX() + deltaX);
                element.setLayoutY(element.getLayoutY() + deltaY);
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));



        });
        element.setOnMouseReleased(event -> {

            if(element.getLayoutX()>100){
                MyGroup newElement = new MyGroup(event.getX(), event.getY(), 80, element.type);

                anchorPaneVisual.getChildren().add(newElement);
                anchorPaneVisual.getChildren().addAll(newElement.top, newElement.bot, newElement.left, newElement.right);
             makeDrag(newElement);
                newElement.byType(newElement.type);
            }
            element.setLayoutX(x);
            element.setLayoutY(y);
        });

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



