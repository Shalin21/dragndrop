package sample.SerializeModels;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Created by admin on 26.10.17.
 * МОДЕЛЬ ДЛЯ СЕРИАЛИЗАЦИИ ЭЛЕМЕНТОВ ДИАГРАММЫ
 */

public class MyCircleModel implements Serializable {

    private double x,y,size;
    private int type;
    private String text = new String();
    private   String figure = new String ();
    private   String id = new String();
    public MyCircleModel(double x, double y, double size, int type, String text, String figure, String id) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
        this.text = text;
        this.figure=figure;
        this.id= id;
    }
    public MyCircleModel(){}
    @XmlElement(name = "X")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @XmlElement(name = "Y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    @XmlElement(name = "Size")
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    @XmlElement(name = "Type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @XmlElement(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @XmlElement(name = "Figure")
    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }
    @XmlElement(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
