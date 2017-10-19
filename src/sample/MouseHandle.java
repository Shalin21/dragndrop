package sample;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by admin on 08.10.17.
 */
public class MouseHandle implements EventHandler< MouseEvent > {
    private boolean gotFirst    = false;
    private Line    line, head1, head2;
    private Pane    pane;
    private double  x1, y1, x2, y2;
    double          phi         = Math.toRadians( 40 );
    double          barb        = 20;

    public MouseHandle( Pane pane ) {
        this.pane = pane;
    }

    @Override
    public void handle( MouseEvent event ) {
        if( event.getEventType() == MouseEvent.MOUSE_CLICKED ) {
            if( !gotFirst ) {
                x1 = x2 = event.getX();
                y1 = y2 = event.getY();
                line = new Line( x1, y1, x2, y2 );
                head1 = new Line( x2, y2, x2, y2 );
                head2 = new Line( x2, y2, x2, y2 );
                pane.getChildren().add( line );
                pane.getChildren().add( head1 );
                pane.getChildren().add( head2 );
                gotFirst = true;
            } else {
                line = null;
                gotFirst = false;
            }
        } else {
            if( line != null ) {
                x2 = event.getX();
                y2 = event.getY();
                // update line
                line.setEndX( x2 );
                line.setEndY( y2 );
                // draw head
                // http://www.coderanch.com/t/340443/GUI/java/Draw-arrow-head-end-line
                double dx = x2 - x1;
                double dy = y2 - y1;
                double theta = Math.atan2( dy, dx );
                double x, y, rho = theta + phi;

                x = x2 - barb * Math.cos( rho );
                y = y2 - barb * Math.sin( rho );
                head1.setStartX( x2 );
                head1.setStartY( y2 );
                head1.setEndX( x );
                head1.setEndY( y );
                rho = theta - phi;
                x = x2 - barb * Math.cos( rho );
                y = y2 - barb * Math.sin( rho );
                head2.setStartX( x2 );
                head2.setStartY( y2 );
                head2.setEndX( x );
                head2.setEndY( y );
            }
        }
    }

}

