package aleksanderbraksator.trajectory;

public class TrajectoryPoint {

	double velocityX = 0d;
	double velocityY = 0d;
	double velocityZ = 0d;
	double elapsedTime = 0d;
	double timeInterval = 0d;
	double positionX = 0d;
	double positionY = 0d;
	double positionZ = 0d;
	
	public double getVelocity() {
		return Math.sqrt(Math.pow(velocityX, 2.0d) + Math.pow(velocityY, 2.0d));
	}
	
	/**
	 * Gets the angle of the ball trajectory vector at this point off the horizontal axis
	 * @return angle in degrees off horizontal axis
	 */
    public double getAngleInDegrees() {
        return Math.atan2(velocityY, velocityX) * 180.0d / Math.PI;
    }
    
    public double getDistanceXInterval() {
    	return velocityX * timeInterval;
    }
	
    public double getDistanceYInterval() {
    	return velocityY * timeInterval;
    }

    public double getDistanceZInterval() {
    	return velocityZ * timeInterval;
    }

    
	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getVelocityZ() {
		return velocityZ;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public double getTimeInterval() {
		return timeInterval;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getPositionZ() {
		return positionZ;
	}

	@Override
	public String toString() {
		return "TrajectoryPoint [velocityX=" + velocityX + ", velocityY="
				+ velocityY + ", velocityZ=" + velocityZ + ", elapsedTime="
				+ elapsedTime + ", timeInterval=" + timeInterval + ", xPos="
				+ positionX + ", yPos=" + positionY + ", zPos=" + positionZ
				+ ", getVelocity()=" + getVelocity() + ", getAngleInDegrees()="
				+ getAngleInDegrees() + ", getDistanceXInterval()="
				+ getDistanceXInterval() + ", getDistanceYInterval()="
				+ getDistanceYInterval() + ", getDistanceZInterval()="
				+ getDistanceZInterval() + "]";
	}
    
    

}
