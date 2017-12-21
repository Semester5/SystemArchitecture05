import com.cyberbotics.webots.controller.*;

public class RobotController extends DifferentialWheels {

    private static final int TIME_STEP = 15;

    private Camera camera;
    private Accelerometer accelerometer;
    private DistanceSensor[] distanceSensors;
    double[] speed = new double[2];


    public RobotController() {
        super();

        camera = getCamera("camera");
        accelerometer = getAccelerometer("accelerometer");
        distanceSensors = new DistanceSensor[] { getDistanceSensor("ps0"), getDistanceSensor("ps1"),getDistanceSensor("ps6"),getDistanceSensor("ps7") };

        initSensors();
    }

    public void run() {

        BehaviourRoboterAndBallAreNearAWall roboterAndBallAreNearAWall = new BehaviourRoboterAndBallAreNearAWall();
        BehaviourRoboterMovesBallToWall roboterMovesBallToWall = new BehaviourRoboterMovesBallToWall();
        BehaviourRoboterDrivesToBall roboterDrivesToBall = new BehaviourRoboterDrivesToBall();
        BehaviourRoboterHasToFindABall roboterHasToFindABall = new BehaviourRoboterHasToFindABall();

        while (step(TIME_STEP) != -1) {
            if(roboterAndBallAreNearAWall.isActivatable(camera, accelerometer, distanceSensors)) {
                speed = roboterAndBallAreNearAWall.calculateSpeed(camera, accelerometer, distanceSensors);
            } else if(roboterMovesBallToWall.isActivatable(camera, accelerometer, distanceSensors)) {
                speed = roboterMovesBallToWall.calculateSpeed(camera, accelerometer, distanceSensors);
            } else if(roboterDrivesToBall.isActivatable(camera, accelerometer, distanceSensors)) {
                speed = roboterDrivesToBall.calculateSpeed(camera, accelerometer, distanceSensors);
            } else {
                speed = roboterHasToFindABall.calculateSpeed(camera, accelerometer, distanceSensors);
            }
            setSpeed(speed[0], speed[1]);
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
