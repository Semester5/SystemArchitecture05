import com.cyberbotics.webots.controller.*;

public class RobotController extends DifferentialWheels {

    private static final int TIME_STEP = 15;

    private Camera camera;
    private Accelerometer accelerometer;
    private DistanceSensor[] distanceSensors;
    private LightSensor[] lightSensors;

    public RobotController() {
        super();

        camera = getCamera("camera");
        accelerometer = getAccelerometer("accelerometer");
        distanceSensors = new DistanceSensor[] { getDistanceSensor("ps0"), getDistanceSensor("ps1"),getDistanceSensor("ps6"),getDistanceSensor("ps7") };
        lightSensors = new LightSensor[] { getLightSensor("ls0") };

        initSensors();
    }

    public void run() {
        double[] newSpeed;

        BehaviourNearWall isBallNearAWall = new BehaviourNearWall();
        BehaviourMoveBallToWall moveBallToWall = new BehaviourMoveBallToWall();
        BehaviourDriveToBall driveToBall = new BehaviourDriveToBall();
        BehaviourFindBall findBall = new BehaviourFindBall();

        while (step(TIME_STEP) != -1) {

            if(isBallNearAWall.isActivatable(camera, accelerometer, distanceSensors)) {
                newSpeed = isBallNearAWall.calculateSpeed(camera, accelerometer, distanceSensors);
            } else if(moveBallToWall.isActivatable(camera, accelerometer, distanceSensors)) {
                newSpeed = moveBallToWall.calculateSpeed(camera, accelerometer, distanceSensors);
            } else if(driveToBall.isActivatable(camera, accelerometer, distanceSensors)) {
                newSpeed = driveToBall.calculateSpeed(camera, accelerometer, distanceSensors);
            } else {
                newSpeed = findBall.calculateSpeed(camera, accelerometer, distanceSensors);
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

        for (int i = 0; i < lightSensors.length; i++) {
            lightSensors[i].enable(10);
        }
    }

    public static void main(String[] args) {
        RobotController robotController = new RobotController();
        robotController.run();
    }
}
