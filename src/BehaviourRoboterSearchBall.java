import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterSearchBall extends BaseController implements IBehaviour {

    public BehaviourRoboterSearchBall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        return true;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        return driveRight(0.333);
    }
}
