import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DistanceSensor;

public class BehaviourRoboterAndBallAreNearAWall extends BaseController implements IBehaviour {
    private final int MIN_DISTANCE_VALUE_TO_FIND_BALL = 1000;

    private int cycleCount = 0;
    private int hitCount = 0;
    private boolean didBounce = false;

    private int returnCycleCount = 0;
    private int returnMoveCount = 0;
    private boolean wasHit = false;

    private boolean ballOutOfSight = false;
    private int ballFoundCycles = 0;

    public BehaviourRoboterAndBallAreNearAWall() {
        super();
    }

    @Override
    public boolean isActivatable(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        boolean checkDistanceSensors = false;
        for(int i = 0; i < distanceSensors.length; i++) {
            if(distanceSensors[i].getValue() > MIN_DISTANCE_VALUE_TO_FIND_BALL) {
                checkDistanceSensors = true;
                break;
            }
        }

        if (ballFoundCycles > 0) {
            return true;
        }
        if (wasHit && returnCycleCount > 0) {
            returnCycleCount--;

            int[] image = camera.getImage();
            int cameraWidth = camera.getWidth();
            int cameraHeight = camera.getHeight();
            if (!ballOutOfSight && !detectBall(image, cameraWidth, cameraHeight, cameraWidth - 1)) {
                System.out.println("Ball out of sight on way back...");
                ballOutOfSight = true;
            } else if (ballOutOfSight && detectBall(image, cameraWidth, cameraHeight, cameraWidth - 1)) {
                System.out.println("Ball found on way back...");
                wasHit = false;
                ballFoundCycles = 10;
                return true;
            }

            return true;
        }

        if((accelerometer.getValues()[0] < -1.0 || accelerometer.getValues()[1] < -1.0)) {
            if(checkDistanceSensors) {
                if (!didBounce) {
                    if (hitCount <= 0) {
                        cycleCount = 1;
                        hitCount++;
                        didBounce = true;
                    } else if (cycleCount <= 10) {
                        hitCount++;
                        cycleCount++;
                        didBounce = true;
                    } else {
                        hitCount = 0;
                        cycleCount = 0;
                        didBounce = false;
                    }
                }

                //System.out.printf("Hit-Test: %d @ %d (Bounce = %d)\n", hitCount, cycleCount, didBounce ? 1 : 0);

                if (hitCount >= 2) {
                    wasHit = true;
                    ballOutOfSight = false;
                    hitCount = 0;
                    cycleCount = 0;
                    returnCycleCount -= 50; // drive back is faster
                    return true;
                }
            }
        } else if (didBounce) {
            //System.out.printf("Hit-Test: %d @ %d (Bounce = %d)\n", hitCount, cycleCount, 0);
            didBounce = false;
        }

        wasHit = false;
        returnCycleCount++;
        returnMoveCount = 0;
        return false;
    }

    @Override
    public double[] calculateSpeed(Camera camera, Accelerometer accelerometer, DistanceSensor[] distanceSensors) {
        if (ballFoundCycles > 0) {
            System.out.println("Turn to Ball found on way back...");
            ballFoundCycles--;
            return driveRight(1);
        }
        if (returnMoveCount++ < 5) {
            return standStill();
        }
        if (returnCycleCount < 15) {
            return driveRight(0.5);
        }
        return driveBack(returnMoveCount > returnCycleCount ? 0.05 : 0);
    }
}