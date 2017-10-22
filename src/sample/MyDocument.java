package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

/**
 * Created by admin on 21.10.17.
 */
public class MyDocument extends Group {
    Line line1 = new Line();
    Line line2 = new Line();
    Line line3 = new Line();
    CubicCurve line4 = new CubicCurve();


    public MyDocument(double x, double y, double size) {
        line1.setStartX(x);
        line1.setStartY(y+size);
        line1.setEndX(x);
        line1.setEndY(y);

        line2.setStartX(x);
        line2.setStartY(y);
        line2.setEndX(x+size*2);
        line2.setEndY(y);

        line3.setStartX(x+size*2);
        line3.setStartY(y);
        line3.setEndX(x+size*2);
        line3.setEndY(y+size);

        line4.setStartX(x);
        line4.setStartY(y+size);

        line4.setControlX1(x+size/2);
        line4.setControlY1(y+size+size*0.6);
        line4.setControlX2(x+size+size/4);
        line4.setControlY2(y+size-size*0.6);
        line4.setFill(null);
        line4.setStroke(Color.BLACK);
//                                   cubicCurve.setStyle("-fx-stroke-dash-array: 2;");=
        line4.setStrokeWidth(1);
        line4.setEndX(x+size*2);
        line4.setEndY(y+size);

        super.getChildren().addAll(line1, line2, line3, line4);

    }
}
