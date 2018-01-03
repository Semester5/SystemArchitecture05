import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterDrivesToBall extends BaseController implements IBehaviour {

    private static int RED_TOL = 10;
    private static int GREEN_TOL = 10;
    private static int BLUE_TOL = 10;

    public BehaviourRoboterDrivesToBall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        int[] image = camera.getImage();
        int cameraWidth = camera.getWidth();
        int cameraHeight = camera.getHeight();

        /* Print Camerapixels:
         for (int y = 0; y < cameraHeight; y++) {
            for (int x = 0; x < cameraWidth; x++) {

                int redCameraValue = Camera.imageGetRed(image, cameraWidth, x, y);
                int greedCameraValue = Camera.imageGetGreen(image, cameraWidth, x, y);
                int blueCameraValue = Camera.imageGetBlue(image, cameraWidth, x, y);

                System.out.printf("%03d/%03d/%03d - ", redCameraValue, greedCameraValue, blueCameraValue);
            }
            System.out.println("");
        }*/

        //search ball from the left side
        Integer xLeft = null;
        for (int x = 0; x <= cameraWidth/2; x++) {
            boolean ballDetected = detectBall(image, cameraWidth, cameraHeight, x);
            if(ballDetected && xLeft == null) {
                xLeft = x;
            } else if(!ballDetected && xLeft != null) {
                xLeft = null;
            }
        }

        if(xLeft == null) {
            //ball is not in the center in front of the robi
            return false;
        }

        //search ball from the right side
        Integer xRight = null;
        for (int x = cameraWidth; x >= cameraWidth/2; x--) {
            boolean ballDetected = detectBall(image, cameraWidth, cameraHeight, x);
            if(ballDetected && xRight == null) {
                xRight = cameraWidth - x;
            } else if(!ballDetected && xRight != null) {
                xRight = null;
            }
        }

        if(xRight == null) {
            //ball is not in the center in front of the robi
            return false;
        }

        int diff = Math.abs(xLeft - xRight);
        return diff <= 5;
    }

    private boolean detectBall(int[] image, int cameraWidth, int cameraHeight, int x) {
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

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        return driveForward();
    }
}