import java.awt.*;
import java.io.FileNotFoundException;

public class Scene {
	private GObject[] obj; // the objects in the scene

	/**
	 * Scene const. 
	 * Purpose: Creates a new scene from the objects in the given file array
	 * 
	 * @param fileName: an array of Strings (fileNames)
	 * @throws FileNotFoundException
	 */
	public Scene(String[] fileName) throws FileNotFoundException {
		obj = new GObject[fileName.length];
		for (int i = 0; i < fileName.length; i++) {
			obj[i] = new GObject(fileName[i]);
		}
	}

	/**
	 * transform Purpose: transform a scene of GObjects by a matrix
	 * 
	 * @param m: thetransformation matrix
	 */
	public void transform(Matrix m) {
		for (GObject o : obj) {
			o.transform(m);
		}
	}

	/**
	 * toString produces a String representation of the scene
	 */
	public String toString() {
		String ret = "";
		for (GObject o : obj) {
			ret += o.toString() + '\n';
		}
		return ret;
	}

	/**
	 * draw Purpose: draw the scene using back face elimination
	 * 
	 * @param c  : the camera used to draw the scene???
	 * @param g  : the graphics object used to draw the scene
	 */
	public void draw(Camera c, Graphics g) { //FIX MEEEEEEEE
		/* Back face elimination is required before drawing polygons */
		for (GObject object : obj) { // for all the faces in the object check if they are front facing by using 3
										// verts and the VPN
			for (Face f : object.face) {
				Point3D[] verts = new Point3D[f.index.length];
				for (int i = 0; i < f.index.length; i++) {
					verts[i] = object.vertex[f.index[i]];
					System.out.println(verts[i]);
				}
				System.out.println("");
				if (Point3D.isFrontFace(verts[0], verts[1], verts[2], c.getVPN())) {
					for (Point3D v : verts) {
						v = c.project(v); // convert the vertices to projection coords
						System.out.println(v);
					}
					int[] xs = new int[verts.length]; // x coords of polygon verts
					int[] ys = new int[verts.length]; // y coords of polygon verts
					for (int j = 0; j > verts.length; j++) {
						xs[j] = (int) verts[j].x;
						ys[j] = (int) verts[j].y;
					}
					System.out.println("Time to draw the polygon " + f.toString());
					g.fillPolygon(xs, ys, verts.length); // figure out how to draw the polygon!!!!!!!!!!!!!!!
					System.out.println( "Polygon drawn");
				}
			}
		}
	}
}