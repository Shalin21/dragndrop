package sample;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11.10.17.
 */
public class MyCircle extends Circle {
    Anchor top = new Anchor();
    Anchor bot = new Anchor();
    Anchor left = new Anchor();
    Anchor right = new Anchor();
    Label text = new Label();

    public MyCircle(double centerX, double centerY, double radius, int type) {
        super(centerX, centerY, radius);
        top = new Anchor(centerX, centerY-radius, 4, 3);
        bot = new Anchor(centerX, centerY+radius, 4, 1);
        left = new Anchor(centerX-radius, centerY, 4,4);
        right = new Anchor(centerX+radius, centerY, 4, 2);
        top.setVisible(false);
        bot.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);
        DoubleProperty doubleXProperty = new SimpleDoubleProperty();
        DoubleProperty doubleYProperty = new SimpleDoubleProperty();
        doubleXProperty.bind(super.centerXProperty().subtract(13));
        doubleYProperty.bind(super.centerYProperty().subtract(10));
        text.layoutXProperty().bindBidirectional(doubleXProperty);
        text.layoutYProperty().bindBidirectional(doubleYProperty);
        text.setFont(Font.font(null, FontWeight.BLACK.BOLD, 14));
        switch (type){
            case 1:{}
            case 2:{}
            case 3:{}
        }
        Dragable();
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

}