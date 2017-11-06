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
		if (in.hasNext() == false) {
			in.close();
			return;
			}
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
		in.close();
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
	//Not 100% sure on this one
	public void transform (Matrix m) {
		for (Point3D s : vertex) {
			s.transform(m);
		}
	}
	/*Testing
	public static void main (String[] args) throws FileNotFoundException {
		File f = new File("shapes/cube.dat");
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
		in.close();
		System.out.println(obj1.toString());
	}
	*/
}