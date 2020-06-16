import java.awt.Image;

public class Tiles {
	private String terrainType = "";
	private String[] locationList = { "sprites/lightGrass.png" };
	private Item object;
	private Image image;
	private boolean transitionSpot;

	public Tiles(Image image, boolean transitionSpot) {
		this.image = image;
		this.transitionSpot = transitionSpot;
	}

	public String getTerrain() {
		return terrainType;
	}

	public String getLocation() {
		return locationList[0];
	}

	public Item getObject() {
		return object;
	}

	public void setObject(Item object) {
		this.object = object;
	}

	public void drawTile(int x, int y) {

	}

	public Image getImage() {
		return image;
	}

	public boolean getTransitionSpot() {
		return transitionSpot;
	}

}
