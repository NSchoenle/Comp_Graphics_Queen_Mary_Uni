import static java.lang.Math.*;

public class Point3D {
	public double x, y, z;

	public Point3D(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
	}

	public double distance(Point3D p) {
		return sqrt(pow((p.x - x), 2) + pow((p.y - y), 2) + pow((p.z - z), 2));
	}

	public String toString(){
                return "Point: ("+this.x+","+y+", "+z +")";
        }
}