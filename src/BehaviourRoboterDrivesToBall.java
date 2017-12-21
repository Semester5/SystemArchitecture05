import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterDrivesToBall extends BaseController implements IBehaviour {

    private static int RED_TOL = 255;
    private static int GREEN_TOL = 80;
    private static int BLUE_TOL = 80;

    public BehaviourRoboterDrivesToBall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        int[] image = camera.getImage();

        int redCameraValue = 0;
        int greedCameraValue = 0;
        int blueCameraValue = 0;

        for (int x = camera.getWidth()/3; x < (camera.getWidth()/3)*2; x++) {

            for (int y = camera.getHeight()/3; y < camera.getHeight()/3*2; y++) {

                redCameraValue = Camera.imageGetRed(image, camera.getWidth(), x, y);
                greedCameraValue = Camera.imageGetGreen(image, camera.getWidth(), x, y);
                blueCameraValue = Camera.imageGetBlue(image, camera.getWidth(), x, y);

                //System.out.println("Check DriveToBall: RED: " + redCameraValue + " GREEN: " + greedCameraValue + " BLUE: " + blueCameraValue);

                if((redCameraValue < RED_TOL) && (blueCameraValue < BLUE_TOL) && (greedCameraValue < GREEN_TOL)) {
                    //System.out.println("Yeah, DriveToBall");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        //System.out.println("DriveToBall: driveForward()");
        return driveForward();
    }
}