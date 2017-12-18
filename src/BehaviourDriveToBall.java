import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourDriveToBall extends BaseController implements IBehaviour {

    private static int RED_TOL = 140;
    private static int GREEN_TOL = 80;
    private static int BLUE_TOL = 80;

    public BehaviourDriveToBall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        int[] image = camera.getImage();

        int red = Camera.imageGetRed(image, camera.getWidth(), camera.getWidth() / 2, camera.getHeight() / 2);
        int blue = Camera.imageGetBlue(image, camera.getWidth(), camera.getWidth() / 2, camera.getHeight() / 2);
        int green = Camera.imageGetGreen(image, camera.getWidth(), camera.getWidth() / 2, camera.getHeight() / 2);

        System.out.println("Drive to ball: RED: " + red + " GREEN: " + green + " BLUE: " + blue);

        if((red < RED_TOL) && (blue < BLUE_TOL) && (green < GREEN_TOL)) {
            return true;
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        return driveForward();
    }
}