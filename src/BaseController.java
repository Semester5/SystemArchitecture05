public abstract class BaseController {

    protected final int MIN_SPEED = 0;
    protected final int MAX_SPEED = 1000;

    protected double[] speed;

    public BaseController() {
        this.speed = new double[2];
    }

    protected double[] driveRight() {
        speed[0] = MAX_SPEED;
        speed[1] = MIN_SPEED;
        return speed;
    }

    protected double[] driveLeft() {
        speed[0] = MIN_SPEED;
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