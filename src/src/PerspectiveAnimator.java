public class PerspectiveAnimator extends ParallelAnimator {
	protected void setupCamera() {
		camera = new PerspectiveCamera(-5, 5, -5, 5);
		//((PerspectiveCamera) camera).setupCOP(new Point3D(0, 0, 3));
		((PerspectiveCamera) camera).setupCOP(new Point3D(350, 56,10));
	}
	public static void main(String[] args) {
		new PerspectiveAnimator().loop();
	}
}