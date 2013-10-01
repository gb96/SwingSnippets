import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * From <a href=
 * "http://stackoverflow.com/questions/10100815/swing-popover-with-arrows">Stack
 * Overflow</a>
 * 
 * @author bowering
 * 
 */
public class WindowMoveAdapter extends MouseAdapter {
	private boolean dragging = false;
	private int prevX = -1;
	private int prevY = -1;

	@Override
	public void mousePressed(final MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			dragging = true;
		}
		prevX = e.getXOnScreen();
		prevY = e.getYOnScreen();
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		if (prevX != -1 && prevY != -1 && dragging) {
			Window w = SwingUtilities.getWindowAncestor(e.getComponent());
			if (w != null && w.isShowing()) {
				Rectangle rect = w.getBounds();
				w.setBounds(rect.x + (e.getXOnScreen() - prevX),
						rect.y + (e.getYOnScreen() - prevY), rect.width,
						rect.height);
			}
		}
		prevX = e.getXOnScreen();
		prevY = e.getYOnScreen();
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		dragging = false;
	}
}
