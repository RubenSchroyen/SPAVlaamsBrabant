package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class with all the inheritable features of the game objects (food, worm, projectile)
 * 
 * @invar isValidPosition(getPosX(), getPosY())
 * 
 * @version 2.0
 * 
 * @Author Ruben Schroyen & Ralph Vancampenhoudt
 * 
 * An R² production for the course: Object-oriented Programming at KuLeuven
 */



public class GameObject 
{
	/**
	 * The world this object is given
	 */
	private World world;
	
	/**
	 * The position on the Y-axis this object is given
	 */
	private double posY;
	
	/**
	 * The position on the X-axis this object is given
	 */
	private double posX;
	
	/**
	 * The radius this object is given
	 */
	private double radius;
	
	/**
	 * A boolean to determine whether the object is already destroyed or not
	 */
	private boolean isDestroyed;

	
	/**
	 * The constructor for the chosen object, giving it a world, position and radius
	 * 
	 * @param world
	 * 		The world this object is given
	 * 
	 * @param posX
	 * 		The position on the X-axis this object is given
	 * 
	 * @param posY
	 * 		The position on the Y-axis this object is given
	 * 
	 * @param radius
	 * 		The radius this object is given
	 * 
	 * @post 	
	 * 		We set every value to the given one
	 * 			| new.getPosX() == posX
	 * 			| new.getPosY() == posY
	 * 			| new.getWorld() == world
	 * 			| new.getRadius() == radius
	 */
	public GameObject(World world, double posX, double posY, double radius)
	{
		if (this.isValidPosition(posX, posY))
		{
			this.setPosX(posX);
			this.setPosY(posY);
		}
		this.setWorld(world);
		this.setRadius(radius);
	}

	
	/**
	 * This method checks if the value for position is a valid one
	 * 
	 * @param posX
	 * 		The position on the X-axis this object is given
	 * 
	 * @param posY
	 * 		The position on the Y-axis this object is given
	 * 
	 * @return
	 * 		True if the value is a valid one
	 * 			| !posX == Double.NEGATIVE_INFINITY
	 * 			| !posY == Double.NEGATIVE_INFINITY
	 * 			| !posX == Double.POSITIVE_INFINITY
	 * 			| !posY == Double.POSITIVE_INFINITY
	 * 
	 * @throws IllegalArgumentException
	 * 		If the value is not a valid one 
	 * 			| posX == Double.NEGATIVE_INFINITY
	 * 			| posY == Double.NEGATIVE_INFINITY
	 * 			| posX == Double.POSITIVE_INFINITY
	 * 			| posY == Double.POSITIVE_INFINITY
	 */
	public boolean isValidPosition(double posX, double posY) throws IllegalArgumentException 
	{
		if ((posX == Double.NEGATIVE_INFINITY) || (posY == Double.NEGATIVE_INFINITY) || (posX == Double.POSITIVE_INFINITY) || (posY == Double.POSITIVE_INFINITY))
			throw new IllegalArgumentException("Not a valid value for position");
		return true;
	}

	/**
	 * This method sets the value for the Y-position of the object
	 * 
	 * @param posY
	 * 		The Y-position of the object in meters
	 * 
	 * @post 
	 * 		Sets the value of the object Y-position to a newly calculated or given Y-position
	 * 		| new.getPosY() == posY
	 */
	
	@Raw @Model
	public void setPosY(double posY) 
	{
		this.posY = posY;
	}

	/**
	 * This method sets the value for the X-position of the object
	 * 
	 * @param posX
	 * 		The X-position of the object in meters
	 * 
	 * 
	 * @post 
	 * 		Sets the value of the object X-position to a newly calculated or given X-position
	 * 		| new.getPosX() == posX
	 */
	
	@Raw @Model
	public void setPosX(double posX) 
	{
		this.posX = posX;
	}


	/**
	 * This method sets the value for the radius of the object
	 * 
	 * @param radius
	 * 		The radius the object has been given in meters
	 *  
	 * @post 
	 * 		Sets the value of the object radius to a newly calculated or given radius
	 * 		| new.getRadius == radius
	 */
	@Raw @Model
	public void setRadius(double radius) 
	{
		this.radius = radius;
	}

	/**
	 * This method sets the world of a object
	 * 
	 * @param world
	 * 		the world of this object 
	 * 
	 * @post
	 * 		Sets the world of a object to this world
	 * 			| new.getWorld() == world
	 */
	@Raw @Model
	public void setWorld(World world) 
	{
		this.world = world;
	}

	/**
	 * This method recalls the world of the object
	 */
	@Raw @Basic
	public World getWorld() 
	{
		return world;
	}

	/**
	 * This method recalls the Y-position of the object
	 */
	@Raw @Basic
	public double getPosY() 
	{
		return posY;
	}

	/**
	 * This method recalls the X-position of the object
	 */
	@Raw @Basic
	public double getPosX() 
	{
		return posX;
	}

	/**
	 * This method recalls the radius of the object
	 */
	@Raw @Basic
	public double getRadius() 
	{
		return radius;
	}
	/**
	 * This method returns if the gameobject is still active.
	 * 
	 * @return true if isDestroyed == false
	 * 			false if isDestroyed == true
	 */
	public boolean isActive()
	{
		return !isDestroyed;
	}
	
	/**
	 * This method destroys the gameobject and removes it from the world.
	 * 
	 * 
	 * @post
	 * 	removes the object from the world
	 * 		| new.getWorld() == null
	 */
	public void destroy()
	{
		this.getWorld().removeObject(this);
		this.setWorld(null);
		this.isDestroyed = true;
	}

}
