public abstract class BaseController {

    protected final int MIN_SPEED = 0;
    protected final int MAX_SPEED = 1000;

    protected double[] speed;

    public BaseController() {
        this.speed = new double[2];
    }

    protected double[] standStill() {
        speed[0] = 0;
        speed[1] = 0;
        return speed;
    }

    protected double[] rotate() {
        speed[0] = MAX_SPEED;
        speed[1] = -MAX_SPEED;
        return speed;
    }

    protected double[] driveRight(double rotationFactor) {
        speed[0] = MAX_SPEED;
        speed[1] = MAX_SPEED * rotationFactor;
        return speed;
    }

    protected double[] driveLeft(double rotationFactor) {
        speed[0] = MAX_SPEED * rotationFactor;
        speed[1] = MAX_SPEED;
        return speed;
    }

    protected double[] driveForward() {
        speed[0] = MAX_SPEED;
        speed[1] = MAX_SPEED;
        return speed;
    }

    protected double[] driveBack() {
        speed[0] = -MAX_SPEED;
        speed[1] = -MAX_SPEED;
        return speed;
    }
}