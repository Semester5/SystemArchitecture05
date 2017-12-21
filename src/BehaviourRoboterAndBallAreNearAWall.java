import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterAndBallAreNearAWall extends BaseController implements IBehaviour {
    private final int MAX_STEP_COUNTER = 100;

    private int stepCounter = 0;

    public BehaviourRoboterAndBallAreNearAWall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {

        System.out.println("Check IsBallNearWall: " + accelerometer.getValues()[0]);
        System.out.println("Check IsBallNearWall: " + accelerometer.getValues()[1]);
        System.out.println("Check IsBallNearWall: " + accelerometer.getValues()[2]);

        if(stepCounter == MAX_STEP_COUNTER) {
            stepCounter = 0;
            return false;
        }

        if((accelerometer.getValues()[0] < -3.0 || accelerometer.getValues()[1] < -3.0) || (stepCounter > 0 && stepCounter < MAX_STEP_COUNTER)) {
            System.out.println("Yeah, Ball is near the wall");
            return true;
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        stepCounter++;
        if(stepCounter < 90) {
            return driveBack();
        }
        if(stepCounter == 100) {
            stepCounter = 0;
        }
        return driveLeft();
    }
}