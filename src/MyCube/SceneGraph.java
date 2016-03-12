package MyCube;

public class SceneGraph {

	public double viewer[] = {0, 0, -1}; //ask what this does later
	
	public SceneGraph(){}
	
	public boolean isVisible(double v[][], int v00, int v01, int v02)
	{
		boolean result = false;
		
		double x0 = v[0][v00]; //x coordinate of v00
		double y0 = v[1][v00]; //y coordinate of v00
		double z0 = v[2][v00]; //z coordinate of v00
		
		double x1 = v[0][v01]; //x coordinate of v01
		double y1 = v[1][v01]; //y coordinate of v01
		double z1 = v[2][v01]; //z coordinate of v01
		
		double x2 = v[0][v02]; //x coordinate of v02
		double y2 = v[1][v02]; //y coordinate of v02
		double z2 = v[2][v02]; //z coordinate of v02
		
		double v0x = x1 - x0;
		double v0y = y1 - y0;
		double v0z = z1 - z0;

		double v1x = x2 - x0;
		double v1y = y2 - y0;
		double v1z = z2 - z0;

		//compute the surface normal
		double crossx = v0y * v1z - v0z * v1y; //cross product
		double crossy = v0z * v1x - v0x * v1z;
		double crossz = v0x * v1y - v0y * v1x;
		
		System.out.println(crossx + "," + crossy + "," + crossz);
		
		//compute the angle between surface normal and the viewer
		double dotprod = crossx * viewer[0] + crossy * viewer[1] + crossz * viewer[2]; //dot product
		
		System.out.println(dotprod); //if positive => looking at the front
									 //if negative => looking at the back
		
		if(dotprod >= 0)
		{
			result = true;
		}
		
		if(dotprod <= 0)
		{
			result = false;
		}
		
		
		return result;
	}
	
}