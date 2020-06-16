import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

public class Player extends Entity {
	private Game game;
	private int drawX = 1920 / 2;
	private int drawY = 1080 / 2;
	protected Sprite sprite; // this entity's sprite
	private Rectangle playerRectangle;
	private int upCounter = 0;
	private int leftCounter = 0;
	private int rightCounter = 0;
	private int downCounter = 0;
	long drawTimer;
	private Stages stages;

	private Image[] walkingDown = new Image[2];
	private Image[] walkingRight = new Image[3];
	private Image[] walkingUp = new Image[2];
	private Image[] walkingLeft = new Image[2];
	private Image[] standingStill = new Image[4];
	long lastDrawTimer;

	public Player(Game game, String r, int newX, int newY) {
		super(r, newX, newY);
		this.game = game;
		sprite = (SpriteStore.get()).getSprite(r);
		stages = new Stages(game);

		loadImages();
		lastDrawTimer = System.currentTimeMillis();

		playerRectangle = new Rectangle(drawX, drawY, 38, 53);

	}

	public void loadImages() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url;
		for (int x = 0; x < 4; x++) {
			url = Player.class.getResource("sprites/charStill" + x + ".png");
			standingStill[x] = tk.getImage(url);
		}
		for (int x = 0; x < 2; x++) {
			url = Player.class.getResource("sprites/char" + x + ".png");
			walkingDown[x] = tk.getImage(url);
		}
		for (int x = 0; x < 2; x++) {
			url = Player.class.getResource("sprites/charLeft" + x + ".png");
			walkingLeft[x] = tk.getImage(url);
		}
		for (int x = 0; x < 2; x++) {
			url = Player.class.getResource("sprites/charUp" + x + ".png");
			walkingUp[x] = tk.getImage(url);
		}
		for (int x = 0; x < 3; x++) {
			url = Player.class.getResource("sprites/charRight" + x + ".png");
			walkingRight[x] = tk.getImage(url);
		}

	}

	public void move(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public void draw(Graphics2D g) {
		sprite.draw(g, drawX, drawY);
	}

	public void checkMoveHorizontal(int dx, Graphics2D g) {
		int movementDelta = (int) Math.round(dx * 17.0 / 1000);
		int temp = (movementDelta > 0) ? 3 : -3;

		playerRectangle.setLocation(drawX + movementDelta + temp, drawY);

		int tileSetter = 0;

		for (int i = 0; i < game.getRectanglesOnScreen().size(); i++) {
			Item tempItem = game.getItemsOnScreen().get(i);
			Rectangle rectangle = game.getRectanglesOnScreen().get(i);

			if (playerRectangle.intersects(rectangle)) {

				movementDelta = 0;
				if (tempItem.getBush() == true) {
					tileSetter = -1;
					movementDelta = (int) Math.round(dx * 17.0 / 1000);
				} else if (!tempItem.getValidSpot()) {
					tileSetter = tempItem.getTeleport();
					movementDelta = (int) Math.round(dx * 17.0 / 1000);
				} else if (tempItem.getValidSpot()) {
					tileSetter = 1;
					movementDelta = (int) Math.round(dx * 17.0 / 1000);
				}

				break;
			} else {
				tileSetter = 1;
			}
		}

		Rectangle tileMapRectangle = new Rectangle(game.getTileMap().getExtraWidth() + game.getWorldX(),
				game.getTileMap().getExtraHeight() + game.getWorldY(), game.getTileMap().getWidth() * 16,
				game.getTileMap().getHeight() * 16);

		if (!tileMapRectangle.contains(playerRectangle)) {
			movementDelta = 0;
		}

		game.setDrawSign(false);
		switch (tileSetter) {
		case 1:
		case 11:
		case 12:
		case 19:
		case 22:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 44:
			super.x += movementDelta;
			break;
		case 2:
			super.x += movementDelta;
			stages.addHouse();
			break;
		case 3:
			super.x += movementDelta;
			stages.addPlayerHouse();
			game.getPlayer().setX(805);
			game.getPlayer().setY(250);
			break;
		case 4:
			super.x += movementDelta;
			stages.addMainStage();
			break;
		case 5:
			super.x += movementDelta;
			game.getPlayer().setX(1000);
			game.getPlayer().setY(540);
			stages.addStage29();
			break;
		case 6:
			game.setDrawSign(true);
			break;
		case 7:
			super.x += movementDelta;
			game.setDrawShop(true);
			break;
		case 8:
			super.x += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1840);
			game.getPlayer().setY(400);
			break;
		case 9:
			super.x += movementDelta;
			stages.addPokeCenter();
			game.getPlayer().setX(800);
			game.getPlayer().setY(600);
			break;
		case 13:
			super.x += movementDelta;
			stages.addScienceLab();
			game.getPlayer().setX(840);
			game.getPlayer().setY(590);
			break;
		case 14:
			super.y += movementDelta;
			stages.addElite1();
			break;
		case 15:
			super.y += movementDelta;
			stages.addElite2();
			break;
		case 16:
			super.y += movementDelta;
			stages.addElite3();
			break;
		case 17:
			super.y += movementDelta;
			stages.addElite4();
			break;
		case 18:
			super.y += movementDelta;
			stages.addChampionRoom();
			break;
		case 20:
			super.x += movementDelta;
			stages.addStage30();
			game.getPlayer().setX(960);
			game.getPlayer().setY(520);
			break;
		case 21:
			super.x += movementDelta;
			stages.addStage29();
			game.getPlayer().setX(-2300);
			break;
		case 23:
			super.x += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1400);
			game.getPlayer().setY(400);
			break;
		case 24:
			super.x += movementDelta;
			stages.addElite4PreRoom();
			break;
		case 30:
			super.x += movementDelta;
			stages.addElite1();
			break;
		case 31:
			super.x += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1565);
			game.getPlayer().setY(375);
			break;
		case 32:
			super.x += movementDelta;
			stages.addPokeShop();
			game.getPlayer().setX(960);
			game.getPlayer().setY(540);
			break;
		case 33:
			super.x += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1325);
			game.getPlayer().setY(400);
			break;
		case 34:
			super.x += movementDelta;
			stages.addStage30();
			game.getPlayer().setY(-3500);
			break;
		case 35:
			super.x += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setY(540);
			break;
		case 36:
			super.x += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setY(200);
			break;
		case 37:
			super.x += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setX(1365);
			game.getPlayer().setY(-380);
			break;
		case 38:
			super.x += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setX(1725);
			game.getPlayer().setY(450);
			break;
		case 39:
			super.x += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setX(1600);
			game.getPlayer().setY(-1200);
			break;
		case 40:
			super.x += movementDelta;
			stages.addElite4PreRoom();
			game.getPlayer().setX(960);
			game.getPlayer().setY(800);
			break;
		case 41:
			super.y += movementDelta;
			stages.addHouse();
			game.getPlayer().setX(850);
			game.getPlayer().setY(610);
			break;
		case 42:
			super.x += movementDelta;
			stages.addMainStage();
			game.getPlayer().setX(1350);
			game.getPlayer().setY(870);
			break;
		case 43:
			super.x += movementDelta;
			stages.addMainStage();
			game.getPlayer().setX(100);
			game.getPlayer().setY(540);
			break;
		case -1:
			super.x += movementDelta;
			randomEncounter();

		}

	}

	public void checkMoveVertical(int dy) {
		int movementDelta = (int) Math.round(dy * 17.0 / 1000);

		int temp = (movementDelta > 0) ? 3 : -3;
		playerRectangle.setLocation(drawX, drawY + movementDelta + temp);

		int tileSetter = 0;
		for (int i = 0; i < game.getRectanglesOnScreen().size(); i++) {
			Item tempItem = game.getItemsOnScreen().get(i);
			Rectangle rectangle = game.getRectanglesOnScreen().get(i);

			if (playerRectangle.intersects(rectangle)) {

				movementDelta = 0;
				if (tempItem.getBush() == true) {

					tileSetter = -1;
					movementDelta = (int) Math.round(dy * 17.0 / 1000);
				} else if (!tempItem.getValidSpot()) {
					tileSetter = tempItem.getTeleport();

					movementDelta = (int) Math.round(dy * 17.0 / 1000);
				} else if (tempItem.getValidSpot()) {
					tileSetter = 1;
					movementDelta = (int) Math.round(dy * 17.0 / 1000);
				}

				break;
			} else {
				tileSetter = 1;
			}
		}

		Rectangle tileMapRectangle = new Rectangle(game.getTileMap().getExtraWidth() + game.getWorldX(),
				game.getTileMap().getExtraHeight() + game.getWorldY(), game.getTileMap().getWidth() * 16,
				game.getTileMap().getHeight() * 16);

		if (!tileMapRectangle.contains(playerRectangle)) {
			movementDelta = 0;
		}

		game.setDrawSign(false);
		switch (tileSetter) {
		case 1:
		case 11:
		case 12:
		case 19:
		case 22:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 44:
			super.y += movementDelta;
			break;
		case 2:
			super.y += movementDelta;
			stages.addHouse();
			game.getPlayer().setX(805);
			game.getPlayer().setY(260);
			break;
		case 3:
			super.y += movementDelta;
			stages.addPlayerHouse();
			game.getPlayer().setX(805);
			game.getPlayer().setY(250);
			break;
		case 4:
			super.y += movementDelta;
			stages.addMainStage();
			game.getPlayer().setX(1200);
			game.getPlayer().setY(450);
			break;
		case 5:
			super.y += movementDelta;
			stages.addStage29();
			break;
		case 6:
			game.setDrawSign(true);
			break;
		case 7:
			super.y += movementDelta;
			game.setDrawShop(true);
			break;
		case 8:
			super.y += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1840);
			game.getPlayer().setY(400);
			break;
		case 9:
			super.y += movementDelta;
			stages.addPokeCenter();
			game.getPlayer().setX(800);
			game.getPlayer().setY(600);
			break;
		case 10:
			super.y += movementDelta;
			game.setDrawPC(true);
			break;
		case 13:
			super.y += movementDelta;
			stages.addScienceLab();
			game.getPlayer().setX(840);
			game.getPlayer().setY(590);
			break;
		case 14:
			super.y += movementDelta;
			stages.addElite1();
			break;
		case 15:
			super.y += movementDelta;
			stages.addElite2();
			break;
		case 16:
			super.y += movementDelta;
			stages.addElite3();
			break;
		case 17:
			super.y += movementDelta;
			stages.addElite4();
			break;
		case 18:
			super.y += movementDelta;
			stages.addChampionRoom();
			break;
		case 20:
			super.y += movementDelta;
			stages.addStage30();
			game.getPlayer().setX(960);
			game.getPlayer().setY(520);
			break;
		case 21:
			super.y += movementDelta;
			stages.addStage29();
			game.getPlayer().setX(-2300);
			break;
		case 23:
			super.y += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1400);
			game.getPlayer().setY(400);
			break;
		case 24:
			super.y += movementDelta;
			stages.addElite4PreRoom();
			break;
		case 30:
			super.y += movementDelta;
			stages.addElite1();
			break;
		case 31:
			super.y += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1565);
			game.getPlayer().setY(375);
			break;
		case 32:
			super.y += movementDelta;
			stages.addPokeShop();
			game.getPlayer().setX(960);
			game.getPlayer().setY(540);
			break;
		case 33:
			super.y += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setX(1325);
			game.getPlayer().setY(400);
			break;
		case 34:
			super.y += movementDelta;
			stages.addStage30();
			game.getPlayer().setY(-3500);
			break;
		case 35:
			super.y += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setY(540);
			break;
		case 36:
			super.y += movementDelta;
			stages.addCherryGroveCity();
			game.getPlayer().setY(200);
			break;
		case 37:
			super.y += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setX(1365);
			game.getPlayer().setY(-380);
			break;
		case 38:
			super.y += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setX(1725);
			game.getPlayer().setY(450);
			break;
		case 39:
			super.y += movementDelta;
			stages.addFinalTown();
			game.getPlayer().setX(1600);
			game.getPlayer().setY(-1200);
			break;
		case 40:
			super.y += movementDelta;
			stages.addElite4PreRoom();
			game.getPlayer().setX(960);
			game.getPlayer().setY(800);
			break;
		case 41:
			super.y += movementDelta;
			stages.addHouse();
			game.getPlayer().setX(850);
			game.getPlayer().setY(610);
			break;
		case 42:
			super.y += movementDelta;
			stages.addMainStage();
			game.getPlayer().setX(1350);
			game.getPlayer().setY(870);
			break;
		case 43:
			super.y += movementDelta;
			stages.addMainStage();
			game.getPlayer().setX(100);
			game.getPlayer().setY(540);
			break;
		case -1:
			super.y += movementDelta;
			randomEncounter();

		}
	}

	public void checkSelection(Graphics2D g) {

		playerRectangle.setLocation(drawX, drawY);

		g.fillRect(drawX, drawY, 38, 53);
		int tileSetter = 0;
		for (int i = 0; i < game.getRectanglesOnScreen().size(); i++) {
			Item tempItem = game.getItemsOnScreen().get(i);
			Rectangle rectangle = game.getRectanglesOnScreen().get(i);

			if (playerRectangle.intersects(rectangle)) {

				if (tempItem.getBush() == true) {

					tileSetter = -1;
				} else if (!tempItem.getValidSpot()) {
					tileSetter = tempItem.getTeleport();

				} else if (tempItem.getValidSpot()) {
					tileSetter = 1;
				}

				break;
			} else {
				tileSetter = 1;
			}
		}

		switch (tileSetter) {
		case 7:
			game.setDrawShop(true);
			break;
		case 10:
			game.setDrawPC(true);
			break;
		case 11:
			game.getTextBox().addMessageText("Your pokemon have been healed \n");
			for (int i = 0; i < game.getPokemonList().size(); i++) {
				game.getPokemonList().get(i).setHealth(game.getPokemonList().get(i).getMaxHealth());
				for (int k = 0; k < game.getPokemonList().get(i).moveList.size(); k++) {
					game.getPokemonList().get(i).moveList.get(k).setPP(game.getPokemonList().get(i).moveList.get(k).getMaxPP());
				}
			}
			break;
		case 12:
			game.setDrawPC(true);
			break;
		case 19:
		case 26:
		case 27:
		case 28:
		case 29:
			game.setPlayTransition(true);
			game.setInitBattle(false);
			game.setDrawBattle(true);
			game.setTrainerEncounter(true);
			game.setFightingElite4(true);
			break;
		case 22:
			game.setDrawShop(true);
			break;
		case 25:
			game.getTextBox().addMessageText("Behind me leads to the elite 4.\n");
			game.getTextBox().addMessageText("You will battle 4 of the best \ntrainers in this land.");
			game.getTextBox().addMessageText("After that you will face the \nchampion for his title.");
			game.getTextBox().addMessageText("Once you enter you won't be able \nto leave, enter if you are ready.");
			break;
		case 44:
			game.getTextBox().addMessageText("Welcome to the lab.\n");
			break;
		}
	}

	public void randomEncounter() {
		int random = (int) (Math.random() * 10000) + 1;
		if (random >= 9900) {
			game.setPlayTransition(true);
			game.setMidTransition(false);
			game.setInitBattle(false);
			game.setDrawBattle(true);

			Pokemon newPokemon = new Pokemon();
			int randomGenPokemon = (int) (Math.random() * game.getCompletePokemonList().size());
			newPokemon.cloneObject(game.getCompletePokemonList().get(randomGenPokemon));
			game.getEnemyPokemonList().add(newPokemon);
			game.setTrainerEncounter(false);
		}
	}

	public void drawDown(Graphics2D g) {
		g.drawImage(walkingDown[downCounter], drawX, drawY, null);
		if (System.currentTimeMillis() - 500 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			downCounter++;
			if (downCounter > 1) {
				downCounter = 0;
			}
		}
	}

	public void drawLeft(Graphics2D g) {
		g.drawImage(walkingLeft[leftCounter], drawX, drawY, null);
		if (System.currentTimeMillis() - 200 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			leftCounter++;
			if (leftCounter > 1) {
				leftCounter = 0;
			}
		}
	}

	public void drawUp(Graphics2D g) {
		g.drawImage(walkingUp[upCounter], drawX, drawY, null);
		if (System.currentTimeMillis() - 300 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			upCounter++;
			if (upCounter > 1) {
				upCounter = 0;
			}
		}
	}

	public void drawRight(Graphics2D g) {
		g.drawImage(walkingRight[rightCounter], drawX, drawY, null);
		if (System.currentTimeMillis() - 300 >= lastDrawTimer) {
			lastDrawTimer = System.currentTimeMillis();
			rightCounter++;
			if (rightCounter > 2) {
				rightCounter = 0;
			}
		}
	}

	public void drawStill(Graphics2D g, int direction) {
		g.drawImage(standingStill[direction], drawX, drawY, null);
	}

	public void setX(int x) {
		super.x = x;
	}

	public void setY(int y) {
		super.y = y;
	}

}
