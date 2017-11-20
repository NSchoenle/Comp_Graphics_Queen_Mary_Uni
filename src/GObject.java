import java.util.Scanner;
import java.io.*;
import java.awt.Color;

public class GObject {
	public Point3D[] vertex; //the vertexes of the object
	public Face[] face; //the faces of the object

	public GObject(Point3D[] v, Face[] f) {
		vertex = v;
		face = f;
	}
	
	public GObject(String fileName) throws FileNotFoundException {
		Scanner in = new Scanner(new File(fileName));
		//if the file is empty return
		if (in.hasNext() == false) {
			in.close();
			return;
			}
		//create a new array of Point3D of the size specified
		vertex = new Point3D[in.nextInt()];
		for (int i = 0; i < vertex.length; i++) {
			//have to cast because files are given as doubles even though requires ints
			vertex[i] = new Point3D((int)in.nextDouble(), (int)in.nextDouble(), (int) in.nextDouble());
		}
		face = new Face[in.nextInt()];
		for (int j = 0; j < face.length; j++) {
			int[] verts = new int[in.nextInt()];
			for (int r = 0; r < verts.length; r++) {
				verts[r] = in.nextInt();
			}
			float r = in.nextFloat();
			float g = in.nextFloat();
			float b = in.nextFloat();
			Color c = new Color(r, g, b);

			face[j] = new Face(verts, c);
		}
		in.close();
	}
	/**
	 * toString
	 * Produces: a String representation of the GObject
	 */
	public String toString() {
		String ret = "" + vertex.length + '\n'; //number of verts
		for (int i = 0; i < vertex.length; i++) { //verts listed one per line
			ret += vertex[i].toString() + '\n';
		}
		//ret += '\n';
		ret += face.length; //number of faces
		ret+= '\n';
		for (int j = 0; j < face.length; j++) { //faces as strings 1 per line
			ret += face[j].toString() + '\n';
		}
		return ret;
	}
	//Not 100% sure on this one
	/**
	 * transform
	 * Purpose: Transform an object by a matrix
	 * @param m : the transformation matrix
	 */
	public void transform (Matrix m) {
		for (int i =0;i<vertex.length; i++) {
			vertex[i]=vertex[i].transform(m);
		}
	}
	/*Testing*/
	public static void main (String[] args) throws FileNotFoundException {
		/*
		File f = new File("src/cube.dat");
		
		System.out.println(f.getName());
		System.out.println(f.getAbsolutePath());
		Scanner in = new Scanner(f);

		Point3D[] vertex = new Point3D[in.nextInt()];
		for (int i = 0; i < vertex.length; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int z = in.nextInt();
			vertex[i] = new Point3D(x,y,z);
		}
		Face[] face = new Face[in.nextInt()];
		for (int j = 0; j < face.length; j++) {
			int[] verts = new int[in.nextInt()];
			for (int r = 0; r < verts.length; r++) {
				verts[r] = in.nextInt();
			}
			float r = in.nextFloat();
			float g = in.nextFloat();
			float b = in.nextFloat();
			Color c = new Color(r, g, b);

			face[j] = new Face(verts, c);
		}
		GObject obj1 = new GObject (vertex, face);
		in.close();*/
		GObject obj1 = new GObject ("src/cube.dat");
		System.out.println(obj1.toString());
		GObject obj2 = new GObject ("src/pyramid.dat");
		System.out.println(obj2.toString());
	}
	
}