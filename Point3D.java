import static java.lang.Math.*;

public class Point3D {
	public double x, y, z, w;

	public Point3D(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.w = 1;
	}

	//Find the distance between the point and the origin
	public double distance(Point3D p) {
		return sqrt(pow((p.x - x), 2) + pow((p.y - y), 2) + pow((p.z - z), 2));
	}

	//Transform the point based on the matrix
	public Point3D transform (Matrix m) {
		double xnew = 0;
		double ynew = 0;
		double znew = 0;
		
		for (int i = 0; i<4; i++) {
			xnew+= m.m[0][i];
			ynew+= m.m[1][i];
			znew += m.m[2][i];
		}
		return new Point3D(xnew,ynew,znew);
	}
	
	//ORIENTATION TESTING
	
	//Return the vector between this point and p
	public Vector3D vector(Point3D p){
		return new Vector3D (p.x-x, p.y - y, p.z-z);
	}
	public static Vector3D faceNormal(Point3D p1, Point3D p2, Point3D p3){
		Vector3D v1 = new Vector3D(p2.x-p1.x,p2.y-p1.y, p2.z-p1.z);
		Vector3D v2 = new Vector3D(p3.x-p1.x,p3.y-p1.y, p3.z-p1.z);
		return v1.crossProduct(v2);
	}
	public static boolean isFrontFace(Point3D p1, Point3D p2, Point3D p3, Vector3D vpn){
		return faceNormal(p1,p2,p3).dotProduct(vpn)>0; //if n dot vpn >0 then front facing
	}
	
	//Make the point look nice for printing
	public String toString(){
                return this.x+" "+this.y+" "+ this.z;
        }
}