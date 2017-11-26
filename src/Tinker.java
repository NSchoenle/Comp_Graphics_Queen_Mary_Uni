import static java.lang.Math.PI;

public class Tinker {
	public static void main (String[] args){
		Matrix mX = new Matrix(), 
				mY = new Matrix(), 
				mZ = new Matrix(),
				mA = new Matrix();
		mX.setRotationX(-PI / 11);
		mY.setRotationY(PI / 13);
		mZ.setRotationZ(PI / 17);
		mA = mZ.multiply(mY.multiply(mX));
		for (int i = 0; i<4; i++){
			for (int j = 0; j<4; j++){
				System.out.print(mA.m[i][j] + " ");
			}
			System.out.print('\n');
		}
		Vector3D n= new Vector3D(0,0,1);
		n.normalize();
		Point3D VRP = new Point3D(0,0,0);
		n.normalize();
		Vector3D u = n.crossProduct(new Vector3D (0,1,0));
		Vector3D v = n.crossProduct(u);
		
		Matrix mat = new Matrix();
		mat.m[0][0] = u.x;
		mat.m[0][1] = u.y;
		mat.m[0][2] = u.z;
		mat.m[0][3] = -(u.x * VRP.x + u.y * VRP.y + u.z * VRP.z);
		
		mat.m[1][0] = v.x;
		mat.m[1][1] = v.y;
		mat.m[1][2] = v.z;
		mat.m[1][3] = -(v.x * VRP.x + v.y * VRP.y + v.z * VRP.z);
	
		mat.m[2][0] = n.x;
		mat.m[2][1] = n.y;
		mat.m[2][2] = n.z;
		mat.m[2][3] = -(n.x * VRP.x + n.y * VRP.y + n.z * VRP.z);
	
		mat.m[3][0] = 0;
		mat.m[3][1] = 0;
		mat.m[3][2] = 0;
		mat.m[3][3] = 1;
		System.out.println("");
		for (int i = 0; i<4; i++){
			for (int j = 0; j<4; j++){
				System.out.print(mat.m[i][j] + " ");
			}
			System.out.print('\n');
		}
	}
}
