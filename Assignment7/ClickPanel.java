import java.awt.*;
import java.awt.event.*;
import java.util.*;

//An example of using an inner class.
public class MyMouseListener implements MouseListener {

	public static Point p = null;

    public void mousePressed(MouseEvent e) {
    }

    /* Empty method definition. */
    public void mouseReleased(MouseEvent e) {
    }

    /* Empty method definition. */
    public void mouseEntered(MouseEvent e) {
    }

    /* Empty method definition. */
    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
   		p = e.getPoint();
    }
}
