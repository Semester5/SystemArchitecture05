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
        BehaviourRoboterSearchBall roboterHasToFindABall = new BehaviourRoboterSearchBall();

        int lastStepType = -1;
        while (step(TIME_STEP) != -1) {
            /*
            System.out.printf("Sensorwerte: %.5f / %.5f -- %.5f / %.5f / %.5f / %.5f : ",
                    accelerometer.getValues()[0],  accelerometer.getValues()[1],
                    distanceSensors[0].getValue(), distanceSensors[1].getValue(), distanceSensors[2].getValue(), distanceSensors[3].getValue());
            */
            if(roboterAndBallAreNearAWall.isActivatable(camera, accelerometer, distanceSensors)) {
                speed = roboterAndBallAreNearAWall.calculateSpeed(camera, accelerometer, distanceSensors);
                if (lastStepType != 0) {
                    System.out.printf("Robi is near a wall.\n");
                }
                lastStepType = 0;
            } else if(roboterMovesBallToWall.isActivatable(camera, accelerometer, distanceSensors)) {
                speed = roboterMovesBallToWall.calculateSpeed(camera, accelerometer, distanceSensors);
                if (lastStepType != 1) {
                    System.out.printf("Robi moves ball to a wall.\n");
                }
                lastStepType = 1;
            } else if(roboterDrivesToBall.isActivatable(camera, accelerometer, distanceSensors)) {
                speed = roboterDrivesToBall.calculateSpeed(camera, accelerometer, distanceSensors);
                if (lastStepType != 2) {
                    System.out.printf("Robi drives to a ball. \n");
                }
                lastStepType = 2;
            } else {
                speed = roboterHasToFindABall.calculateSpeed(camera, accelerometer, distanceSensors);
                if (lastStepType != 3) {
                    System.out.printf("Robi is looking for a new ball.\n");
                }
                lastStepType = 3;
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
