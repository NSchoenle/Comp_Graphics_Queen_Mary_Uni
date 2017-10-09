import java.awt.Color;

public class Face {
	public int[] index;
	public Color color;

	public Face(int[] i, Color c) {
		index = i;
		color = c;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i<index.length; i++) {
			ret+= index[i] + " ";
		}
		ret += '\n' +color.getRed() +" "+ color.getGreen() + " " + color.getBlue();
		return ret;
	}
}
