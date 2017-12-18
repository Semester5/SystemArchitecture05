import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public interface IBehaviour {
    boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors);
    double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors);
}
