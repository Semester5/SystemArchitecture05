import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourFindBall extends BaseController implements IBehaviour {

    public BehaviourFindBall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] sensors) {
        return false;
    }

    @Override
    public double[] calculateSpeed() {
        return driveLeft();
    }
}
