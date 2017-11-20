import static java.lang.Math.*;
public class Vector3D implements Cloneable {
	public double x, y, z, w; //the coordinates of the vector
	
	//may need to do something about the w and homogeneous coords
	public Vector3D(double X, double Y, double Z) {
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.w = 1;
	}
/**
 * toString
 * Purpose: Create a string for testing
 * Produces: a String of the form x y z
 */
	public String toString() {
		return x + " " + y + " " + z;
	}
	/**
	 * clone
	 * Purpose: Create a clone of the vector
	 * Produces: a new vector with the same coordinates
	 */
	public Vector3D clone() throws CloneNotSupportedException {
		return new Vector3D(x, y, z);
	}
	/**
	 * L2norm
	 * Purpose: Find the |v| where v is this
	 * @return: a double of the value of |this|
	 */
	public double L2norm() {
		return sqrt((x * x) + (y * y) + (z * z));
	}
/**
 * Procedure: dotProduct
 * Purpose: calculate the dot product of this and the vector v
 * @param v: a Vector3D
 * @return a double of the dot product
 */
	public double dotProduct(Vector3D v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
/**
 * crossProduct
 * Purpose: calculate the cross product of this and vector v
 * @param v: a Vector3D
 * @return: a new vector of the cross product
 */
	public Vector3D crossProduct(Vector3D v) {
		return new Vector3D(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
	}

	/**
	 * normalize
	 * Purpose: Normalize the vector
	 * Returns nothing
	 */
	public void normalize() {
		double len = sqrt (pow(this.x,2)+pow(this.y, 2)+pow(this.z, 2));
		this.x = this.x / len;
		this.y = this.y / len;
		this.z = this.z / len;
	}
	/**
	 * transform
	 * Purpose: transform a vector by multiplying it by a given matrix
	 * @param m: The transformation matrix
	 * @return a new vector with the coordinates of the transformed vector
	 */
	public Vector3D transform(Matrix m) {
		double xnew = x*m.m[0][0]+y*m.m[0][1]+z*m.m[0][2]+w*m.m[0][3]; 
		double ynew = x*m.m[1][0]+y*m.m[1][1]+z*m.m[1][2]+w*m.m[1][3];
		double znew = x*m.m[2][0]+y*m.m[2][1]+z*m.m[2][2]+w*m.m[2][3];
		
		return new Vector3D (xnew,ynew,znew);
	}
}
