package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 17.10.17.
 */
public class MyGroup extends Rectangle implements Serializable{
    Label text = new Label();
    //Rectangle rectangle = new Rectangle();
    double size;
    Anchor top = new Anchor();
    Anchor bot = new Anchor();
    Anchor left = new Anchor();
    Anchor right = new Anchor();
    Line line1 = new Line();
    Line line2 = new Line();
    Line line3 = new Line();
    Line line4 = new Line();
    int type;
    double x,y;

    public MyGroup( double x, double y, double size, int type) {
        this.type=type;
        this.text.setText("text");
        super.setLayoutX(x);
        super.setLayoutY(y);
        super.setHeight(size);
        super.setWidth(size*2);
        size=size;
        top = new Anchor(x+size, y, 4, 1);
       bot = new Anchor(x+size, y+size, 4, 3);
        left = new Anchor(x, y+size/2, 4,4);
        right = new Anchor(x+size*2, y+size/2, 4, 2);
        this.x=x;
        this.y=y;
        top.setParent(this);
        bot.setParent(this);
        left.setParent(this);
        right.setParent(this);

        top.setVisible(false);
        bot.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);

        super.setStroke(Color.BLACK);
        text.setLayoutX(x+((size*0.9) + (text.getText().length()*3)));
        text.setLayoutY(y+size/2.9);

        line1.startXProperty().bind(super.layoutXProperty().add(5));
        line1.startYProperty().bind(super.layoutYProperty());
        line1.endXProperty().bind(super.layoutXProperty().add(5));
        line1.endYProperty().bind(super.layoutYProperty().add(size));
        line2.startXProperty().bind(super.layoutXProperty().add(10));
        line2.startYProperty().bind(super.layoutYProperty());
        line2.endXProperty().bind(super.layoutXProperty().add(10));
        line2.endYProperty().bind(super.layoutYProperty().add(size));

        line3.startXProperty().bind(super.layoutXProperty().add(size*2-5));
        line3.startYProperty().bind(super.layoutYProperty());
        line3.endXProperty().bind(super.layoutXProperty().add(size*2-5));
        line3.endYProperty().bind(super.layoutYProperty().add(size));

        line4.startXProperty().bind(super.layoutXProperty().add(size*2-10));
        line4.startYProperty().bind(super.layoutYProperty());
        line4.endXProperty().bind(super.layoutXProperty().add(size*2-10));
        line4.endYProperty().bind(super.layoutYProperty().add(size));

        bot.centerXProperty().bind(super.layoutXProperty().add(size));
        bot.centerYProperty().bind(super.layoutYProperty().add(size));

        top.centerXProperty().bind(super.layoutXProperty().add(size));
        top.centerYProperty().bind(super.layoutYProperty());

        left.centerXProperty().bind(super.layoutXProperty());
        left.centerYProperty().bind(super.layoutYProperty().add(size/2));

        right.centerXProperty().bind(super.layoutXProperty().add(size*2));
        right.centerYProperty().bind(super.layoutYProperty().add(size/2));

    }
    public void byType(int type){

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
            case 4:{
                typeFour();
                break;
            }
        }
    }
    private void typeFour() {

        super.setFill(Color.ORANGE);
        super.setArcHeight(20);
        super.setArcWidth(20);
        Pane parent = (Pane) super.getParent();
        parent.getChildren().addAll(this.text);
    }

    private void typeThree() {

        super.setFill(Color.YELLOW);
        Pane parent = (Pane) super.getParent();
        parent.getChildren().addAll(this.text);
       // super.getChildren().removeAll();
        //super.getChildren().addAll( super, this.text);
    }

    private void typeTwo(double size) {
        //Line line = new Line();

        super.setFill(Color.CYAN);
        Pane parent= (Pane) super.getParent();

        parent.getChildren().addAll(this.text, this.line1);
       // super.getChildren().removeAll();
        //super.getChildren().addAll( super, this.text,  line);
    }

    private void typeOne(double size) {
        super.setFill(Color.LIGHTBLUE);
        Pane parent = (Pane) super.getParent();
        parent.getChildren().addAll(this.text,line1,line2,line3,line4);
       // super.getChildren().removeAll();
        //super.getChildren().addAll( super, this.text,  line1, line2, line3, line4);
    }



    public List<Anchor> getConnectionPoints(){
        List<Anchor> list = new ArrayList<>();
        list.add(top);
        list.add(bot);
        list.add(left);
        list.add(right);
        return list;
    }

    public void changeVisible(){

        bot.setVisible(!bot.isVisible());
        top.setVisible(!top.isVisible());
        left.setVisible(!left.isVisible());
        right.setVisible(!right.isVisible());

    }
}
