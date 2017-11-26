public class PerspectiveCamera extends Camera {
	private Point3D cop = new Point3D(0, 0, -4);
	// center of projection
	
	private Point3D vrp = new Point3D(0, 0, 0);
	// view reference point: the origin of camera coordinating system
	
	private Vector3D vpn = new Vector3D(0, 0, 1);
	// view plane normal (axis n)
	
	private Vector3D vup = new Vector3D(0, 1, 0);
	//the view up vector (axis v)
	private Vector3D u, dop;
	// axis u and direction of projection
	
	private Matrix m = new Matrix(); // camera transformation matrix
	{m.setIdentity();}
	
	
	public PerspectiveCamera(double xmin_, double xmax_, double ymin_, double ymax_) {
		super(xmin_, xmax_, ymin_, ymax_);
		dop = cop.vector(vrp);
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

		//translate so COP is at origin
		Matrix tCOP = new Matrix();
		tCOP.setTranslation(cop.x,cop.y, cop.z);
		
		//Shear so center line of view vol is z axis
		Matrix sh = new Matrix();

		sh.m[0][2] = dop.x/dop.z;
		sh.m[1][2] = dop.y/dop.z;
		// Scale to canonical perspective view vol
		/*Point3D _vrp = new Point3D(vrp.x,vrp.y,vrp.z);
		_vrp.transform(tCOP).transform(sh);
		Matrix s= new Matrix();
		s.m[0][0] = (2*_vrp.z)/();
		s.m[1][1] = ;
		s.m[2][2] = ;
		*/
		return p.transform(sh.multiply(tCOP.multiply(m)));
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
		t.m[2][2] = 1; //might need to be 1
		t.m[2][3] = 0; 
	
		t.m[3][0] = 0;
		t.m[3][1] = 0;
		t.m[3][2] = 1/cop.distance(vrp); 
		//t.m[3][2] = -1; 
		//unsure if correct
		t.m[3][3] = 0; 

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
		vup = vup_;
		vrp = vrp_;
		
		u = vpn_.crossProduct(vup_);
		u.normalize();
	}

	

}
