import java.util.Scanner;
import java.io.*;
import java.awt.Color;

public class GObject {
	public Point3D[] vertex;
	public Face[] face;

	public GObject(Point3D[] v, Face[] f) {
		vertex = v;
		face = f;
	}

	public GObject(String fileName) throws FileNotFoundException {
		Scanner in = new Scanner(new File(fileName));
		vertex = new Point3D[in.nextInt()];
		for (int i = 0; i < vertex.length; i++) {
			vertex[i] = new Point3D(in.nextInt(), in.nextInt(), in.nextInt());
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

	}

	public String toString() {
		String ret = "" + vertex.length + '\n';
		for (int i = 0; i < vertex.length; i++) {
			ret += vertex[i].toString() + '\n';
		}
		ret += '\n' + '\n';
		ret += face.length + '\n';
		for (int j = 0; j < face.length; j++) {
			ret += face[j].toString() + '\n';
		}
		return ret;
	}
	
	public static void main (String[] args) throws FileNotFoundException {
		GObject obj1 = new GObject ("cube.dat");
		System.out.println(obj1.toString());
	}
}