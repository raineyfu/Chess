/****************************************************************************
* Name:        Camera
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     Class to follow the player
*****************************************************************************/
public class Camera {
	private int x = 0;
	private int y = 0;
	private int maxOffsetX = 1920;
	private int maxOffsetY = 1080;
	private int minOffsetX = 0;
	private int minOffsetY = 0;

	public void update(int x, int y) {

		if (x > maxOffsetX) {
			this.x = maxOffsetX;
		}
		if (x < minOffsetX) {
			this.x = minOffsetX;
		}
		if (y > maxOffsetY) {
			this.y = maxOffsetY;
		}
		if (y < minOffsetY) {
			this.y = minOffsetY;
		}

		this.x = x;
		this.y = y;

	}

	public int getCameraX() {
		return x;
	}

	public int getCameraY() {
		return y;
	}

	public void setOffsetX(int maxX, int minX) {
		maxOffsetX = maxX;
		minOffsetX = minX;
	}

	public void setOffsetY(int maxY, int minY) {
		maxOffsetY = maxY;
		minOffsetY = minY;
	}

	public void setCameraX(int x) {
		this.x += x;
	}

	public void setCameraY(int y) {
		this.y += y;
	}

}
