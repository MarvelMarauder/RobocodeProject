
package sample;

//packages to import; make sure you have one for any new classes you want to add
import robocode.HitByBulletEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import java.awt.*;
import robocode.WinEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.BulletHitEvent;

public class MyFirstRobot extends Robot {


	public void run() {
	//set different colors
		setBodyColor(Color.black);
		//this loop runs by default; if nothing happens, this is what the robot will do
		while (true) {
			ahead(100);
			turnLeft(45);
			ahead(100);
			turnLeft(45);
			ahead(100);
		}
	}
	//when the robot sees another one
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
		if (e.getDistance() < 100) {
           fire(3);
        } else {
           fire(1);
        }
	}
	
	//when the robot gets hit
	public void onHitByBullet(HitByBulletEvent e) {
		turnLeft(90 - e.getBearing());
		back(100);
	}
	
	//when the robot hits another one
	public void onHitRobot(HitRobotEvent e) {
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