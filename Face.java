import java.awt.Color;

public class Face {
	public int[] index; //the indexes of the vertices that make up the face
	public Color color; // the face's color

	public Face(int[] i, Color c) {
		index = i;
		color = c;
	}
/**
 * toString
 * Purpose: produce a string representation of the face
 */
	public String toString() {
		String ret = "";
		for (int i = 0; i<index.length; i++) {
			ret+= index[i] + " ";
		}
		//ret += '\n';
		ret += color.getRed() +" "+ color.getGreen() + " " + color.getBlue();
		return ret;
	}
}
