import com.cyberbotics.webots.controller.*;

public class RobotController extends DifferentialWheels {

    private static final int TIME_STEP = 15;

    private Camera camera;
    private Accelerometer accelerometer;
    private DistanceSensor[] distanceSensors;

    public RobotController() {
        super();

        camera = getCamera("camera");
        accelerometer = getAccelerometer("accelerometer");
        distanceSensors = new DistanceSensor[] { getDistanceSensor("ps0"), getDistanceSensor("ps7") };

        initSensors();
    }

    public void run() {
        double[] newSpeed;

        BehaviourNearWall nearWall = new BehaviourNearWall();
        BehaviourMoveBallToWall moveToWall = new BehaviourMoveBallToWall();
        BehaviourFindBall findBall = new BehaviourFindBall();

        while (step(TIME_STEP) != -1) {

            if(nearWall.isActivatable(camera, accelerometer, distanceSensors)) {
                newSpeed = nearWall.calculateSpeed();
            } else if(moveToWall.isActivatable(camera, accelerometer, distanceSensors)) {
                newSpeed = moveToWall.calculateSpeed();
            } else {
                newSpeed = findBall.calculateSpeed();
            }

            setSpeed(newSpeed[0], newSpeed[1]);
        }
    }

    private void initSensors() {
        camera.enable(10);
        accelerometer.enable(10);

        for (int i = 0; i < distanceSensors.length; i++) {
            distanceSensors[i].enable(10);
        }
    }

    public static void main(String[] args) {
        RobotController robotController = new RobotController();
        robotController.run();
    }
}
