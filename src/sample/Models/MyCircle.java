package sample.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.Models.Anchor;

import javax.xml.bind.annotation.XmlElement;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11.10.17.
 */
public class MyCircle extends Circle implements Serializable{
   public Anchor top = new Anchor();
    public Anchor bot = new Anchor();
    public Anchor left = new Anchor();
    public Anchor right = new Anchor();

    public Label text = new Label();
    public int type;
    public double x,y,size;
    SimpleIntegerProperty asd = new SimpleIntegerProperty();
    @XmlElement(name = "asd")
    public int getAsd() {
        return asd.get();
    }

    public SimpleIntegerProperty asdProperty() {
        return asd;
    }

    public void setAsd(int asd) {
        this.asd.set(asd);
    }
    public MyCircle(double centerX, double centerY, double radius, int type) {
        super(centerX, centerY, radius);
        super.setFill(Color.GREY);
        super.setStroke(Color.BLACK);
        super.setStrokeWidth(2);
        this.type=type;
        this.x = centerX;
        this.y = centerY;
        top = new Anchor(centerX, centerY-radius, 6, 3);
        bot = new Anchor(centerX, centerY+radius, 6, 1);
        left = new Anchor(centerX-radius, centerY, 6,4);
        right = new Anchor(centerX+radius, centerY, 6, 2);
        top.setParent(this);
        bot.setParent(this);
        left.setParent(this);
        right.setParent(this);
        top.setVisible(false);
        bot.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);
        text.setLayoutX(super.getCenterX()-14);
        text.setLayoutY(super.getCenterY()-10);

        this.size = radius;
        text.setFont(Font.font(null, FontWeight.BLACK.BOLD, 14));

        Dragable();
        textDrag();
    }


public void Dragable(){

        bot.centerXProperty().bind(super.centerXProperty());
        bot.centerYProperty().bind(super.centerYProperty().subtract(super.getRadius()));

        top.centerXProperty().bind(super.centerXProperty());
        top.centerYProperty().bind(super.centerYProperty().add(super.getRadius()));

        left.centerXProperty().bind(super.centerXProperty().subtract(super.getRadius()));
        left.centerYProperty().bind(super.centerYProperty());

        right.centerXProperty().bind(super.centerXProperty().add(super.getRadius()));
        right.centerYProperty().bind(super.centerYProperty());



}
public void changeVisible(){

    bot.setVisible(!bot.isVisible());
    top.setVisible(!top.isVisible());
    left.setVisible(!left.isVisible());
    right.setVisible(!right.isVisible());

}

private void textDrag(){
    switch (type){
        case 1:{
            text.setText("XOR");
            DoubleProperty doubleXProperty = new SimpleDoubleProperty();
            DoubleProperty doubleYProperty = new SimpleDoubleProperty();
            doubleXProperty.bind(super.centerXProperty().subtract(14));
            doubleYProperty.bind(super.centerYProperty().subtract(10));
            text.layoutXProperty().bindBidirectional(doubleXProperty);
             text.layoutYProperty().bindBidirectional(doubleYProperty);
            //doubleXProperty.bind(super.centerXProperty().subtract(14));
            break;
        }
        case 2:{text.setText("AND");
            DoubleProperty doubleXProperty = new SimpleDoubleProperty();
            DoubleProperty doubleYProperty = new SimpleDoubleProperty();
            doubleXProperty.bind(super.centerXProperty().subtract(14));
            doubleYProperty.bind(super.centerYProperty().subtract(10));
            text.layoutXProperty().bindBidirectional(doubleXProperty);
            text.layoutYProperty().bindBidirectional(doubleYProperty);
            break;}
        case 3:{text.setText("OR");
            DoubleProperty doubleXProperty = new SimpleDoubleProperty();
            DoubleProperty doubleYProperty = new SimpleDoubleProperty();
            doubleXProperty.bind(super.centerXProperty().subtract(14));
            doubleYProperty.bind(super.centerYProperty().subtract(10));
            text.layoutXProperty().bindBidirectional(doubleXProperty);
            text.layoutYProperty().bindBidirectional(doubleYProperty);
            break;}
    }
}


public List<Anchor> getConnectionPoints(){
    List<Anchor> list = new ArrayList<>();
    list.add(top);
    list.add(bot);
    list.add(left);
    list.add(right);
    return list;
}
public boolean containConnectPoint(Anchor c){
    if(this.top.equals(c) || this.bot.equals(c) || this.left.equals(c) || this.right.equals(c)){return true;}
    else
        return false;
}

    public String getText() {
        return text.getText();
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    public void writeObject(java.io.ObjectOutputStream stream) throws IOException{
    stream.writeObject(x);
        stream.writeObject(y);
        stream.writeObject(size);
        stream.writeObject(type);
    }
    public void readObject(java.io.ObjectInputStream stream) throws IOException{
        stream.readDouble();
        stream.readDouble();
        stream.readDouble();
        stream.readInt();
    }
}
