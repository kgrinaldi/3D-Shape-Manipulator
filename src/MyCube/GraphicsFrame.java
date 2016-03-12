package MyCube;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class GraphicsFrame extends JFrame {

	// -- JPanel for the graphics
	private GraphicPanel gp;
	
	// -- JPanel for the GUI controls
	private ControlPanel cp;
		
	// -- Cube object to be used by all
	public Cube cube = new Cube();
	
	public Pyramid pyramid = new Pyramid(); //added pyramid
	
	GraphicPanel getGraphicPanel()
	{
		return gp;
	}
	
	public GraphicsFrame (int height, int width)
	{
		setTitle("Basic Graphics Frame");
		// -- add some items to the content pane of the frame
		//JButton okButton = new JButton("OK");
		//frame.add(okButton);
				
		// -- size of the frame: width, height
		setSize(width, height);
		
		// -- center the frame on the screen
		setLocationRelativeTo(null);
		
		URL imgURL = getClass().getResource("Logo.png");//got alternate code from the internet
		BufferedImage bi = null;		
		//File inputfile = new File("./Logo.png");
		try {
			bi = ImageIO.read(imgURL);
		} catch (IOException e) {
			//TODO Auto-generated catch block
		 	e.printStackTrace();
			System.out.println("error reading logo, exiting");
			System.exit(0);
		}
		// -- this is the call that actually changes the icon
		setIconImage(bi);
				
		// -- shut down the entire application when the frame is closed
		//    if you don't include this the application will continue to
		//    run in the background and you'll have to kill it by pressing
		//    the red square in eclipse
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// -- set the layout manager and add items
		//    5, 5 is the border around the edges of the areas
		setLayout(new BorderLayout(5, 5));

		gp = new GraphicPanel(this);
		this.add(gp, BorderLayout.CENTER);
		
		cp = new ControlPanel(this);
		this.add(cp, BorderLayout.EAST);
		
		// -- show the frame on the screen
		setVisible(true);
		
	}
	
	
	public static void main (String[] args)
	{
		GraphicsFrame gf = new GraphicsFrame(512, 768);
	}
}
