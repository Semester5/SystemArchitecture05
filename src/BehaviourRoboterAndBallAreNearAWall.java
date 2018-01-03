import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterAndBallAreNearAWall extends BaseController implements IBehaviour {
    private final int MIN_DISTANCE_VALUE_TO_FIND_BALL = 1000;

    public BehaviourRoboterAndBallAreNearAWall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        boolean checkDistanceSensors = false;
        for(int i = 0; i < distanceSensors.length; i++) {
            if(distanceSensors[i].getValue() > MIN_DISTANCE_VALUE_TO_FIND_BALL) {
                checkDistanceSensors = true;
                break;
            }
        }

        if((accelerometer.getValues()[0] < -1.0 || accelerometer.getValues()[1] < -1.0)) {
            if(checkDistanceSensors) {
                System.out.printf("RoboterAndBallAreNearAWall is activated: %.5f / %.5f / %.5f", accelerometer.getValues()[0],  accelerometer.getValues()[1],  accelerometer.getValues()[2]);
                return true;
            } else {
                System.out.printf("RoboterAndBallAreNearAWall is NOT activated: %.5f / %.5f / %.5f", accelerometer.getValues()[0],  accelerometer.getValues()[1],  accelerometer.getValues()[2]);
            }
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        return driveRight();
    }
}