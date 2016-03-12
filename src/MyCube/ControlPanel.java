package MyCube;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ControlPanel extends JPanel {

	private GraphicsFrame gf;
	private JTextField parameterField = new JTextField();
	private int a = 1;
	
	
	
	public ControlPanel (GraphicsFrame _gf)
	{
		gf = _gf;
		
		parameterField.setText("0");
		setLayout(new GridLayout(20, 1, 2, 2));
		
		Label LabelX = new Label("X");
		Label LabelY = new Label("Y");
		Label LabelZ = new Label("Z");
		
		
		Label ScaleTitle = new Label("Scale");
		
		final JTextField scaleFieldX = new JTextField();
		scaleFieldX.setText("0");
		
		final JTextField scaleFieldY = new JTextField();
		scaleFieldY.setText("0");
		
		final JTextField scaleFieldZ = new JTextField();
		scaleFieldZ.setText("0");
		
		
		JButton scaleButton = new JButton("Scale");
		scaleButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double x = Double.parseDouble(scaleFieldX.getText());
						double y = Double.parseDouble(scaleFieldY.getText());
						double z = Double.parseDouble(scaleFieldZ.getText());
						
						if(a == 1) //cube
						{
							gf.cube.transform(x, y, z, 0, 1);
							gf.getGraphicPanel().repaint();
						}
						
						if(a == 2) //pyramid
						{
							gf.pyramid.transform(x, y, z, 0, 1);
							gf.getGraphicPanel().repaint();
						}
					}
				}
			);
		
		Label TranslateTitle = new Label("Translate");
		
		final JTextField translateFieldX = new JTextField();
		translateFieldX.setText("0");
		
		final JTextField translateFieldY = new JTextField();
		translateFieldY.setText("0");
		
		final JTextField translateFieldZ = new JTextField();
		translateFieldZ.setText("0");
		
		JButton translateButton = new JButton("Translate");
		translateButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double x = Double.parseDouble(translateFieldX.getText());
						double y = Double.parseDouble(translateFieldY.getText());
						double z = Double.parseDouble(translateFieldZ.getText());
						
						if(a == 1)
						{
							gf.cube.transform(x, y, z, 0, 0);
							gf.getGraphicPanel().repaint();
						}
						
						if(a == 2)
						{
							gf.pyramid.transform(x, y, z, 0, 0);
							gf.getGraphicPanel().repaint();
						}
						
					}
				}
			);
		
		Label RotateTitle = new Label("Rotate");
		
		Label Angle = new Label("Angle");
		
		final JTextField angleField = new JTextField();
		angleField.setText("0");
		
		final JTextField rotateFieldX = new JTextField();
		rotateFieldX.setText("0");
		
		final JTextField rotateFieldY = new JTextField();
		rotateFieldY.setText("0");
		
		final JTextField rotateFieldZ = new JTextField();
		rotateFieldZ.setText("0");

		JButton rotateXButton = new JButton("Rotate X");
		rotateXButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double angle = Double.parseDouble(angleField.getText());
						
						if(a == 1)
						{
							gf.cube.transform(0, 0, 0, angle, 2);
							gf.getGraphicPanel().repaint();
						}
						
						if(a == 2)
						{
							gf.pyramid.transform(0, 0, 0, angle, 2);
							gf.getGraphicPanel().repaint();
						}
					}
				}
			);

		
		JButton rotateYButton = new JButton("Rotate Y");
		rotateYButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double angle = Double.parseDouble(angleField.getText());
						
						if(a == 1)
						{
							gf.cube.transform(0, 0, 0, angle, 3);
							gf.getGraphicPanel().repaint();
						}
						
						if(a == 2)
						{
							gf.pyramid.transform(0, 0, 0, angle, 3);
							gf.getGraphicPanel().repaint();
						}
						
					}
				}
			);
		
		
		JButton rotateZButton = new JButton("Rotate Z");
		rotateZButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double angle = Double.parseDouble(angleField.getText());
						
						if(a == 1)
						{
							gf.cube.transform(0, 0, 0, angle, 4);
							gf.getGraphicPanel().repaint();
						}
						
						if(a == 2)
						{
							gf.pyramid.transform(0, 0, 0, angle, 2);
							gf.getGraphicPanel().repaint();
						}
						
					}
				}
			);
		
		JButton arbitraryButton = new JButton("Arbitrary"); //arbitrary button
		arbitraryButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						double x = Double.parseDouble(rotateFieldX.getText());
						double y = Double.parseDouble(rotateFieldY.getText());
						double z = Double.parseDouble(rotateFieldZ.getText());
						double angle = Double.parseDouble(angleField.getText());
						
						if(a == 1)
						{
							gf.cube.transform(x, y, z, angle, 5);
							gf.getGraphicPanel().repaint();
						
						}
						
						if(a == 2)
						{
							gf.pyramid.transform(x, y, z, angle, 5);
						}
						
					}
				}
			);
		
		final JRadioButton cubeButton = new JRadioButton("Cube");
		cubeButton.setSelected(true); //sets Cube transformations as default
		
		
		
		final JRadioButton pyramidButton = new JRadioButton("Pyramid");
		
		cubeButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0){
						pyramidButton.setSelected(false);;
						a = 1;
						
					}
			
				}
		);
		
		pyramidButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						cubeButton.setSelected(false);
						a = 2;
					
					}
				}
				
		);
		
		add(ScaleTitle); //labels taken out until further notice
		//add(LabelX); 
		add(scaleFieldX);
		//add(LabelY);
		add(scaleFieldY);
		//add(LabelZ);
		add(scaleFieldZ);
		add(scaleButton);
		add(TranslateTitle);
		//add(LabelX);
		add(translateFieldX);
		//add(LabelY);
		add(translateFieldY);
		//add(LabelZ);
		add(translateFieldZ);
		add(translateButton);
		add(RotateTitle);
		//add(Angle);
		add(angleField);
		//add(LabelX);
		add(rotateFieldX);
		//add(LabelY);
		add(rotateFieldY);
		//add(LabelZ);
		add(rotateFieldZ);
		add(rotateXButton);
		add(rotateYButton);
		add(rotateZButton);
		add(arbitraryButton);
		add(cubeButton);
		add(pyramidButton);
	}
	
}