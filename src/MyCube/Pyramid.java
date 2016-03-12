package MyCube;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pyramid {

	// -- cube vertices, 1 X 1 X 1 centered at the origin (0, 0, 0)
	//    last point is the center, not a vertex!
	public double [][] v = {
			{0.5, -0.5,  0.5, -0.5,  0  , 0  }, //x
			{0.5,  0.5,  0.5,  0.5, -0.5, 0  }, //y
			{0.5,  0.5, -0.5, -0.5,  0  , 0  }, //z
			{1.0,  1.0,  1.0,  1.0,  1.0, 1.0}  //center
	};
	
	// -- edges (indices of connected vertices)
	public int edges [][] =
		{
			{0, 1},
			{1, 3},
			{2, 0},
			{2, 3},
			{0, 4},
			{1, 4},
			{2, 4},
			{3, 4}
		};
	
	public int faces [][] = 
		{
			{4, 3 ,2},     //back - working
			{2, 0, 4},    //right - working
			{1, 4, 0},    //front - working
			{1, 3, 4},    //left  - working
			{2, 3, 1, 0} //bottom - working
		};

	public Color colors[] = {Color.red,Color.orange, Color.yellow, Color.green, Color.blue, new Color(255,0,255), Color.white};
	
	// -- construct the pyramid
	public Pyramid (){}
	
	
	public void draw (Graphics2D g2d, Color c)
	{
		// -- set the drawing color
				g2d.setColor(c);
				
				boolean here;
				
				for(int i = 0; i < faces.length; i++) 
				{
					here = FaceVisibility(i);
					
					System.out.println(here);
					
					if(here == true)
					{
						int xpoint[] = {0,0,0,0}; //nothing is being inserted into the array
						int ypoint[] = {0,0,0,0};
						
						int TempX = 0;
						int TempY = 0;
						
						int SideAmount = 0;
									
						if(faces[i].length == 4)
						{
							for(int j = 0; j < 4; j++) //the problem here is that each row of faces is not equal to the first
							{
								TempX = (int) v[0] [faces[i][j]];
								xpoint[j] = TempX;
								System.out.println("xpoint" + j + ": " + xpoint[j]);
								
								
								TempY = (int) v[1] [faces[i][j]];
								ypoint[j] = TempY;
								System.out.println("ypoint" + j + ": " + ypoint[j]);
							}
							
							SideAmount = 4;
						}
						
						else if(faces[i].length == 3)
						{
							for(int j = 0; j < 3; j++) //the problem here is that each row of faces is not equal to the first
							{
								TempX = (int) v[0] [faces[i][j]];
								xpoint[j] = TempX;
								System.out.println("xpoint" + j + ": " + xpoint[j]);
								
								
								TempY = (int) v[1] [faces[i][j]];
								ypoint[j] = TempY;
								System.out.println("ypoint" + j + ": " + ypoint[j]);
							}
							
							SideAmount = 3;
						}
						
						g2d.setColor(colors[i]);
						g2d.fillPolygon(xpoint, ypoint, SideAmount); //x[], y[], sides
					}
					
					else
					{
						
					}
					
				}
	}
	

	public boolean FaceVisibility(int f) //use to determine point's visibility
	{
		SceneGraph sg = new SceneGraph();
		
		int v00 = faces[f][0]; //these variables have been moved because the pyramid will have different faces than the cube
		int v01 = faces[f][1];
		int v02 = faces[f][2];
		
		boolean visible = sg.isVisible(v, v00, v01, v02);
		
		return visible;
				
		
		//make sure faces are in the right order
		
		//if(visible = true)
		//{
			//face is visible
		//}
	}

	
	/*
	public void drawLine(double x0, double y0, double x1, double y1, Graphics2D g2d, Color c) //makes a line out of pixels
	{
		double x;
		double y;
		
		g2d.setColor(c);
		
		for(double t = 0.0; t <= 1.0; t = t + .01)
		{
			x = x0 + (x1 - x0) * t;
			y = y0 + (y1 - y0) * t;
			
			g2d.fillOval((int)x, (int)y, 2, 2);
			
		}
		
	}
	*/


	/* -- Transformation 
          0: translate
          1: scale
          2: rotate x
          3: rotate y
          4: rotate z
          5: arbitrary
	-- */
	public void transform (double x, double y, double z, double factor, int transformation)
	{
		double trans[][] = null;
		
		int center = v[0].length - 1;
		
		System.out.println("Before : ");
		for(int a = 0; a < v.length; a++)
		{
			System.out.print("{");
			for(int b = 0; b < v.length; b++)
			{
				System.out.print(" " + v[a][b] + ",");
			}
			System.out.println("}");
		}
		
		switch (transformation) {
		case 0: // -- translate
			// -- translation matrix
			double translate[][] = {{1, 0, 0, x}, {0, 1, 0, y}, {0, 0, 1, z}, {0, 0, 0, 1}};
			trans = translate;
			break;
		case 1: // -- scale
		    {
			// -- translation to origin
			double trans0[][] = {{1, 0, 0, -v[0][center]}, {0, 1, 0, -v[1][center]}, {0, 0, 1, -v[2][center]}, {0, 0, 0, 1}};
			// -- scale
			double scale[][] = {{x, 0, 0, 0}, {0, y, 0, 0}, {0, 0, z, 0}, {0, 0, 0, 1}};
			// -- translate back to point
			double trans1[][] = {{1, 0, 0, v[0][center]}, {0, 1, 0, v[1][center]}, {0, 0, 1, v[2][center]}, {0, 0, 0, 1}};

			// -- build transformation matrix    trans1 X scale X trans0
			trans = matmult(scale, trans0);
			trans = matmult(trans1, trans);
		    }
			break;
		case 2: // -- rotate x
			{
			// -- degrees to radius
			double theta = factor * (Math.PI / 180.0);
			
			// -- translation to origin
			double trans0[][] = {{1, 0, 0, -v[0][center]}, {0, 1, 0, -v[1][center]}, {0, 0, 1, -v[2][center]}, {0, 0, 0, 1}};
			// -- rotate about X axis
			double rot[][] = {{1, 0, 0, 0}, {0, Math.cos(theta), -Math.sin(theta), 0}, {0, Math.sin(theta), Math.cos(theta), 0}, {0, 0, 0, 1}};
			// -- translate back to point
			double trans1[][] = {{1, 0, 0, v[0][center]}, {0, 1, 0, v[1][center]}, {0, 0, 1, v[2][center]}, {0, 0, 0, 1}};

			// -- build transformation matrix    trans1 X rot X trans0
			trans = matmult(rot, trans0);
			trans = matmult(trans1, trans);
			}
			break;
		case 3: // -- rotate y
			{
			// -- degrees to radius
			double theta = factor * (Math.PI / 180.0);
	
			// -- translation to origin
			double trans0[][] = {{1, 0, 0, -v[0][center]}, {0, 1, 0, -v[1][center]}, {0, 0, 1, -v[2][center]}, {0, 0, 0, 1}};
			// -- rotate about Y axis
			double rot[][] = {{Math.cos(theta), 0, -Math.sin(theta), 0}, {0, 1, 0, 0}, {Math.sin(theta), 0, Math.cos(theta), 0}, {0, 0, 0, 1}};
			//negated sines
			//works the same way
			
			// -- translate back to point
			double trans1[][] = {{1, 0, 0, v[0][center]}, {0, 1, 0, v[1][center]}, {0, 0, 1, v[2][center]}, {0, 0, 0, 1}};
	
			// -- build transformation matrix    trans1 X rot X trans0
			trans = matmult(rot, trans0);
			trans = matmult(trans1, trans);
			}
			break;
		case 4: // -- rotate z
			{
			// -- degrees to radius
			double theta = factor * (Math.PI / 180.0);
	
			// -- translation to origin
			double trans0[][] = {{1, 0, 0, -v[0][center]}, {0, 1, 0, -v[1][center]}, {0, 0, 1, -v[2][center]}, {0, 0, 0, 1}};
			// -- rotate about Z axis
			double rot[][] = {{Math.cos(theta), -Math.sin(theta), 0, 0}, {Math.sin(theta), Math.cos(theta), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
			// -- translate back to point
			double trans1[][] = {{1, 0, 0, v[0][center]}, {0, 1, 0, v[1][center]}, {0, 0, 1, v[2][center]}, {0, 0, 0, 1}};
	
			// -- build transformation matrix    trans1 X rot X trans0
			trans = matmult(rot, trans0);
			trans = matmult(trans1, trans);
			}
			break;
		case 5: // -- arbitrary(under construction)
			{
			//u
			double u[] = {x, y, z};
				
			//u magnitude
			double uMagnitude = Math.sqrt(u[0] + u[1] + u[2]);
			
			//v
			double V[] = {u[0]/uMagnitude, u[1]/uMagnitude, u[2]/uMagnitude};
			
			//d
			double d = Math.sqrt((Math.pow(V[1], 2)) + (Math.pow(V[2], 2)));
				
			//degrees
			double theta = factor * (Math.PI / 180);	
			
			//Translate to Origin
			double transA[][] = {{1, 0, 0, -v[0][center]}, {0, 1, 0, -v[1][center]}, {0, 0, 1, -v[2][center]}, {0, 0, 0, 1}};

			//Rotate X
			double rotA[][] = {{1, 0, 0, 0}, {0, V[2]/d, -V[1]/d, 0}, {0, V[1]/d, V[2]/d, 0}, {0, 0, 0, 1}};
			
			//Rotate Y
			double rotB[][] = {{d, 0, -V[0], 0}, {0, 1, 0, 0}, {V[0], 0, d, 0}, {0, 0, 0, 1}};
			
			//Rotate Z
			double rotC[][] = {{Math.cos(theta), -Math.sin(theta), 0, 0}, {Math.sin(theta), Math.cos(theta), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
			
			//DeRotate X
			double rotD[][] = {{1, 0, 0, 0}, {0, V[2]/d, V[1]/d, 0}, {0, -V[1]/d, V[2]/d, 0}, {0, 0, 0, 1}};
			
			//DeRotate Y
			double rotE[][] = {{d, 0, V[0], 0}, {0, 1, 0, 0}, {-V[0], 0, d, 0}, {0, 0, 0, 1}};
			
			//DeTranslate
			double transB[][] = {{1, 0, 0, v[0][center]}, {0, 1, 0, v[1][center]}, {0, 0, 1, v[2][center]}, {0, 0, 0, 1}};
			
			trans = matmult(rotA, transA);
			trans = matmult(rotB ,trans);
			trans = matmult(rotC, trans);
			trans = matmult(rotD, trans);
			trans = matmult(rotE, trans);
			trans = matmult(transB, trans);
			
			System.out.println("Arbitrary: ");
			for (int k = 0; k < trans.length; k++)
			{
				System.out.print("{");
				for (int l = 0; l < trans[0].length; l++)
				{
					System.out.print(" " + trans[k][l] + ",");
				}
				System.out.println("}");
				
			}
			
			}
			break;
		}
		v = matmult(trans, v); //trans ends up here to multiply with the vector
	
		
		System.out.println("After: ");
		for (int c = 0; c < v.length; c++)
		{
			System.out.print("{");
			for (int d = 0; d < v[0].length; d++)
			{
				System.out.print(" " + v[c][d] + ",");
			}
			System.out.println("}");
			
		}
		
		
	}
	

	
	// -- Standard matrix multiplication routine
	public double [][] matmult(double A[][], double B[][]) {
		int rowsA = A.length;
		int colsA = A[0].length;
		int rowsB = B.length;
		int colsB = B[0].length;
		
		if (colsA != rowsB) {

			return null;
		}
		
		double C[][] = new double [rowsA][colsB];
		
		for (int i = 0; i < rowsA; ++i) {
			for (int j = 0; j < colsB; ++j) {
				for (int k = 0; k < rowsB; ++k) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		return C;
	}

	public void Reset()
	{
		
	}
	
}
