import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class PointVectorTest {
	Vector3D v1 = new Vector3D (1,0,0);
	Vector3D v2 = new Vector3D (0,1,0);
	Vector3D v3 = new Vector3D (0,0,1);
	
	Vector3D v4 = new Vector3D (1,1,1);
	Vector3D v5 = new Vector3D (1,1,-1);
	Vector3D v6 = new Vector3D (2,5,3);
	Vector3D v7 = new Vector3D (10,1,5);
	@Test
	public void DotProductTest() {
		assertEquals(v4.dotProduct(v5),1, 0.01);
		assertEquals(v6.dotProduct(v6),38, 0.01);
		assertEquals(v1.dotProduct(v7),10, 0.01);
	}
	@Test
	public void CrossProductTest() {
		Vector3D res1 = v1.crossProduct(v1);
		assertEquals(true,res1.x==0 &&res1.y ==0 && res1.z ==0);
		Vector3D res2 = v3.crossProduct(v1);
		assertEquals(true,res1.x==0 &&res1.y ==1 && res1.z ==0);
		Vector3D res3 = v3.crossProduct(v2);
		assertEquals(true,res1.x==-1 &&res1.y ==0 && res1.z ==0);
	}
}
