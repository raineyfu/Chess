/****************************************************************************
* Name:        Entity
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     Object for controlling all movement and collision
*****************************************************************************/
/* Entity.java
 * An entity is any object that appears in the game.
 * It is responsible for resolving collisions and movement.
 */

import java.awt.*;
import java.util.*;

public class Entity {

	// Java Note: the visibility modifier "protected"
	// allows the variable to be seen by this class,
	// any classes in the same package, and any subclasses
	// "private" - this class only
	// "public" - any class can see it

	protected double x; // current x location
	protected double y; // current y location
	protected double permaX;
	protected double permaY;
	protected double maxX;
	protected double maxY;

	protected Sprite sprite; // this entity's sprite
	protected double dx; // horizontal speed (px/s) + -> right
	protected double dy; // vertical speed (px/s) + -> down
	protected Sprite[] walkingUp = new Sprite[3];
	protected Sprite[] walkingDown = new Sprite[3];
	protected Sprite[] walkingRight = new Sprite[3];
	protected Sprite[] walkingLeft = new Sprite[3];
	protected Sprite[] charStill = new Sprite[4];
	private int rightCounter = 0;
	private int leftCounter = 0;
	private int upCounter = 0;
	private int downCounter = 0;
	private long lastDrawTimer = System.currentTimeMillis();

	protected long timer = 1000;

	public Rectangle me = new Rectangle(); // bounding rectangle of
											// this entity
	private Rectangle him = new Rectangle(); // bounding rect. of other
												// entities

	/*
	 * Constructor input: reference to the image for this entity, initial x and y
	 * location to be drawn at
	 */
	public Entity(String r, float newX, float newY) {
		x = newX;
		y = newY;
		permaX = newX;
		permaY = newY;
		sprite = (SpriteStore.get()).getSprite(r);
	} // constructor

	/* public Entity(String source, int x, int y, int maxX, int maxY) {
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.maxY = maxY;
		permaX = x;
		permaY = y;

		for (int i = 0; i < 4; i++) {
			charStill[i] = (SpriteStore.get()).getSprite("assets/sprites/mom/charStill" + i + ".png");
		}

		for (int i = 0; i < 2; i++) {
			walkingUp[i] = (SpriteStore.get()).getSprite("assets/sprites/mom/charUp" + (i + 1) + ".png");
			walkingRight[i] = (SpriteStore.get()).getSprite("assets/sprites/mom/charRight" + (i + 1) + ".png");
			walkingDown[i] = (SpriteStore.get()).getSprite("assets/sprites/mom/charDown" + (i + 1) + ".png");
			walkingLeft[i] = (SpriteStore.get()).getSprite("assets/sprites/mom/charLeft" + (i + 1) + ".png");
		}
		walkingUp[2] = charStill[0];
		walkingRight[2] = charStill[1];
		walkingDown[2] = charStill[2];
		walkingLeft[2] = charStill[3];
		sprite = charStill[2];
	} */

	/*
	 * move input: delta - the amount of time passed in ms output: none purpose:
	 * after a certain amout of time has passed, update the location
	 */
	public void move() {
		// update location of entity based ov move speeds
		x += dx;
		y += dy;
		permaX += dx;
		permaY += dy;
	} // move

	public void moveHorizontal(int x) {
		this.x += x;
	}

	public void moveVertical(int y) {
		this.y += y;
	}

	// get and set velocities
	public void setHorizontalMovement(double newDX) {
		dx = newDX;
	} // setHorizontalMovement

	public void setVerticalMovement(double newDY) {
		dy = newDY;
	} // setVerticalMovement

	public double getHorizontalMovement() {
		return dx;
	} // getHorizontalMovement

	public double getVerticalMovement() {
		return dy;
	} // getVerticalMovement

	// get position
	public int getX() {
		return (int) x;
	} // getX

	public int getY() {
		return (int) y;
	} // getY

	/*
	 * Draw this entity to the graphics object provided at (x,y)
	 */
	public void draw(Graphics2D g) {
		if (dx > 0) {
			drawRight(g);
		} else if (dx < 0) {
			drawLeft(g);
		} else if (dy > 0) {
			drawUp(g);
		} else if (dy < 0) {
			drawDown(g);
		} else {
			sprite.draw(g, (int) x, (int) y);
		}
	} // draw

	/*
	 * Do the logic associated with this entity. This method will be called
	 * periodically based on game events.
	 */
	public void doLogic() {
		if (x >= maxX) {
			dx = 0;
		}
		if (y >= maxY) {
			dy = 0;
		}
	}

	/*
	 * collidesWith input: the other entity to check collision against output: true
	 * if entities collide purpose: check if this entity collides with the other.
	 */
	public boolean collidesWith(Entity other) {
		me.setBounds((int) permaX, (int) permaY, sprite.getWidth(), sprite.getHeight());
		him.setBounds(other.getX(), other.getY(), other.sprite.getWidth(), other.sprite.getHeight());
		return me.intersects(him);
	} // collidesWith

	public void update(int worldX, int worldY) {
		x = permaX + worldX;
		y = permaY + worldY;
	}

	public void drawRight(Graphics2D g) {
		walkingRight[rightCounter].draw(g, (int) x, (int) y);
		if (System.currentTimeMillis() - 300 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			rightCounter++;
			if (rightCounter > 2) {
				rightCounter = 0;
			}
		}
	}

	public void drawUp(Graphics2D g) {
		walkingUp[upCounter].draw(g, (int) x, (int) y);
		if (System.currentTimeMillis() - 300 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			upCounter++;
			if (upCounter > 2) {
				upCounter = 0;
			}
		}
	}

	public void drawDown(Graphics2D g) {
		walkingDown[downCounter].draw(g, (int) x, (int) y);
		if (System.currentTimeMillis() - 300 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			downCounter++;
			if (downCounter > 2) {
				downCounter = 0;
			}
		}
	}

	public void drawLeft(Graphics2D g) {
		walkingLeft[leftCounter].draw(g, (int) x, (int) y);
		if (System.currentTimeMillis() - 300 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			leftCounter++;
			if (leftCounter > 2) {
				leftCounter = 0;
			}
		}
	}

} // Entity class