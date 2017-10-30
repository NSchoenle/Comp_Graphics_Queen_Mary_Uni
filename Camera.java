/*THIS IS A COURSEWORK PROGRAM. TEST IT WELL
 * 
 * THIS IS FOR THE ORTHOGRAPHIC PARALLEL PROJECTION.(DOP AND VPN AND COORD AXIS ARE ALL SAME) 
 * THE PERSPECTIVE WILL BE A SUBCLASS THAT OVERWRITES THESE METHODS
 */
public class Camera {
	/*Camera attributes given by instructors*/
	
	private double xmin, xmax, ymin, ymax; // USED FOR CAMERA WINDOW
	private double fcp, bcp; // NOT USED: front & back clippling planes
	private double ax, bx, ay, by; //USED FOR VIEWPORT 
	
	public Camera(double xmin_, double xmax_, double ymin_, double ymax_) {
		xmin = xmin_;
		xmax = xmax_;
		ymin = ymin_;
		ymax = ymax_;
		/*unused*/
		fcp = 0;
		bcp = 0;
		/*used in viewpoint transformation*/
		ax = 0;
		bx = 0;
		ay = 0;
		by = 0;
	}
	/*Check point Z coords
	 * OVER-RIDDEN FOR PERSPECTIVE*/
	public Vector3D getVPN() {
		/* return a vector that points towards the viewer. Used for face orientation */
		
		/*
		Point3D p1 = new Point3D (xmin,ymin,0);
		Point3D p2 = new Point3D (xmax,ymin,0);
		Point3D p3 = new Point3D (xmax,ymax,0);
		Vector3D v1 = new Vector3D(p2.x-p1.x,p2.y-p1.y, p2.z-p1.z);
		Vector3D v2 = new Vector3D(p3.x-p1.x,p3.y-p1.y, p3.z-p1.z);
		return v1.crossProduct(v2);
		*/
		return new Vector3D (0,0,1);
	}

	/*WORLD COORDS TO CAMERA COORDS 3D-3D 
	 * WILL NOT BE OVER-RIDDEN BY PERSPECTIVIE*/
	protected Point3D cameraTransform(final Point3D p) {
		Vector3D VPN = getVPN();
		Vector3D VUP = new Vector3D (0,1,0); //FIX VUP WITH MORE GENERIC PARAMS?
		Point3D VRP = new Point3D (0,0,0);/*FIX WITH MORE GENERAL POINT???*/
		
		Vector3D n = VPN;
		n.normalize();
		Vector3D u = VPN.crossProduct(VUP);
		u.normalize();
		Vector3D v = n.crossProduct(u);
	
		Matrix mat = new Matrix();
		mat.m[0][0] = u.x;
		mat.m[0][1] = u.y;
		mat.m[0][2] = u.z;
		mat.m[0][3] = -(u.x * VRP.x + u.y * VRP.y + u.z * VRP.z);
		
		mat.m[1][0] = v.x;
		mat.m[1][1] = v.y;
		mat.m[1][2] = v.z;
		mat.m[1][3] = -(v.x * VRP.x + v.y * VRP.y + v.z * VRP.z);
	
		mat.m[2][0] = n.x;
		mat.m[2][1] = n.y;
		mat.m[2][2] = n.z;
		mat.m[2][3] = -(n.x * VRP.x + n.y * VRP.y + n.z * VRP.z);
	
		mat.m[3][0] = 0;
		mat.m[3][1] = 0;
		mat.m[3][2] = 0;
		mat.m[3][3] = 1;

		return p.transform(mat);
		
	}
	/*VERTS MAPPED TO POINTS IN VIEW PLANE 3D -2D
	 * PROJECTED ONTO THE Z=0 PLANE
	 * OVER RIDEDEN BY PERSPECTIVE*/
	protected Point3D projectionTransform(final Point3D p) {
		/*
		Matrix t= new Matrix();
		t.m[0][0] = 0;
		t.m[0][1] = 0;
		t.m[0][2] = 0;
		t.m[0][3] = -(umax);
		
		t.m[1][0] = ;
		t.m[1][1] = ;
		t.m[1][2] = ;
		t.m[1][3] = ;
	
		t.m[2][0] = ;
		t.m[2][1] = ;
		t.m[2][2] = ;
		t.m[2][3] = ;
	
		t.m[3][0] = 0;
		t.m[3][1] = 0;
		t.m[3][2] = 0;
		t.m[3][3] = 1;
		*/
		return p; //unsure if correct
	}
	
	/*VIEW PLANE MAPPED TO SCREEN 2D-2D
	 * NOT OVER-RIDDEN BY PERSPECTIVE*/
	private final Point3D viewportTransform(final Point3D p) {
		//USES aX aY bX bY VALUES
		p.x = ax + bx*p.x;
		p.y = ay + by * p.y;
		return p;
	}
	/*Code for project given by instructors*/
	public final Point3D project(final Point3D p) {
		Point3D temp = cameraTransform(p);
		temp = projectionTransform(temp);
		return viewportTransform(temp);
	}
	/*SET UP VIEWPORT
	 * NOT OVER-RIDDEN BY PERSPECTIVE*/
	public void setViewport(int width, int height) {
		/* calculate ax, bx, ay, by 
		 * bX = dVx/dWx; aX = Vxmin - bX*Wxmin; 
		 * bY = dVy/dWy; aY = Vymin - bY*Wymin.*/
		bx = width/(xmax - xmin);
		by = height/(ymax - ymin);
				
		ax = 0 - bx*xmin;
		ay = 0 - by*ymin;
		
		
	}

	public String toString() {
		return "" + xmin + " " + ymin + " " + xmax + " " + ymax + '\n' + ax + " " + bx + " " + ay + " " + by;
	}
}