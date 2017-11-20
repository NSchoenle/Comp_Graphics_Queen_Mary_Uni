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
	 * transform 
	 * transform a scene of GObjects by a matrix
	 * 
	 * @param m: the transformation matrix
	 */
	public void transform(Matrix m) {
		for (int i = 0; i< obj.length; i++){
			obj[i].transform(m);
			
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
	public void draw(Camera c, Graphics g) { 
		/* Back face elimination is required before drawing polygons */
		for (GObject object : obj) { // for all the faces in the object check if they are front facing by using 3
										// verts and the VPN
			for (Face f : object.face) {
				Point3D[] verts = new Point3D[f.index.length];
				for (int i = 0; i < f.index.length; i++) {
					verts[i] = object.vertex[f.index[i]];
				}
			/*	//TESTING PRINTS
				if (verts.length == 0) {
					System.out.println("NONE");
				} else {
					System.out.println(verts.length);
					for (int x = 0; x < verts.length; x++) {
						System.out.println(verts[x].toString());
					}
				}
				//---------
				System.out.println("");
				*/
				if (Point3D.isFrontFace(verts[0], verts[1], verts[2], c.getVPN())) {
					for (int q = 0; q<verts.length;q++){
					//	System.out.println(v);
						verts[q] = c.project(verts[q]); // convert the vertices to projection coords
					//	System.out.println(v);
					}
					
					int[] xs = new int[verts.length]; // x coords of polygon verts
					int[] ys = new int[verts.length]; // y coords of polygon verts
					for (int j = 0; j < verts.length; j++) {
						xs[j] = (int) verts[j].x;
						ys[j] = (int) verts[j].y;
					}
					System.out.println("Verts to be drawn");
					for (int k = 0; k<verts.length;k++){
						System.out.println(xs[k]+ " " +ys[k]);
					}
					System.out.println("Time to draw the polygon " + f.toString());
					g.setColor(f.color);
					g.fillPolygon(xs, ys, verts.length); // figure out how to draw the polygon!!!!!!!!!!!!!!!
					System.out.println( "Polygon drawn");
				}
			}
		}
	}
}