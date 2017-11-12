package sample;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.*;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.shape.CubicCurve;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Models.*;
import sample.SerializeModels.MyCircleCollection;
import sample.SerializeModels.MyCircleModel;
import sample.SerializeModels.MyLineCollection;
import sample.SerializeModels.MyLineModel;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;


public class Controller {

    @FXML
    private MenuItem menuSave;

    @FXML
    private MenuItem menuLoad;
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

    int clickCount = 0;
    MyCircle ts = new MyCircle(500, 500, 20, 1);
    MyLine line = null;
    public void initialize() {
    label.setVisible(false);
    radioBtn.setText("Enable draw");

        /**
         * Собитие сериализации обьектов
         */
        menuSave.setOnAction(event -> {
            MyCircleCollection collection = new MyCircleCollection();
            MyLineCollection collection1 = new MyLineCollection();
            for (Node c: anchorPaneVisual.getChildren()) {
                if(c instanceof MyCircle){
                    MyCircle ts = (MyCircle)c;
                    MyCircleModel l = new MyCircleModel(ts.centerXProperty().get(), ts.centerYProperty().get(), ts.size, ts.type, "","Circle", ts.getId());
                    collection.add(l);
                }
                else if(c instanceof MyGroup){
                    MyGroup ts = (MyGroup) c;
                    MyCircleModel l = new MyCircleModel(ts.layoutXProperty().get(), ts.layoutYProperty().get(), ts.size, ts.type, ts.text.getText(), "Group", ts.getId());
                    collection.add(l);
                }
                else if (c instanceof MyEvent){
                    MyEvent ts = (MyEvent) c;
                    MyCircleModel l = new MyCircleModel(ts.layoutXProperty().get(), ts.layoutYProperty().get(), ts.size, ts.type, ts.text, "Event", ts.getId());
                    collection.add(l);
                }
                else if(c instanceof MyLine){
                    MyLine ts = (MyLine)c;
                    MyLineModel l = new MyLineModel(ts.getStartId(), ts.getEndId(), ts.getStartType(), ts.getEndType(), ts.getLineType());
                    collection1.add(l);
                }
            }
            try {
                Stage stage = (Stage) anchorPaneVisual.getScene().getWindow();
                DirectoryChooser fileChooser = new DirectoryChooser();
                fileChooser.setTitle("Open Resource File");

                JAXBContext jc = JAXBContext.newInstance(MyCircleCollection.class);
                String path = fileChooser.showDialog(stage).getPath();


                File serializedFile = new File(path+File.separator+"object.xml");



                File serializedFile1 = new File(path+File.separator+"line.xml");

                if (serializedFile.exists() == false ) {
                    serializedFile.createNewFile();

                }

                Marshaller m = jc.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);




                JAXBElement<MyCircleCollection> jaxbElement = new JAXBElement<>(
                        new QName("List"), MyCircleCollection.class, collection);


                m.marshal(jaxbElement, serializedFile);



                JAXBContext jc1 = JAXBContext.newInstance(MyLineCollection.class);

                if (serializedFile1.exists() == false)
                    serializedFile1.createNewFile();

                Marshaller m1 = jc1.createMarshaller();
                m1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


                JAXBElement<MyLineCollection> jaxbElement1 = new JAXBElement<>(
                        new QName("List"), MyLineCollection.class, collection1);


                m1.marshal(jaxbElement1, serializedFile1);



            }
            catch (JAXBException e){
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
                System.out.println(e.toString());
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
//
        });

        /**
         * Собитие десериализации обьектов
         */
        menuLoad.setOnAction(event -> {
            MyCircleCollection collection = new MyCircleCollection();
            MyLineCollection collection1 = new MyLineCollection();
            Anchor end =new Anchor();
            Anchor start1 = new Anchor();
            try{

                Stage stage = (Stage) anchorPaneVisual.getScene().getWindow();
                DirectoryChooser fileChooser = new DirectoryChooser();
                fileChooser.setTitle("Open Resource File");
                JAXBContext jc = JAXBContext.newInstance(MyCircleCollection.class);
                String path = fileChooser.showDialog(stage).getPath();


                File serializedFile = new File(path+File.separator+"object.xml");



                File serializedFile1 = new File(path+File.separator+"line.xml");

                //UNMARSHALLING
                Unmarshaller unmarshaller = jc.createUnmarshaller();

                StreamSource xml = new StreamSource(serializedFile);
                 collection= (MyCircleCollection) unmarshaller.unmarshal(xml, MyCircleCollection.class).getValue();

                FileChooser fileChooser1 = new FileChooser();
                fileChooser1.setTitle("Open Resource File");
                JAXBContext jc1 = JAXBContext.newInstance(MyLineCollection.class);


                Unmarshaller unmarshaller1 = jc1.createUnmarshaller();

                StreamSource xml1 = new StreamSource(serializedFile1);
                collection1= (MyLineCollection) unmarshaller1.unmarshal(xml1, MyLineCollection.class).getValue();

                MyCircle c = null;
                for (MyCircleModel model:collection.getCollection()
                     ) {
                    if(model.getFigure().contains("Circle")) {
                        c = new MyCircle(model.getX(), model.getY(), model.getSize(), model.getType());

                        anchorPaneVisual.getChildren().addAll(c, c.text, c.right, c.left, c.top, c.bot);
                        c.setId(model.getId());
                        setDraggable1(c);
                    }
                    else if(model.getFigure().contains("Group")){
                        MyGroup group = new MyGroup(model.getX(), model.getY(), model.getSize(), model.getType(), model.getText());
                        group.setId(model.getId());
                        group.text.setText(group.getId());
                        anchorPaneVisual.getChildren().addAll(group, group.right, group.left, group.top, group.bot);
                        group.byType(model.getType());
                        makeDrag2(group);
                    }
                    else if(model.getFigure().contains("Event")){
                        MyEvent group1 = new MyEvent(model.getX(), model.getY(), model.getSize(), model.getType(), model.getText());
                        group1.setId(model.getId());
                        group1.text1.setText(group1.getId());
                        anchorPaneVisual.getChildren().add(group1);
                        group1.switchByType(group1.type);
                        group1.makeDrag();
                    }

                }

                for ( MyLineModel model:collection1.getCollection()) {

                    for (Node n:anchorPaneVisual.getChildren()) {
                        if(n instanceof Anchor){


                            if(((Anchor)n).parent.getId().equalsIgnoreCase(model.getStartId()) && ((Anchor)n).type==model.getStartType() ){
                               start1 = (Anchor)n;
//                                //System.out.println("start");
//                                System.out.println("Start type:"+start1.type);
//                                System.out.println("Start parent:"+start1.parent.getId());

                            }
                            if(((Anchor)n).parent.getId().equalsIgnoreCase(model.getEndId()) && ((Anchor)n).type==model.getEndType()){
                                end = (Anchor)n;
//                               // System.out.println("end");
//                                System.out.println("End type:"+end.type);
//                                System.out.println("End parent:"+end.parent.getId());
//                                System.out.println();
                            }


                        }

                    }
                    MyLine line = new MyLine(start1, end, model.getLineType());
                    anchorPaneVisual.getChildren().add(line);
                    if(line.getLineType()==3){
                        anchorPaneVisual.getChildren().add(line.trinagle);
                        line.trinagle.toBack();
                    }
                    line.toBack();

                }


            }
            catch (JAXBException e){
                System.out.println(e.toString());
            }
        });

        rbLine.setSelected(true);
        rbLine.setToggleGroup(toggleGroup);
        rbLine2.setToggleGroup(toggleGroup);
        rbLine3.setToggleGroup(toggleGroup);


        circle.setVisible(false);
        MyCircle xor = new MyCircle(circle.getLayoutX(), circle.getLayoutY(), 20, 1);
        elementPane.getChildren().add(xor);
        elementPane.getChildren().add(xor.text);


        MyCircle and = new MyCircle(circle.getLayoutX(), circle.getLayoutY() + 50, 20, 2);
        elementPane.getChildren().add(and);
        elementPane.getChildren().add(and.text);


        MyCircle or = new MyCircle(circle.getLayoutX(), circle.getLayoutY() + 100, 20, 3);
        elementPane.getChildren().add(or);
        elementPane.getChildren().add(or.text);


        int pos = 90;
        for (int i = 1; i <= 4; i++) {
            pos = pos + 50;
            MyGroup el1 = new MyGroup(circle.getLayoutX() - circle.getRadius(), circle.getLayoutY() + pos, 30, i, "text");
            el1.text.setVisible(false);
            elementPane.getChildren().add(el1);
            el1.byType(i);
            setElementGDrag(el1);
        }
        pos = 280;
        for (int i = 1; i <= 3; i++) {
            pos = pos + 50;
            MyEvent el1 = new MyEvent(circle.getLayoutX() - circle.getRadius()+10, circle.getLayoutY() + pos, 30, i,"");

            elementPane.getChildren().add(el1);
            el1.switchByType( i);
            makeDrag(el1);

        }

        setElementDrag(xor);
        setElementDrag(and);
        setElementDrag(or);


        radioBtn.setOnAction(event -> {
            for (Node c : anchorPaneVisual.getChildren()) {

                if (c instanceof MyCircle) {
                    ((MyCircle) c).changeVisible();
                } else if (c instanceof MyGroup) {
                    ((MyGroup) c).changeVisible();

                }
                else if(c instanceof MyEvent){
                    ((MyEvent) c).changeVisible();

                }
                for (Node temp1 : anchorPaneVisual.getChildren()) {
                    if (temp1 instanceof Anchor) {
                        Anchor temp = (Anchor) temp1;
                        CubicCurve cubicCurve = new CubicCurve();

                        temp.setOnMousePressed(event1 -> {
                            start=temp;
                            cubicCurve.startXProperty().bind(temp.centerXProperty());
                            cubicCurve.startYProperty().bind(temp.centerYProperty());
                            cubicCurve.controlX1Property().bind(temp.centerXProperty());
                            cubicCurve.controlY1Property().bind(temp.centerYProperty());
                            cubicCurve.controlX2Property().bind(temp.centerXProperty());
                            cubicCurve.controlY2Property().bind(temp.centerYProperty());
                            cubicCurve.setEndX(event1.getX());
                            cubicCurve.setEndY(event1.getY());
                            cubicCurve.setStroke(Color.BLACK);
                            cubicCurve.setStrokeWidth(1);
                            cubicCurve.setFill(null);
                            anchorPaneVisual.getChildren().add(cubicCurve);

                        });

                        temp.setOnMouseDragged(event1 -> {


                            cubicCurve.setEndX(event1.getX());
                            cubicCurve.setEndY(event1.getY());

                        });

                        temp.setOnMouseReleased(event1 -> {
                            for (Node anchor:anchorPaneVisual.getChildren()) {
                                if(anchor instanceof Anchor){
                                    Anchor p = (Anchor) anchor;
                                    if (p.contains(event1.getX(), event1.getY())) {
                                        int lineType =1;
                                    if(rbLine.isSelected()){lineType=1;}
                                    else if(rbLine2.isSelected()){lineType=2;}
                                    else if(rbLine3.isSelected()){lineType=3;}
                                         line = new MyLine(start, p, lineType);

                                    }
                                }

                            }
                            if(line!=null) {
                                anchorPaneVisual.getChildren().add(line);
                                if(line.lineType==3){
                                    anchorPaneVisual.getChildren().add(line.trinagle);
                                }
                                line.toBack();
                                line.trinagle.toBack();
                                anchorPaneVisual.getChildren().remove(cubicCurve);
                            }
                            else{
                                anchorPaneVisual.getChildren().remove(cubicCurve);
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
            if ((event1.getX() - circle1.getRadius() > 0 && event1.getX() + circle1.getRadius() < anchorPaneVisual.getWidth()) &&
                    (event1.getY() - circle1.getRadius() > 0 && event1.getY() + circle1.getRadius() < anchorPaneVisual.getHeight())) {
                circle1.setCenterY(event1.getY());
                circle1.setCenterX(event1.getX());
                circle1.text.setLayoutX(circle1.getCenterX() - 14);
                circle1.text.setLayoutY(circle1.getCenterY() - 10);
            }

        });

    }

    private final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    /**
     * Собития для возможности добавления элементов перетаскивая их из левой панели (события для каждого типа элементов)
     */
        /**
         * Собитие для обьектов MyGroup
         */
    public void makeDrag2(MyGroup super1) {

        super1.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        super1.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            if ((super1.getLayoutX() + deltaX > 0 && super1.getLayoutX() + deltaX + super1.getWidth() < anchorPaneVisual.getWidth()) &&
                    (super1.getLayoutY() + deltaY > 0 && super1.getLayoutY() + deltaY + super1.getHeight() < anchorPaneVisual.getHeight())) {

                super1.setLayoutX(super1.getLayoutX() + deltaX);
                super1.setLayoutY(super1.getLayoutY() + deltaY);
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
                super1.text.setLayoutX(super1.getLayoutX() + ((super1.size * 0.4) - (super1.text.getText().length() * 3)));
                super1.text.setLayoutY(super1.getLayoutY() + super1.size / 4);
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
    /**
     * Собитие для обьектов MyCircle
     */
    public void setElementDrag(MyCircle element) {

        element.setOnMousePressed(event -> {
            x = element.getCenterX();
            y = element.getCenterY();
        });
        element.setOnMouseDragged(event -> {
            element.setCenterX(event.getX());
            element.setCenterY(event.getY());

        });
        element.setOnMouseReleased(event -> {
            if (element.getCenterX() > 100) {
                Random a = new Random();
                MyCircle circle1 = new MyCircle(event.getX() - circle.getRadius() * 2.7, event.getY() + circle.getRadius() * 2.3, 20, element.type);
                circle1.setId("MyCircle"+(getNodeCount()+1));


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
                if(radioBtn.isSelected()){
                    circle1.changeVisible();
                }

            }

            element.setCenterX(x);
            element.setCenterY(y);


        });

    }
    /**
     * Собитие для обьектов MyGroup
     */
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

            if (element.getLayoutX() > 100) {
                MyGroup newElement = new MyGroup(event.getX(), event.getY(), 80, element.type, "text");
                newElement.setId("MyGroup"+(getNodeCount()+1));
                anchorPaneVisual.getChildren().add(newElement);
                anchorPaneVisual.getChildren().addAll(newElement.top, newElement.bot, newElement.left, newElement.right);
                makeDrag2(newElement);
                newElement.byType(newElement.type);
                if(radioBtn.isSelected()){
                    newElement.changeVisible();
                }
            }
            element.setLayoutX(x);
            element.setLayoutY(y);
        });

    }
    /**
     * Собитие для обьектов MyEvent
     */

    double x2,y2;
    private void makeDrag(MyEvent super1) {
        if (super1.type != 3) {
            super1.setOnMousePressed(event -> {
                x2 = super1.getLayoutX();
                y2 = super1.getLayoutY();
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            });

            super1.setOnMouseDragged(event -> {
                double deltaX = event.getSceneX() - mousePosition.get().getX();
                double deltaY = event.getSceneY() - mousePosition.get().getY();
                super1.setLayoutX(super1.getLayoutX() + deltaX);
                super1.setLayoutY(super1.getLayoutY() + deltaY);
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

                super1.process.setLayoutX(super1.getLayoutX() + super1.process.getWidth() * 0.15);
                super1.process.setLayoutY(super1.getLayoutY() + super1.process.getHeight() * 16.4);
            });
            super1.setOnMouseReleased(event -> {
                if (super1.getLayoutX() > 100) {
                    MyEvent el = new MyEvent(100, 100, 80, super1.type, "text");
                    anchorPaneVisual.getChildren().add(el);
                    el.setId("MyEvent"+(getNodeCount()+1));
                    el.switchByType(super1.type);
                    if (radioBtn.isSelected()) {
                        el.changeVisible();
                    }
                    if(super1.type==1) {
                        anchorPaneVisual.getChildren().addAll(el.right);
                    }
                    el.makeDrag();
                }
                super1.setLayoutX(x2);
                super1.setLayoutY(y2);

                super1.process.setLayoutX(x2 + 10);
                super1.process.setLayoutY(495);
            });
        }


        super1.process.setOnMouseReleased(event -> {
            if (super1.getLayoutX() > 100) {
                MyEvent el = new MyEvent(event.getX(), event.getY(), 80, super1.type, "text");
                anchorPaneVisual.getChildren().add(el);
                anchorPaneVisual.getChildren().addAll(el.right);
                el.setId("MyEvent"+(getNodeCount()+1));
                el.switchByType(super1.type);

                if (radioBtn.isSelected()) {
                    el.changeVisible();
                }
                el.makeDrag();
            }
            super1.setLayoutX(x2);
            super1.setLayoutY(y2);
            super1.process.setLayoutX(x2 - super1.size * 0.3);
            super1.process.setLayoutY(492);
        });
        super1.process.setOnMousePressed(event -> {
            x2 = super1.getLayoutX();
            y2 = super1.getLayoutY();
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        super1.process.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            super1.process.setLayoutX(super1.process.getLayoutX() + deltaX);
            super1.process.setLayoutY(super1.process.getLayoutY() + deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

            super1.setLayoutX(super1.process.getLayoutX() + super1.process.getWidth() * 0.24);
            super1.setLayoutY(super1.process.getLayoutY() - super1.process.getHeight() * 16.4);
        });
    }

    /**
     * Функция подсчета кол-ва обьектов на панели
     */
    public int getNodeCount() {
        int i = 0;
        for (Node n : anchorPaneVisual.getChildren()
                ) {
            i++;
        }
        return i;
    }



}

