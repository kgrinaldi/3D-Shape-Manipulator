package MyCube;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


// -- need to extend JPanel so that we can override some of
//    the default methods -- JPanel inherits from AWT Container
//    (can hold Components) which inherits from AWT Component
//    (can be displayed on a screen)
public class GraphicPanel extends JPanel {

	// -- JFrame container
	private GraphicsFrame gf;
	
	public GraphicPanel (GraphicsFrame _gf)
	{
		super();
		
		gf = _gf;

		this.setBackground(Color.gray);

		// -- The JPanel can have a mouse listener if desired
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
			
		}
		);
	
	}
	
	// -- this override sets the desired size of the JPanel which is
	//    used by some layout managers -- default desired size is 0,0
	//    which is, in general, not good -- will pull from layout manager
	public Dimension getPreferredSize() 
	{
		return new Dimension(50, 50);
	}
	
	// -- this override is where all the painting should be done. 
	//    DO NOT call it directly. Rather, call repaint() and let the
	//    event handling system decide when to call it
	//    DO NOT put graphics function call elsewhere in the code, although
	//    legal, it's bad practice and could destroy the integrity of the
	//    display
	
	
	
	public void paint(Graphics g)
	{
		// -- the base class paintComponent(g) method ensures 
		//    the drawing area will be cleared properly. Do not
		//    modify any attributes of g prior to sending it to
		//    the base class
		super.paintComponent(g);
		
		// -- for legacy reasons the parameter comes in as type Graphics
		//    but it is really a Graphics2D object. Cast it up since the
		//    Graphics2D class is more capable
		Graphics2D g2d = (Graphics2D)g;

		// -- get the height and width of the JPanel drawing area
		int width = this.getWidth();
		int height = this.getHeight();
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, width, height);
		
		
		if (gf.cube != null) {
			gf.cube.draw(g2d, Color.green);
		}
		
		if (gf.pyramid != null) {
			gf.pyramid.draw(g2d, Color.cyan);
		}
	}
	

}
