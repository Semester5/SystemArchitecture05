package Example;

import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.DifferentialWheels;


public abstract class RobotWithCameraAccelerometerWheelEncoder extends DifferentialWheels {
	
	public RobotWithCameraAccelerometerWheelEncoder(int sensorResponse) {

//		Camera.pixelGetRed(0);
		
		this.getCamera(getCameraName()).enable(sensorResponse);
		
		this.getAccelerometer(getAccelerometerName()).enable(sensorResponse);
		
		this.enableEncoders(sensorResponse);
	}
	
	
	protected String getCameraName() {
		return "CAMERA";
	}

	
	protected String getAccelerometerName() {
		return "ACCELEROMETER";
	}

	
	public int getCameraWidth() {
		return this.getCamera(getCameraName()).getWidth();
	}
	
	
	public int getCameraHeight() {
		return this.getCamera(getCameraName()).getHeight();
	}


	
	public int[] getCameraValues() {
//		Camera.pixelGetBlue(1);
		return this.getCamera(getCameraName()).getImage();
	}
	
	
	public double getXAccel() {
		return this.getAccelerometer(getAccelerometerName()).getValues()[0];
	}

	
	public double getYAccel() {
		return this.getAccelerometer(getAccelerometerName()).getValues()[1];
	}
	
	
	public double getZAccel() {
		return this.getAccelerometer(getAccelerometerName()).getValues()[2];
	}
	
	public double[] getEncoderValues()  {
		return new double[] { this.getLeftEncoder(), this.getRightEncoder()};
	}
}
