import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Robot;

public class RobotController extends DifferentialWheels {

    private static final int TIME_STEP = 15;

    private Camera camera;
    private DistanceSensor[] distanceSensors;

    public RobotController() {
        super();

        camera = getCamera("camera");
        this.distanceSensors = new DistanceSensor[] {
                getDistanceSensor("ps0"),
                getDistanceSensor("ps7"),
        };

        initSensors();
    }

    public void run() {
        float[] newSpeed;

        BehaviourNearWall nearWall = new BehaviourNearWall();
        BehaviourMoveBallToWall moveToWall = new BehaviourMoveBallToWall();
        BehaviourFindBall findBall = new BehaviourFindBall();

        while (step(TIME_STEP) != -1) {

            if(nearWall.isActivatable()) {
                newSpeed = nearWall.calculateSpeed();
            } else if(moveToWall.isActivatable()) {
                newSpeed = moveToWall.calculateSpeed();
            } else {
                newSpeed = findBall.calculateSpeed();
            }

            setSpeed((double) newSpeed[0], (double) newSpeed[1]);
        }
    }

    private void initSensors() {
        camera.enable(10);

        for (int i = 0; i < distanceSensors.length; i++) {
            distanceSensors[i].enable(10);
        }
    }
}
