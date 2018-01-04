import com.cyberbotics.webots.controller.Camera;

public abstract class BaseController {

    private static int RED_TOL = 10;
    private static int GREEN_TOL = 10;
    private static int BLUE_TOL = 10;

    protected final int MIN_SPEED = 0;
    protected final int MAX_SPEED = 1000;

    protected double[] speed;

    public BaseController() {
        this.speed = new double[2];
    }

    protected double[] standStill() {
        speed[0] = 0;
        speed[1] = 0;
        return speed;
    }

    protected double[] rotate() {
        speed[0] = MAX_SPEED;
        speed[1] = -MAX_SPEED;
        return speed;
    }

    protected double[] driveRight(double rotationFactor) {
        speed[0] = MAX_SPEED;
        speed[1] = MAX_SPEED * (1.0 - rotationFactor);
        return speed;
    }

    protected double[] driveLeft(double rotationFactor) {
        speed[0] = MAX_SPEED * (1.0 - rotationFactor);
        speed[1] = MAX_SPEED;
        return speed;
    }

    protected double[] driveForward() {
        speed[0] = MAX_SPEED;
        speed[1] = MAX_SPEED;
        return speed;
    }

    protected double[] driveBack(double tilt) {
        speed[0] = -MAX_SPEED * (1.0 - tilt);
        speed[1] = -MAX_SPEED;
        return speed;
    }

    protected boolean detectBall(int[] image, int cameraWidth, int cameraHeight, int x) {
        for (int y = cameraHeight/3; y < cameraHeight/3*2; y++) {

            int redCameraValue = Camera.imageGetRed(image, cameraWidth, x, y);
            int greedCameraValue = Camera.imageGetGreen(image, cameraWidth, x, y);
            int blueCameraValue = Camera.imageGetBlue(image, cameraWidth, x, y);

            if((redCameraValue > RED_TOL) && (blueCameraValue < BLUE_TOL) && (greedCameraValue < GREEN_TOL)) {
                return true;
            }
        }
        return false;
    }
}