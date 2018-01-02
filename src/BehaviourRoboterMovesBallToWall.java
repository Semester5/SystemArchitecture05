import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterMovesBallToWall extends BaseController implements IBehaviour{

    private final int MIN_DISTANCE_VALUE_TO_FIND_BALL = 1000;
    private final int D_FRONT_RIGHT = 0; //ps0
    private final int D_FRONT_LEFT = 3; //ps7

    public BehaviourRoboterMovesBallToWall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {

        for(int i = 0; i < distanceSensors.length; i++) {
            if(distanceSensors[i].getValue() > MIN_DISTANCE_VALUE_TO_FIND_BALL) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        if (distanceSensors[D_FRONT_RIGHT].getValue() > distanceSensors[D_FRONT_LEFT].getValue()) {
            return driveRight();
        } else if (distanceSensors[D_FRONT_RIGHT].getValue() < distanceSensors[D_FRONT_LEFT].getValue()) {
            return driveLeft();
        }
        return driveForward();
    }
}