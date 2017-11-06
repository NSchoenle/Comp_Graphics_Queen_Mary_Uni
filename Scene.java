import java.awt.*;
import java.io.FileNotFoundException;

public class Scene {
	private GObject[] obj; //the objects in the scene

	public Scene(String[] fileName) throws FileNotFoundException {
		for (int i = 0; i < fileName.length; i++) {
			obj[i] = new GObject(fileName[i]);
		}
	}

	/**
	 * transform Purpose: transform a scene of GObjects by a matrix
	 * 
	 * @param m:the transformation matrix
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
	 * @param c : the camera used to draw the scene???
	 * @param g : the object to be drawn???
	 */
	public void draw(Camera c, Graphics g) {
		// STUB
		/* Back face elimination is required before drawing polygons */
	}
}