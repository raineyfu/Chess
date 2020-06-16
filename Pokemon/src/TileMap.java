import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class TileMap {

	private Game game;
	private int width;
	private int height;
	public static Tiles[][] tiles;
	public static int[][] map;
	private int extraHeight;
	private int extraWidth;

	public TileMap(Game game, int width, int height, String levelLocation, int extraWidth, int extraHeight) {
		this.game = game;
		this.width = width;
		this.height = height;
		this.extraHeight = extraHeight;
		this.extraWidth = extraWidth;

		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("sprites/lightGrass.png");
		Image grass1 = tk.getImage(url);
		url = Stages.class.getResource("sprites/lighterGrass.png");
		Image grass2 = tk.getImage(url);
		url = Stages.class.getResource("assets/woodFloor.png");
		Image floor1 = tk.getImage(url);
		url = Stages.class.getResource("assets/sand.png");
		Image sand1 = tk.getImage(url);
		url = Stages.class.getResource("assets/pokeCenterFloor.png");
		Image pokeCenterFloor = tk.getImage(url);
		url = Stages.class.getResource("assets/grayFloor.png");
		Image floor2 = tk.getImage(url);
		url = Stages.class.getResource("assets/championRoomImage.png");
		Image championFloor = tk.getImage(url);
		url = Stages.class.getResource("assets/elite1FloorImage.png");
		Image elite1Floor = tk.getImage(url);
		url = Stages.class.getResource("assets/elite2FloorImage.png");
		Image elite2Floor = tk.getImage(url);
		url = Stages.class.getResource("assets/elite3FloorImage.png");
		Image elite3Floor = tk.getImage(url);
		url = Stages.class.getResource("assets/elite4FloorImage.png");
		Image elite4Floor = tk.getImage(url);
		url = Stages.class.getResource("assets/wood.jpg");
		Image wood = tk.getImage(url);

		String[] test = game.getFileContents(levelLocation);
		int[][] mapGrid = new int[width][height];

		for (int y = 0; y < mapGrid.length; y++) {
			test[y] = test[y].trim();
			String[] tempStore = test[y].split(" ");
			for (int x = 0; x < mapGrid[y].length; x++) {
				tempStore[x] = tempStore[x].trim();
				mapGrid[y][x] = Integer.parseInt(tempStore[x]);
			}
		}

		tiles = new Tiles[width][height];
		Tiles tempTiles;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				switch (mapGrid[x][y]) {
				case 0:
					tempTiles = new Tiles(grass2, true);
					tiles[x][y] = tempTiles;
					break;
				case 1:
					tempTiles = new Tiles(grass1, false);
					tiles[x][y] = tempTiles;
					break;
				case 2:
					tempTiles = new Tiles(grass2, false);
					tiles[x][y] = tempTiles;
					break;
				case 3:
					tempTiles = new Tiles(floor1, false);
					tiles[x][y] = tempTiles;
					break;
				case 4:
					tempTiles = new Tiles(sand1, false);
					tiles[x][y] = tempTiles;
					break;
				case 5:
					tempTiles = new Tiles(pokeCenterFloor, false);
					tiles[x][y] = tempTiles;
					break;
				case 6:
					tempTiles = new Tiles(floor2, false);
					tiles[x][y] = tempTiles;
					break;
				case 7:
					tempTiles = new Tiles(elite1Floor, false);
					tiles[x][y] = tempTiles;
					break;
				case 8:
					tempTiles = new Tiles(elite2Floor, false);
					tiles[x][y] = tempTiles;
					break;
				case 9:
					tempTiles = new Tiles(championFloor, false);
					tiles[x][y] = tempTiles;
					break;
				case 10:
					tempTiles = new Tiles(elite3Floor, false);
					tiles[x][y] = tempTiles;
					break;
				case 11:
					tempTiles = new Tiles(elite4Floor, false);
					tiles[x][y] = tempTiles;
					break;
				case 12:
					tempTiles = new Tiles(wood, false);
					tiles[x][y] = tempTiles;
					break;
				}

			}
		}

		map = new int[121][69];

		updateMap();
	}

	public void updateMap() {
		initMap();

		for (int x = 0; x < game.getItemsOnScreen().size(); x++) {
			Item tempItem = game.getItemsOnScreen().get(x);
			int tileSetter = 0;

			if (tempItem.getBush() == true) {
				tileSetter = -1;
				setTileFromItem(tempItem, tileSetter);
			} else if (!tempItem.getValidSpot()) {
				tileSetter = tempItem.getTeleport();
				setTileFromItem(tempItem, tileSetter);
			}
		}
	}

	private void setTileFromItem(Item itemToSet, int tileSetter) {
		int tempValueX = Math.round(itemToSet.getX() / 16);
		int tempValueY = Math.round(itemToSet.getY() / 16);

		int sizeX = Math.round(itemToSet.getSizeX() / 16);
		int sizeY = Math.round(itemToSet.getSizeY() / 16);
		for (int i = tempValueX; i <= tempValueX + sizeX; i++) {
			map[i][tempValueY] = tileSetter;
			for (int z = tempValueY; z <= tempValueY + sizeY; z++) {
				map[i][z] = tileSetter;
			}
		}
	}

	public void initMap() {
		for (int x = 0; x < 121; x++) {
			for (int y = 0; y < 69; y++) {
				map[x][y] = 1;
			}
		}
	}

	public Tiles getTile(int x, int y) {
		return tiles[x][y];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getExtraHeight() {
		return extraHeight;
	}

	public int getExtraWidth() {
		return extraWidth;
	}

	public Image getImage() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = TileMap.class.getResource("cardback.png");
		return tk.getImage(url);
	}

}
