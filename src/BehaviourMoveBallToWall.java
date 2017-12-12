import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourMoveBallToWall extends BaseController implements IBehaviour{

    private static int RED_TOL = 140;
    private static int GREEN_TOL = 80;
    private static int BLUE_TOL = 80;

    public BehaviourMoveBallToWall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] sensors) {
        int[] image = camera.getImage();

        int red = Camera.imageGetRed(image, camera.getWidth(), camera.getWidth() / 2, camera.getHeight() / 2);
        int blue = Camera.imageGetBlue(image, camera.getWidth(), camera.getWidth() / 2, camera.getHeight() / 2);
        int green = Camera.imageGetGreen(image, camera.getWidth(), camera.getWidth() / 2, camera.getHeight() / 2);

        if((red < RED_TOL) && (blue < BLUE_TOL) && (green < GREEN_TOL)) {
            return true;
        }
        return false;
    }

    @Override
    public double[] calculateSpeed() {
        return driveForward();
    }
}