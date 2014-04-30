package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

/**
 * The class of all movable objects
 * 
 * @version 2.0
 * 
 * @Author Ruben Schroyen & Ralph Vancampenhoudt
 * 
 * An R² production for the course: Object-oriented Programming at KuLeuven
 *
 * * @invar isValidMass(getMass())
 *
 */
public class MovableObject extends GameObject
{

	/**
	 *  The angle the worm is facing in radial
	 **/
	private double angle;

	/**
	 *   The mass an object has in kilogram
	 */
	public double mass;
	
	/**
	 *   The earth acceleration (9.80665)
	 */
	public final double g = 9.80665;
	
	/**
	 *   The force with which an object jumps
	 */
	public double force;


	/**
	 *   The velocity with which an object jumps
	 */
	public double velocity;


	/**
	 *   The time an object is in the air during a jump
	 */
	public double time;

	
	
	public MovableObject(World world, double posX, double posY, double radius, double angle) {
		super(world, posX, posY, radius);
		this.setAngle(angle);
		
	}



	public double getAngle() {
		return angle;
	}



	/**
	 * This method sets the value for the angle a worm is facing. If the angle surpasses the value of PI, the angle gets reset by extracting PI
	 * 
	 * @param angle
	 * 		The angle of direction a worm is facing
	 * 			If angle surpasses the value of PI, it gets reset to the angle - the amount of times PI goes in the angle
	 * 				| this.angle = angle - k*Math.PI
	 * 			If angle surpasses the value of -PI, it gets reset to the angle + the amount of times PI goes in the angle
	 * 				| this.angle = angle + k*Math.PI
	 * 
	 * @post 
	 * 		Sets the value of the worms angle to a newly calculated or given angle
	 * 		| new.getAngle() == angle
	 */
	@Basic @Model
	public void setAngle(double angle) 
	{
		if (angle > Math.PI)
			angle = Math.PI;
		if (angle < -Math.PI)
			angle = -Math.PI;
		this.angle = angle;
	}
	



	public double getForce() {
		return force;
	}



	public void setForce(double force) {
		this.force = force;
	}



	public double getVelocity() {
		return velocity;
	}



	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}



	

	public double getTime() {
		return time;
	}



	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * This method makes the object follow a certain direction
	 * 
	 * @param delta
	 * 		The steps in time that are taken to calculate the position
	 * 
	 * @post
	 * 		The positions get set to the calculated ones
	 * 			| new.getPosY() == jumpStep[0]
	 * 			| new.getPosX() == jumpStep[1]
	 */
	public void Jump(double delta)
	{
		double begin = this.getPosY();
		
			double[] jumpStep = new double[2];
			jumpStep = this.JumpStep(this.JumpTime(delta));
			this.setPosX(jumpStep[0]);
			this.setPosY(jumpStep[1]);
			
			
		
		
	}


	/**
	 * This method calculates the in-air time
	 * 
	 * @param delta
	 * 		
	 * @return
	 * 		jumpTime
	 */
	public double JumpTime(double delta)
	{
		double X = this.getPosX();
		double Y = this.getPosY();
		double[] jumpStepResult = new double[2];
		double jumpTime = 0;
		while (getWorld().isPassable(X, Y, this.getRadius()))
		{
			jumpStepResult = this.JumpStep(jumpTime);
			X = jumpStepResult[0];
			Y = jumpStepResult[1];
			jumpTime += delta;
		}
		return jumpTime;
	}

	/**
	 * This method calculates the position in air for the object
	 * 
	 * @param DeltaT
	 *		The steps in time to calculate the position 
	 *
	 * @return
	 * 		jumpStep
	 */
	public double[] JumpStep(double DeltaT)
	{       
		
		double velocityX = this.getVelocity() * Math.cos(this.getAngle());
		double velocityY = this.getVelocity() * Math.sin(this.getAngle());
		double x = this.getPosX() + (velocityX * DeltaT);
		double y = this.getPosY() + (velocityY * DeltaT - 0.5*g*Math.pow(DeltaT, 2));

		double[] jumpstep = new double[] {x,y};

		return jumpstep;

	}
}
