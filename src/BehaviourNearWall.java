import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourNearWall extends BaseController implements IBehaviour {

    public BehaviourNearWall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        System.out.println("Check IsBallNearWall: " + accelerometer.getValues()[0]);
        System.out.println("Check IsBallNearWall: " + accelerometer.getValues()[1]);
        System.out.println("Check IsBallNearWall: " + accelerometer.getValues()[2]);

        if(accelerometer.getValues()[0] < -1.0 || accelerometer.getValues()[1] < -1.0) {
            return true;
        }
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        return driveLeft();
    }
}