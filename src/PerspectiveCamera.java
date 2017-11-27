public class PerspectiveCamera extends Camera {
	private Point3D cop = new Point3D(0, 0, -4);
	// center of projection
	
	private Point3D vrp = new Point3D(0, 0, 0);
	// view reference point: the origin of camera coordinating system
	
	private Vector3D vpn = new Vector3D(0, 0, 1);
	// view plane normal (default axis n)
	
	private Vector3D vup = new Vector3D(0, 1, 0);
	//the view up vector (default axis v)
	private Vector3D u;
	// axis u
	
	private Matrix m = new Matrix(); // camera transformation matrix
	{m.setIdentity();}
	
	
	public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_) {
		super(xmin_, xmax_, ymin_, ymax_);
		
		u = vpn.crossProduct(vup);
		// Need something about view volume for clipping planes???
	}

	/**
	 * getVPN()
	 * Returns the vpn
	 * @return the View Plane Normal
	 */
	public Vector3D getVPN() {
		return vpn;
	}

	/**
	 * cameraTransform
	 * world coordinates to camera coordinates. This is a 3d-to-3d process.
	 * @param p: the point to transform
	 * @return : the transformed point
	 */
	protected Point3D cameraTransform(final Point3D p) {
		Vector3D v = vup;
		Vector3D n = vpn;
		
		//Translate VRP to origin and rotate to get the view ref coords
		Matrix m = new Matrix();
		m.m[0][0] = u.x;
		m.m[0][1] = u.y;
		m.m[0][2] = u.z;
		m.m[0][3] = -(u.x * vrp.x + u.y * vrp.y + u.z * vrp.z);
		
		m.m[1][0] = v.x;
		m.m[1][1] = v.y;
		m.m[1][2] = v.z;
		m.m[1][3] = -(v.x * vrp.x + v.y * vrp.y + v.z * vrp.z);
	
		m.m[2][0] = n.x;
		m.m[2][1] = n.y;
		m.m[2][2] = n.z;
		m.m[2][3] = -(n.x * vrp.x + n.y * vrp.y + n.z * vrp.z);
	
		m.m[3][0] = 0;
		m.m[3][1] = 0;
		m.m[3][2] = 0;
		m.m[3][3] = 1;
		
		return p.transform(m);
	}
	
	/**
	 * projectionTransform
	 * vertices are mapped to points on the view plane This is a 3d-to-2d process.
	 * @return the transformed point p
	 */
	protected Point3D projectionTransform(final Point3D p) {
		Matrix t = new Matrix();
		t.m[0][0] = 1;
		t.m[0][1] = 0;
		t.m[0][2] = 0;
		t.m[0][3] = 0;
		
		t.m[1][0] = 0;
		t.m[1][1] = 1;
		t.m[1][2] = 0;
		t.m[1][3] = 0;
	
		t.m[2][0] = 0;
		t.m[2][1] = 0;
		t.m[2][2] = 0; //might need to be 1
		t.m[2][3] = 0; 
	
		t.m[3][0] = 0;
		t.m[3][1] = 0;
		t.m[3][2] = 1/cop.distance(vrp); 
		t.m[3][3] = 1; 

		return p.transform(t);
	} 

	/**
	 * setupCOP
	 * Sets up the center of projection
	 * @param cop_: the point to become the COP
	 */
	public void setupCOP(Point3D cop_) {
		cop = cop_;
	}
	
	/**
	 * setupUVN
	 * Sets up the u,v.n coordinate system
	 * @param vrp_: the view reference point
	 * @param vpn_: the view plane normal
	 * @param vup_: the view up vector
	 */
	public void setupUVN(Point3D vrp_, Vector3D vpn_, Vector3D vup_) {
		vpn_.normalize();
		vpn = vpn_;
		vup= vup_;
		vup.normalize();
		vrp = vrp_;
		
		u = vup_.crossProduct(vpn_);
		u.normalize();
		
	}

	

}
