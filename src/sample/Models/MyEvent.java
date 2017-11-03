package sample.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

/**
 * Created by admin on 21.10.17.
 */
public class MyEvent extends Polygon {
    public  Anchor top = new Anchor();
    public  Anchor bot = new Anchor();
    public Anchor left = new Anchor();
    public Anchor right = new Anchor();
    public Rectangle process = new Rectangle();
    public double x,y,size;
    public int type;
    public  Label text1 = new Label();
    public String text = new String();


    public MyEvent(double x, double y, double size, int type, String text) {
        this.x = x;
        this.y = y;
    this.size = size;
    this.type = type;
    this.text1.setText(text);
    this.text = text;
        if(type==1) {
            text1.setLayoutX(super.getLayoutX() + size * 2.2);
            text1.setLayoutY(super.getLayoutY() + size * 1.6);
        }
        else if(type == 2){
            text1.setLayoutX(super.getLayoutX() + size * 1.6);
            text1.setLayoutY(super.getLayoutY() + size * 1.6);
        }
        else {
            text1.setLayoutX(super.getLayoutX() + size*0.4);
            text1.setLayoutY(super.getLayoutY() + size*0.3);
        }
//        right = new Anchor(x+size*1.5, y+size/2, 4, 2);
//        left = new Anchor(x-size/2, y+size/2, 4, 2);
//        top = new Anchor(x+size/2, y, 4, 2);
//        bot = new Anchor(x+size/2, y+size, 4, 2);

//        right = new Anchor(x+size*2, y+size/2, 4, 2);
//        left = new Anchor(x-size/2, y+size/2, 4, 2);
//        top = new Anchor(x+size, y, 4, 2);
//        bot = new Anchor(x+size, y+size, 4, 2);
       // makeDrag();
        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                Pane pane = (Pane)this.getParent();
                if(this.type==1) {
                    pane.getChildren().removeAll(this, this.text1);
                }
                else if(this.type==2){pane.getChildren().removeAll(this, this.text1, this.right, this.left, this.top, this.bot);}
                else if(this.type==3){pane.getChildren().removeAll(this,  this.text1, this.right, this.left, this.top, this.bot, this.process);}

            }
        });
    }

    public void switchByType(int type) {
        switch (type){
            case 1:{
                typeOne();
            break;
        }
            case 2:{
                typeTwo();
            break;
        }
            case 3:
            typeThree();
        }
    }

    private void typeThree() {
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
        //super.setLayoutX(x+10);
        process.setLayoutX(x-size*0.1);
        process.setLayoutY(y-size*0.2);
        process.setHeight(size);
        process.setWidth(size*1.7);
        process.setFill(new Color(0.55, 0.98, 0.49, 1));
        process.setStroke(Color.BLACK);
        process.setStrokeWidth(1);
        Pane parent = (Pane) super.getParent();
        parent.getChildren().addAll(process, text1);
        right = new Anchor(x+size*1.5, y+size/2, 6, 2);
        right.setParent(this);
        right.centerXProperty().bind(super.layoutXProperty().add(size*1.85));
        right.centerYProperty().bind(super.layoutYProperty().add(size-27));
        right.setVisible(false);



        //this.x=super.getLayoutX();
        //this.y=y;
    }

    private void typeTwo() {
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


        right = new Anchor(x+size*1.5, y+size/2, 6, 2);
        left = new Anchor(x-size/2, y+size/2, 6, 4);
        top = new Anchor(x+size/2, y, 6, 1);
        bot = new Anchor(x+size/2, y+size, 6, 3);
        right.setVisible(false);
        left.setVisible(false);
        top.setVisible(false);
        bot.setVisible(false);
        right.setParent(this);
        left.setParent(this);
        top.setParent(this);
        bot.setParent(this);
        right.centerXProperty().bind(super.layoutXProperty().add(size*2.8-3));
        right.centerYProperty().bind(super.layoutYProperty().add(size+60));
        left.centerXProperty().bind(super.layoutXProperty().add(size*0.8-3));
        left.centerYProperty().bind(super.layoutYProperty().add(size+60));
        top.centerXProperty().bind(super.layoutXProperty().add(size*1.8-3));
        top.centerYProperty().bind(super.layoutYProperty().add(size+20));
        bot.centerXProperty().bind(super.layoutXProperty().add(size*1.8-3));
        bot.centerYProperty().bind(super.layoutYProperty().add(size+100));
        //text1.layoutXProperty().bind(super.layoutXProperty());
        //text1.layoutYProperty().bind(super.layoutYProperty());
        Pane parent = (Pane) super.getParent();
        parent.getChildren().addAll(text1, top, bot, left, right);

    }

    private void typeOne() {
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
       // super.setLayoutX(x);
       // this.x=super.getLayoutX();
       // this.y=y;

        right = new Anchor(x+size*2, y+size/2, 6, 2);
        right.setParent(this);
        right.setVisible(false);
        right.centerXProperty().bind(super.layoutXProperty().add(size*3.3-3));
        right.centerYProperty().bind(super.layoutYProperty().add(size+60));
       // text1.layoutXProperty().bind(super.layoutXProperty());
       // text1.layoutYProperty().bind(super.layoutYProperty());
        Pane parent = (Pane) super.getParent();
        parent.getChildren().addAll(text1);
    }


    private final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
    public void makeDrag(){
        text1.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Split");
            dialog.setHeaderText("After how many characters should be splitted?");
            Optional<String> result = dialog.showAndWait();
            if (result.get().length() < 45) {
                if (result.get().length() > 15) {
                    if (result.get().length() >= 30) {
                        text1.setText(result.get().substring(0, 15) + "\n" + result.get().substring(15, 30) + "\n" + result.get().substring(30));
                    } else {
                        text1.setText(result.get().substring(0, 15) + "\n" + result.get().substring(15));
                    }
                } else if (result.get().length() < 15) {
                    text1.setText(result.get());
                }
            }
        });
        Pane anchorPaneVisual = (Pane) super.getParent();
        if(type !=3) {
            super.setOnMousePressed(event -> {
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            });
            super.setOnMouseDragged(event -> {
                double deltaX = event.getSceneX() - mousePosition.get().getX();
                double deltaY = event.getSceneY() - mousePosition.get().getY();
                        if ((super.getLayoutX() + deltaX > 0 && super.getLayoutX() + deltaX  < anchorPaneVisual.getWidth()-100) &&
                                (super.getLayoutY() + deltaY > 0 && super.getLayoutY() + deltaY < anchorPaneVisual.getHeight()-100)) {

                            super.setLayoutX(super.getLayoutX() + deltaX);
                            super.setLayoutY(super.getLayoutY() + deltaY);
                            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

                            process.setLayoutX(super.getLayoutX() - process.getWidth());
                            process.setLayoutY(super.getLayoutY() + process.getHeight() * 12.8);
                            if(type==1) {
                                text1.setLayoutX(super.getLayoutX() + size * 2.2);
                                text1.setLayoutY(super.getLayoutY() + size * 1.6);
                            }
                            else{
                                text1.setLayoutX(super.getLayoutX() + size * 1.6);
                                text1.setLayoutY(super.getLayoutY() + size * 1.6);
                            }

                        }
            });
        }
        process.setOnMousePressed(event -> {
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
        });
        process.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mousePosition.get().getX();
            double deltaY = event.getSceneY() - mousePosition.get().getY();
            process.setLayoutX(process.getLayoutX() + deltaX);
            process.setLayoutY(process.getLayoutY() + deltaY);
            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

            super.setLayoutX(process.getLayoutX() + process.getWidth() * 0.15);
            super.setLayoutY(process.getLayoutY() + process.getHeight() *0.07);
            text1.setLayoutX(super.getLayoutX() + size*0.4);
            text1.setLayoutY(super.getLayoutY() + size*0.3);
        });
    }

    public void changeVisible(){

        bot.setVisible(!bot.isVisible());
        top.setVisible(!top.isVisible());
        left.setVisible(!left.isVisible());
        right.setVisible(!right.isVisible());

    }
}
