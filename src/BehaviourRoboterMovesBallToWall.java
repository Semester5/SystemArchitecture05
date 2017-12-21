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
            //System.out.println("Check MoveBallToWall: DistanceSensorValue " + i + " = " + distanceSensors[i].getValue());
            if(distanceSensors[i].getValue() > MIN_DISTANCE_VALUE_TO_FIND_BALL) {
                //System.out.println("Yeah, MoveBallToWall");
                return true;
            }
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        if (distanceSensors[D_FRONT_RIGHT].getValue() > distanceSensors[D_FRONT_LEFT].getValue()) {
            //System.out.println("MoveBallToWall: driveRight: RIGHTSENSOR: " + distanceSensors[D_FRONT_RIGHT].getValue() + "  LEFTSENSOR: " + distanceSensors[D_FRONT_LEFT].getValue());
            return driveRight();
        } else if (distanceSensors[D_FRONT_RIGHT].getValue() < distanceSensors[D_FRONT_LEFT].getValue()) {
            //System.out.println("MoveBallToWall: driveLeft: RIGHTSENSOR: " + distanceSensors[D_FRONT_RIGHT].getValue() + "  LEFTSENSOR: " + distanceSensors[D_FRONT_LEFT].getValue());
            return driveLeft();
        }
        //System.out.println("MoveBallToWall: DRIVEFORWARD: RIGHTSENSOR: " + distanceSensors[D_FRONT_RIGHT].getValue() + "  LEFTSENSOR: " + distanceSensors[D_FRONT_LEFT].getValue());
        return driveForward();
    }
}