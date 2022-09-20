package sample;

//packages to import; make sure you have one for any new classes you want to add
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class DopeRobot extends Robot {
	boolean movingForward = true;

	public void run() {
	//set different colors
		setBodyColor(Color.black);
		//this loop runs by default; if nothing happens, this is what the robot will do
		while (true) {
			double x = getX();
			double y = getY();
			double height = getBattleFieldHeight();
			double width = getBattleFieldWidth();
			double robotHeight = getHeight();
			moveNormal(x,y, height, width, robotHeight);
		}
	}

	public void reverseDirection() {
		if (movingForward) {
			back(100);
			movingForward = false;
		} else {
			ahead(100);
			movingForward = true;
		}
	}

	/**
	 * smartFire:  Custom fire method that determines firepower based on distance.
	 *
	 * @param robotDistance the distance to the robot to fire at
	 */
	public void smartFire(double robotDistance) {
		if (robotDistance > 200 || getEnergy() < 15) {
			fire(1);
		} else if (robotDistance > 50) {
			fire(2);
		} else {
			fire(3);
		}
	}

	//This method takes the position of the robot and checks if it's
	//about to hit the wall. If it's close, it will change direction and 
	//move. Needs to be paired with a good targeting system to be really effective
	public void moveNormal(double x, double y, double height, double width, double robotHeight) {
		if (x < robotHeight || x > (width - robotHeight)) {
			turnLeft(45);
			ahead(100);
		}
		else if (y < robotHeight || y > (height - robotHeight)) {
			turnLeft(45);
			ahead(100);
		}
		else {
			ahead(100);
		}
		
	}
	//when the Jimbot sees another one
	// public void onScannedRobot(ScannedRobotEvent e) {
	// 	fire(1);
	// 	if (e.getDistance() < 100) {
    //        fire(3);
    //     } else {
    //        fire(1);
    //     }
	// }

	public void onScannedRobot(ScannedRobotEvent e) {
		// Should we stop, or just fire?
		smartFire(e.getDistance());
	}
	
	//when the Jimbot gets hit
	public void onHitByBullet(HitByBulletEvent e) {
		// turnLeft(90 - e.getBearing());
		// back(100);
		reverseDirection();
	}
	
	//when the robot hits another one
	public void onHitRobot(HitRobotEvent e){
		if (e.isMyFault()) {
			reverseDirection();
		}
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
	}
	
	//when the robot hits a wall
	public void onHitWall(HitWallEvent e){
		turnRight(90);
	}
	
	//when the robot successfully shoots another robot
	public void onHit(BulletHitEvent e){
		
	}
	
	//when we win
	public void onWin(WinEvent e) {
		ahead(100);
		turnGunRight(360);
		back(100);
		turnGunRight(360);
	}
		
}