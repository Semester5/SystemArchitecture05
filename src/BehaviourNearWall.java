import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourNearWall extends BaseController implements IBehaviour {

    public BehaviourNearWall() {
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