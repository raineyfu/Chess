import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;

public class Item {
	private int x;
	private int y;
	private int permaX;
	private int permaY;

	private int sizeX;
	private int sizeY;
	private boolean bush;

	private boolean validSpot;
	public int teleport;

	private Image image;

	public Item(Image image, int x, int y, int sizeX, int sizeY, boolean walkable) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		permaX = (int) x;
		permaY = (int) y;

		validSpot = walkable;
		teleport = 0;
	}

	public Item(Image image, int x, int y, boolean walkable) {
		this.image = image;
		this.x = x;
		this.y = y;

		permaX = x;
		permaY = y;

		validSpot = walkable;
		teleport = 0;
	}

	public Item(Image image, int x, int y, boolean walkable, boolean bush) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.sizeX = 46;
		this.sizeY = 40;

		permaX = x;
		permaY = y;

		validSpot = walkable;

		this.bush = true;
		teleport = -1;
	}

	public Item(Image image, int x, int y, int sizeX, int sizeY, int teleportLocation) {
		teleport = teleportLocation;
		this.image = image;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		permaX = x;
		permaY = y;

		validSpot = false;
	}

	public Item(int x, int y, int sizeX, int sizeY, int teleportLocation) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		permaX = x;
		permaY = y;
		this.teleport = teleportLocation;
	}

	public void update(int worldX, int worldY) {
		x = permaX + worldX;
		y = permaY + worldY;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void draw(Graphics g) {
		g.drawImage(image, (int) x, (int) y, null);
	} // draw

	public boolean getValidSpot() {
		return validSpot;
	}

	public void setValidSpot(boolean newValidSpot) {
		validSpot = newValidSpot;
	}

	public int getTeleport() {
		return teleport;
	}

	public boolean getBush() {
		return bush;
	}

}
