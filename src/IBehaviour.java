import com.cyberbotics.webots.controller.DifferentialWheels;

public interface IBehaviour {
    final int MIN_SPEED = 0; // min. motor speed
    final int MAX_SPEED = 1000; // max. motor speed

    float[] speed = new float[2];

    boolean isActivatable();
    float[] calculateSpeed();
}
