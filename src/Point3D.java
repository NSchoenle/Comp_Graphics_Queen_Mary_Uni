import static java.lang.Math.*;

public class Point3D {
	public double x, y, z, w; //the coordinates of the point

	public Point3D(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.w = 1;
	}

	/**
	 * distance
	 * Purpose: Find the distance between two points
	 * @param p: the second point
	 * @return: the distance between the points (a double)
	 */
	public double distance(Point3D p) {
		return sqrt(pow((p.x - x), 2) + pow((p.y - y), 2) + pow((p.z - z), 2));
	}

	/**
	 * transform
	 * Purpose: Transform the point based on the matrix
	 * @param m: the transformation matrix
	 * @return: a new point at the transformed coordinates
	 */
	public Point3D transform (Matrix m) {
		double xnew = m.m[0][0]*x+m.m[0][1]*y+m.m[0][2]*z+m.m[0][3]*1;
		double ynew = m.m[1][0]*x+m.m[1][1]*y+m.m[1][2]*z+m.m[1][3]*1;
		double znew = m.m[2][0]*x+m.m[2][1]*y+m.m[2][2]*z+m.m[2][3]*1;
		double wnew = m.m[3][0]*x+m.m[3][1]*y+m.m[3][2]*z+m.m[3][3]*1;
		return new Point3D(xnew/wnew,ynew/wnew,znew/wnew);
	}
	
	//ORIENTATION TESTING
	
	/**
	 * vector
	 * Purpose: find the vector between this point and p
	 * @param p : the second point
	 * @return the vector between this point and p
	 */
	public Vector3D vector(Point3D p){
		return new Vector3D (p.x-x, p.y - y, p.z-z);
	}
	/**
	 * faceNormal
	 * Purpose: find the normal vector of the face defined by 3 points (p1, p2, p3)
	 * @param p1 : point 1
	 * @param p2 : point 2
	 * @param p3 : point 3
	 * @return : the normal vector of the face
	 */
	public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3){
		Vector3D v1 = new Vector3D(p2.x-p1.x,p2.y-p1.y, p2.z-p1.z);
		Vector3D v2 = new Vector3D(p3.x-p1.x,p3.y-p1.y, p3.z-p1.z);
		return v1.crossProduct(v2);
	}
	/**
	 * isFrontFace
	 * Purpose: determine if the face defined by 3 points (p1, p2, p3) is facing the viewer
	 * @param p1 : point 1
	 * @param p2 : point 2
	 * @param p3 : point 3
	 * @param vpn : the view-plane normal
	 * @return: if the normal of the face dotProduct with vpn >0
	 * 			then front facing (return true)
	 * 			else return false
	 */
	public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn){
		return faceNormal(p1,p2,p3).dotProduct(vpn)>0; 
	}
	
	/**
	 * toString
	 * Produce a string representation of the point
	 */
	public String toString(){
                return this.x+" "+this.y+" "+ this.z;
        }
}