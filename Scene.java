import java.awt.*;
import java.io.FileNotFoundException;

public class Scene
{
  private GObject[] obj;

  public Scene(String[] fileName) throws FileNotFoundException{
	  for (int i = 0; i<fileName.length; i++) {
		  obj[i] = new GObject(fileName[i]);
	  }
  }

  public void transform(Matrix m){
	  for (GObject o:obj) {
		  o.transform(m);
	  }
  }

  //wait until next lab for Camera
  //public void draw(Camera c, Graphics g){}

  /* Make it look nice to save your debugging time! */
  public String toString(){
	  String ret = "";
	  for (GObject o: obj) {
		  ret+= o.toString() + '\n';
	  }
	  return ret;
  }
}