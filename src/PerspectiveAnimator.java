public class PerspectiveAnimator extends ParallelAnimator {
	protected void setupCamera() {
		camera = new PerspectiveCamera(-5, 5, -5, 5);
		((PerspectiveCamera) camera).setupCOP(new Point3D(0, 0, 3));
	}

	public static void main(String[] args) {
		new PerspectiveAnimator().loop();
	}
}