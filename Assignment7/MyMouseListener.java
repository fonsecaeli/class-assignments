import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.MouseInputListener;

public class MyMouseListener implements MouseInputListener {
    public void mouseClicked(MouseEvent e) {
        Point mouseClick = e.getPoint();
        double x = mouseClick.getX();
        double y = mouseClick.getY();
        int column = (int)(x/LifeExtra.SIZE);
        int row = (int)(y/LifeExtra.SIZE);
        if(row < 5 && column < 5) {
            LifeExtra.play = true;
            Graphics g = LifeExtra.panel.getGraphics();
            LifeExtra.run(LifeExtra.currentWorld, LifeExtra.nextWorld, g, LifeExtra.panel, LifeExtra.time);
        }
        else {
            LifeExtra.play = false;
            LifeExtra.addPoint(LifeExtra.currentWorld, mouseClick);
        }

    } 
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}
