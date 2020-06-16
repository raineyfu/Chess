import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Stages {
	private Game game;
	private Image image;

	Toolkit tk = Toolkit.getDefaultToolkit();
	URL url;

	public Stages(Game game) {
		this.game = game;
	}

	public void addStage29() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 253, 94, "route29.txt", -2600, -200));
		game.stopClip();
		game.play("/assets/Music/route29.wav");

		Image tree = getImage("assets/tree1.png");
		Image bush = getImage("assets/bush.png");

		game.getItems().add(new Item(-2595, 200, 70, 600, 8));

		for (int x = -2600; x < 1400; x += 46) {
			if (x < 875 && x > -325) {
				Item topBush = new Item(bush, x, -100, true, true);
				game.getItems().add(topBush);
			}
			if (x < 875 && x > -325) {
				Item topBush1 = new Item(bush, x, -50, true, true);
				game.getItems().add(topBush1);
			}
			if (x < 875 && x > -325) {
				Item topBush2 = new Item(bush, x, 0, true, true);
				game.getItems().add(topBush2);
			}
			if (x < 875 && x > -325) {
				Item topBush3 = new Item(bush, x, 50, true, true);
				game.getItems().add(topBush3);
			}
			if (x < 200 && x > -200 || (x < -350 && x > -650)) {
				Item topBush4 = new Item(bush, x, 100, true, true);
				game.getItems().add(topBush4);
			}
			if (x < 200 && x > -200 || (x < -350 && x > -650)) {
				Item topBush5 = new Item(bush, x, 150, true, true);
				game.getItems().add(topBush5);
			}
			if (x < 200 && x > -200 || (x < -350 && x > -650) || (x < -1450 && x > -2000)) {
				Item topBush6 = new Item(bush, x, 200, true, true);
				game.getItems().add(topBush6);
			}
			if (x < 200 && x > -200 || (x < -350 && x > -650) || (x < -1450 && x > -2000)) {
				Item topBush7 = new Item(bush, x, 250, true, true);
				game.getItems().add(topBush7);
			}
			if (x < 200 && x > -50 || (x < -200 && x > -650) || (x < -1550 && x > -2050)) {
				Item topBush9 = new Item(bush, x, 300, true, true);
				game.getItems().add(topBush9);
			}
			if (x < -200 && x > -650 || (x < -1550 && x > -2050)) {
				Item topBush10 = new Item(bush, x, 350, true, true);
				game.getItems().add(topBush10);
			}
			if (x < -200 && x > -875 || (x < -1700 && x > -2150)) {
				Item topBush11 = new Item(bush, x, 400, true, true);
				game.getItems().add(topBush11);
			}
			if (x < -200 && x > -875 || (x < -1700 && x > -2150)) {
				Item topBush12 = new Item(bush, x, 450, true, true);
				game.getItems().add(topBush12);
			}
			if (x < 200 && x > -50 || (x < -200 && x > -325) || (x < -750 && x > -875) || (x < -1900 && x > -2250)) {
				Item topBush13 = new Item(bush, x, 500, true, true);
				game.getItems().add(topBush13);
			}
			if (x < 650 && x > 280 || (x < 200 && x > -50) || (x < -200 && x > -325) || (x < -750 && x > -875)
					|| (x < -1900 && x > -2250)) {
				Item topBush14 = new Item(bush, x, 550, true, true);
				game.getItems().add(topBush14);
			}
			if (x < 700 && x > 280 || (x < 200 && x > -50) || (x < -1700 && x > -2150)) {
				Item topBush15 = new Item(bush, x, 600, true, true);
				game.getItems().add(topBush15);
			}
			if (x < 750 && x > 280 || (x < 200 && x > -50) || (x < -1700 && x > -2150)) {
				Item topBush16 = new Item(bush, x, 650, true, true);
				game.getItems().add(topBush16);
			}
			if (x < 750 && x > 280 || (x < 200 && x > -50)) {
				Item topBush17 = new Item(bush, x, 700, true, true);
				game.getItems().add(topBush17);
			}
			if (x < 900 && x > 280) {
				Item topBush18 = new Item(bush, x, 750, true, true);
				game.getItems().add(topBush18);
			}
			if (x < 900 && x > 280 || (x < -1450 && x > -2025)) {
				Item topBush19 = new Item(bush, x, 800, true, true);
				game.getItems().add(topBush19);
			}
			if (x < 900 && x > 450 || (x < -1450 && x > -2025)) {
				Item topBush20 = new Item(bush, x, 850, true, true);
				game.getItems().add(topBush20);
			}
			if (x < 640 && x > 450 || (x < 150 && x > -200) || (x < -1450 && x > -2025)) {
				Item topBush21 = new Item(bush, x, 900, true, true);
				game.getItems().add(topBush21);
			}
			if (x < 640 && x > 450 || (x < 150 && x > -200) || (x < -1450 && x > -2025)) {
				Item topBush22 = new Item(bush, x, 950, true, true);
				game.getItems().add(topBush22);
			}
			if (x < 550 && x > 450 || (x < 150 && x > -200) || (x < -950 && x > -1600)) {
				Item topBush23 = new Item(bush, x, 1000, true, true);
				game.getItems().add(topBush23);
			}
			if (x < 550 && x > 0 || (x < -950 && x > -1600)) {
				Item topBush24 = new Item(bush, x, 1050, true, true);
				game.getItems().add(topBush24);
			}
			if (x < 300 && x > 0 || (x < -950 && x > -1150)) {
				Item topBush25 = new Item(bush, x, 1100, true, true);
				game.getItems().add(topBush25);
			}
			if (x < 300 && x > 0 || (x < -950 && x > -1150)) {
				Item topBush26 = new Item(bush, x, 1150, true, true);
				game.getItems().add(topBush26);
			}
		} // for
		for (int x = -2600; x < 1400; x += 76) {
			if (x > 336 || x < -1184) {
				Item bottom5 = new Item(tree, x, 1100, 76, 100, false);
				game.getItems().add(bottom5);
			}
			Item bottom6 = new Item(tree, x, 1200, 76, 100, false);
			game.getItems().add(bottom6);
			if (x > 564 || x < -1640) {
				Item bottom = new Item(tree, x, 1000, 76, 100, false);
				game.getItems().add(bottom);
			}
			if (x > 640 || (x < -196 && x > -424) || x < -2064 || x < -1000 && x > -1500) {
				Item bottom1 = new Item(tree, x, 900, 76, 100, false);
				game.getItems().add(bottom1);
			}

			if (x > 184 || (x < -652 && x > -880) || x < -2372) {
				Item top2 = new Item(tree, x, 100, 76, 100, false);
				game.getItems().add(top2);
			}
			if ((x > 1020 || x < 792) && x > 184 || (x < -652 && x > -880) || (x < -1400 && x > -1500)) {
				Item top3 = new Item(tree, x, 200, 76, 100, false);
				game.getItems().add(top3);
			}
			if ((x < 564 && x > 184) || (x > -196 && x < -44) || (x < -652 && x > -880) || (x < -1300 && x > -1600)) {
				Item top4 = new Item(tree, x, 300, 76, 100, false);
				game.getItems().add(top4);
			}
			if ((x > -196 && x < -44) || (x < -1200 && x > -1700)) {
				Item top7 = new Item(tree, x, 400, 76, 100, false);
				game.getItems().add(top7);
			}
			if ((x > -196 && x < -44) || (x < -348 && x > -750) || (x < -1000 && x > -1900)) {
				Item top8 = new Item(tree, x, 500, 76, 100, false);
				game.getItems().add(top8);
			}
			if (x < -1000 && x > -1200 || (x < -1400 && x > -1700)) {
				Item top9 = new Item(tree, x, 600, 76, 100, false);
				game.getItems().add(top9);
			}
			if (x < -196 && x > -424 || x > 944) {
				Item top9 = new Item(tree, x, 700, 76, 100, false);
				game.getItems().add(top9);
			}
			if (x < -196 && x > -424 || x < -1300 && x > -1500 || x > 944 || x < -2064) {
				Item top9 = new Item(tree, x, 800, 76, 100, false);
				game.getItems().add(top9);
			}
			if (x > 868 || x < -348) {
				Item top5 = new Item(tree, x, -100, 76, 100, false);
				game.getItems().add(top5);
				Item top = new Item(tree, x, 0, 76, 100, false);
				game.getItems().add(top);
			}
			Item top6 = new Item(tree, x, -200, 76, 100, false);
			game.getItems().add(top6);

		}
		game.getItems().add(new Item(getImage("assets/sign2.png"), 1100, 300, 48, 48, 6));
		game.setSignMessage("A road that begins a journey.\nThe road smells like fresh grass.");
		game.getItems().add(new Item(1340, 200, 30, 500, 43));

	}

	public void addMainStage() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 121, 69, "stages1.txt", 0, 0));
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("assets/tree1.png");
		Image image2 = tk.getImage(url);
		this.image = image2;
		game.setSignMessage("New Bark Town, \nWinds of a New Beginning.");

		// or (int y = -1000; y < 1080; y+= 100) {
		// int x = 200;
		// Item top = new Item(image, x, y, 76, 100, false);

		// game.getItems().add(top);
		// }

		for (int x = 0; x < 1920; x += 80) {
			Item top = new Item(image, x, 0, 76, 100, false);
			game.getItems().add(top);
		}
		for (int x = 0; x < 1920; x += 80) {
			Item bottom = new Item(image, x, 960, 76, 100, false);
			game.getItems().add(bottom);
			if (x < 1000) {
				if (x != 400 && x != 480 && x != 560) {
					Item left = new Item(image, 0, x, 76, 100, false);
					game.getItems().add(left);
				}
				Item right = new Item(image, 1840, x, 76, 100, false);
				game.getItems().add(right);
			}
		}

		game.getItems().add(new Item(0, 400, 40, 260, 5));

		url = Stages.class.getResource("assets/sign2.png");
		Image sign = tk.getImage(url);
		Item sign1 = new Item(sign, 300, 400, 48, 48, 6);
		game.getItems().add(sign1);

		url = Stages.class.getResource("assets/flower1.png");
		Image flower = tk.getImage(url);

		for (int y = 1; y < 3; y++) {
			for (int x = 0; x < 4; x++) {
				Item flower1 = new Item(flower, 500 + (x * 40), 600 + (y * 50), true);
				game.getItems().add(flower1);
			}
		}

		game.getItems().add(new Item(1350, 825, 30, 44, 13));

		url = Game.class.getResource("assets/house.png");
		Image house = tk.getImage(url);
		for (int x = 0; x < 2; x++) {
			Item house1 = new Item(house, 550 + (x * 600), 200, 225, 201, false);
			game.getItems().add(house1);
		}

		game.getItems().add(new Item(1200, 400, 40, 40, 41));
		url = Game.class.getResource("assets/fence.png");
		Image fence = tk.getImage(url);
		for (int x = 0; x < 4; x++) {
			Item fence1 = new Item(fence, 700 + (x * 24), 600, 24, 42, false);
			game.getItems().add(fence1);
		}

		url = Stages.class.getResource("assets/lab.png");
		Image labImage = tk.getImage(url);
		Item lab = new Item(labImage, 1200, 600, 291, 216, false);
		game.getItems().add(lab);
		url = Stages.class.getResource("assets/lakeTown.png");
		Image lake = tk.getImage(url);
		Item lake1 = new Item(lake, 500, 750, 194, 212, false);
		game.getItems().add(lake1);

		game.getPlayer().setX(960);
		game.getPlayer().setY(540);

		game.setTimer(System.currentTimeMillis());
		
		game.stopClip();
		game.play("assets/Music/newBarkTown.wav");
	}

	public void addCherryGroveCity() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 121, 69, "cherryGroveCity.txt", 0, 0));
		game.stopClip();
		game.play("assets/Music/cherryGroveCity.wav");

		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("assets/pokeCenter.png");
		Image healCenter = tk.getImage(url);

		url = Stages.class.getResource("assets/flower1.png");
		Image shop = tk.getImage(url);
		Item pc = new Item(shop, 600, 600, 30, 44, 10);
		Image flower2 = getImage("assets/flower2.png");
		Image rose = getImage("assets/rose.png");

		game.setTeleportLocationHeal(31);
		game.setTeleportLocationShop(33);

		game.getItems().add(new Item(getImage("assets/cherryGroveCityHouse.png"), 1400, 600, 158, 216, false));
		game.getItems().add(new Item(getImage("assets/cherryGroveCityHouse.png"), 700, 400, 158, 216, false));
		game.getItems().add(new Item(getImage("assets/cherryGroveCityHouse2.png"), 1100, 500, 158, 206, false));
		game.getItems().add(new Item(getImage("assets/cherryGroveCityMart.png"), 1300, 200, 132, 178, false));
		game.getItems().add(new Item(healCenter, 1500, 200, 160, 152, false));
		game.getItems().add(new Item(1565, 350, 30, 20, 9));
		game.getItems().add(new Item(1325, 360, 40, 30, 32));
		for (int x = 805; x < 1055; x += 50) {
			game.getItems().add(new Item(flower2, x, 720, 50, 30, true));
		}
		for (int x = 805; x < 1055; x += 50) {
			game.getItems().add(new Item(rose, x, 770, 50, 30, true));
		}
		for (int x = 805; x < 1055; x += 50) {
			game.getItems().add(new Item(flower2, x, 830, 50, 30, true));
		}
		for (int x = 805; x < 1055; x += 50) {
			game.getItems().add(new Item(rose, x, 870, 50, 30, true));
		}

		for (int x = 400; x < 1920; x += 81) {
			game.getItems().add(new Item(getImage("assets/tree2.png"), x, 1000, 76, 98, false));
		}
		for (int x = 400; x < 1920; x += 81) {
			game.getItems().add(new Item(getImage("assets/tree2.png"), x, 900, 76, 98, false));
		}
		for (int x = 400; x < 1920; x += 81) {
			if (x <= 742 || x >= 1596) {
				game.getItems().add(new Item(getImage("assets/tree2.png"), x, 700, 76, 98, false));
			}
		}
		for (int x = 400; x < 1920; x += 81) {
			if (x <= 742 || x >= 1596) {
				game.getItems().add(new Item(getImage("assets/tree2.png"), x, 800, 76, 98, false));
			}
		}
		for (int x = 0; x < 1920; x += 81) {
			if (x >= 729 && x <= 1053) {
				continue;
			}
			game.getItems().add(new Item(getImage("assets/tree2.png"), x, 0, 76, 98, false));
		}
		for (int x = 0; x < 1920; x += 81) {
			if (x >= 729 && x <= 1053) {
				continue;
			}
			game.getItems().add(new Item(getImage("assets/tree2.png"), x, 100, 76, 98, false));
		}

		for (int x = 1695; x < 1920; x += 81) {
			game.getItems().add(new Item(getImage("assets/tree2.png"), x, 200, 76, 98, false));
		}

		for (int x = 1150; x < 1300; x += 50) {
			game.getItems().add(new Item(getImage("assets/flower3.png"), x, 200, 48, 28, true));
		}
		for (int x = 1150; x < 1300; x += 50) {
			game.getItems().add(new Item(getImage("assets/flower3.png"), x, 230, 48, 28, true));
		}

		game.getItems().add(new Item(730, 0, 400, 40, 20));
		game.getItems().add(new Item(1900, 300, 40, 400, 21));

		game.getItems().add(new Item(getImage("assets/lake.png"), 0, 652, 179, 452, false));
		game.getItems().add(new Item(getImage("assets/lake.png"), 179, 652, 179, 452, false));
		game.getItems().add(new Item(getImage("assets/lake.png"), 0, 200, 179, 452, false));
		game.getItems().add(new Item(getImage("assets/lake.png"), 179, 200, 179, 452, false));

		game.getItems().add(new Item(getImage("assets/sign2.png"), 600, 200, 48, 48, 6));
		game.setSignMessage("A city where you can smell \nsmall flowers and a sea breeze.");

		game.getPlayer().setX(960);
		game.getPlayer().setY(540);
		// TextBox.initiateText("sup nigga \npress space to continue lmao");
		// TextBox.initiateText("kill yourself nigga \n");
	}

	public void addPokeCenter() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 32, 32, "pokeCenter.txt", 600, 180));
		game.getPlayer().setX(800);
		game.getPlayer().setY(600);

		for (int x = 0; x < game.getPokemonList().size(); x++) {
			game.getPokemonList().get(x).setHealth(game.getPokemonList().get(x).getMaxHealth());
		}

		game.getItems().add(new Item(getImage("assets/pokeCenterDesk.png"), 760, 180, 160, 130, false));
		game.getItems().add(new Item(getImage("assets/pc.png"), 1064, 180, 48, 108, false));
		game.getItems().add(new Item(getImage("assets/pc.png"), 1064, 180, 48, 128, 12));

		game.getItems().add(new Item(getImage("assets/nurse.png"), 825, 200, 44, 150, 11));

		game.getItems().add(new Item(getImage("assets/carpet.png"), 800, 660, 64, 32, game.getTeleportLocationHeal()));
		game.getItems().add(new Item(getImage("assets/pokeCenterTable.png"), 950, 500, 96, 108, false));

		game.getItems().add(new Item(getImage("assets/pokeCenterCushionYellow.png"), 1050, 525, 42, 33, true));
		game.getItems().add(new Item(getImage("assets/pokeCenterCushionBlue.png"), 1050, 575, 42, 33, true));

		game.getItems().add(new Item(getImage("assets/pokeCenterCushionYellow.png"), 900, 575, 42, 33, true));
		game.getItems().add(new Item(getImage("assets/pokeCenterCushionBlue.png"), 900, 525, 42, 33, true));

		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 180, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 622, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 1080, 622, 32, 70, false));

		game.getItems().add(new Item(getImage("assets/pokeBallHolder.png"), 687, 180, 64, 88, false));

	}

	public void addPokeShop() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 32, 32, "elite2.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/pokeAisle2.png"), 600, 180, 192, 87, false));

		game.getItems().add(new Item(getImage("assets/pokeAisle.png"), 600, 370, 144, 78, false));
		game.getItems().add(new Item(getImage("assets/pokeAisleSide.png"), 1064, 180, 48, 156, false));
		game.getItems().add(new Item(getImage("assets/pokeAisleSide.png"), 1064, 336, 48, 156, false));
		game.getItems().add(new Item(getImage("assets/pokeAisleSide.png"), 1064, 492, 48, 156, false));

		game.getItems().add(new Item(getImage("assets/pokeVendor.png"), 910, 200, 42, 54, false));
		game.getItems().add(new Item(getImage("assets/pokeCounter.png"), 800, 180, 240, 122, false));
		game.getItems().add(new Item(910, 200, 42, 130, 22));
		game.getItems()
				.add(new Item(getImage("assets/pokeCarpet.png"), 850, 660, 78, 48, game.getTeleportLocationShop()));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 622, 32, 70, false));

		game.getItems().add(new Item(getImage("assets/pokeCenterTable.png"), 650, 510, 96, 108, false));

		game.getItems().add(new Item(getImage("assets/pokeCenterCushionYellow.png"), 750, 535, 42, 33, true));
		game.getItems().add(new Item(getImage("assets/pokeCenterCushionBlue.png"), 750, 585, 42, 33, true));

		game.getItems().add(new Item(getImage("assets/pokeCenterCushionYellow.png"), 600, 585, 42, 33, true));
		game.getItems().add(new Item(getImage("assets/pokeCenterCushionBlue.png"), 600, 535, 42, 33, true));

	}

	public void addFinalTown() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 100, 150, "finaltown.txt", 740, -1670));
		game.stopClip();
		game.play("assets/Music/finalCity.wav");
		// Game.clip.stop();
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("assets/pokeCenter.png");
		Image pokeCenter = tk.getImage(url);
		url = Stages.class.getResource("assets/pokeShop.png");
		Image pokeShop = tk.getImage(url);
		url = Stages.class.getResource("assets/lake.png");
		Image lake = tk.getImage(url);
		url = Stages.class.getResource("assets/tree2.png");
		Image tree = tk.getImage(url);
		url = Stages.class.getResource("assets/gym.png");
		Image gym = tk.getImage(url);
		url = Stages.class.getResource("assets/house1.png");
		Image house1 = tk.getImage(url);
		url = Stages.class.getResource("assets/gym1.png");
		Image gym2 = tk.getImage(url);
		url = Stages.class.getResource("assets/bridge.png");
		Image bridge = tk.getImage(url);
		Item lake1 = new Item(lake, 1709, -1125, 179, 452, false);
		game.getItems().add(lake1);
		Item lake2 = new Item(lake, 1351, -1125, 179, 452, false);
		game.getItems().add(lake2);
		Item gym1 = new Item(gym, 1533, -1400, 145, 158, false);
		game.getItems().add(gym1);

		game.getItems().add(new Item(1600, -1240, 50, 40, 40));
		Item gym3 = new Item(gym2, 885, -760, 280, 200, false);
		game.getItems().add(gym3);

		game.setTeleportLocationShop(38);
		game.setTeleportLocationHeal(37);

		Item pokeCenter1 = new Item(pokeCenter, 1300, -575, 160, 152, false);
		game.getItems().add(pokeCenter1);

		game.getItems().add(new Item(1365, -420, 40, 40, 9));
		Item pokeShop1 = new Item(pokeShop, 1700, 230, 128, 138, false);
		game.getItems().add(new Item(1725, 370, 40, 40, 32));
		game.getItems().add(pokeShop1);
		Item house = new Item(house1, 1475, -220, 128, 142, false);
		game.getItems().add(house);
		Item house2 = new Item(house1, 2045, -635, 128, 142, false);
		game.getItems().add(house2);
		Item bridge1 = new Item(bridge, 1523, -1170, 0, 0, true);
		game.getItems().add(bridge1);
		for (int x = 740; x < 2300; x += 80) {
			if (x != 900 && x != 980) {
				Item bottom = new Item(tree, x, 625, 76, 100, false);
				game.getItems().add(bottom);
			}
			if (x != 900 && x != 980) {
				Item bottom1 = new Item(tree, x, 525, 76, 100, false);
				game.getItems().add(bottom1);
			}
			if (x < 900 || (x > 1250 && x < 1700) || x > 2000) {
				Item bottom2 = new Item(tree, x, 425, 76, 100, false);
				game.getItems().add(bottom2);
			}
			if (x < 900 || (x > 1500 && x < 1700) || x > 2200) {
				Item bottom3 = new Item(tree, x, 325, 76, 100, false);
				game.getItems().add(bottom3);
			}
			if (x < 800 || (x > 1500 && x < 1700) || x > 2200) {
				Item bottom4 = new Item(tree, x, 225, 76, 100, false);
				game.getItems().add(bottom4);
			}
			if (x < 800 || (x > 1400 && x < 1750) || x > 2200) {
				Item bottom5 = new Item(tree, x, 125, 76, 100, false);
				game.getItems().add(bottom5);
			}
			if (x < 800 || (x > 1400 && x < 1750) || x > 2200) {
				Item bottom6 = new Item(tree, x, 25, 76, 100, false);
				game.getItems().add(bottom6);
			}
			if (x < 800 || (x > 1400 && x < 1750) || x > 2200) {
				Item bottom7 = new Item(tree, x, -75, 76, 100, false);
				game.getItems().add(bottom7);
			}
			if (x < 800 || (x > 1550 && x < 1750) || x > 2200) {
				Item bottom8 = new Item(tree, x, -175, 76, 100, false);
				game.getItems().add(bottom8);
			}
			if (x < 800 || x > 2000) {
				Item bottom9 = new Item(tree, x, -275, 76, 100, false);
				game.getItems().add(bottom9);
			}
			if (x < 800 || x > 2000) {
				Item bottom10 = new Item(tree, x, -375, 76, 100, false);
				game.getItems().add(bottom10);
			}
			if (x < 800 || x > 2000) {
				Item bottom11 = new Item(tree, x, -475, 76, 100, false);
				game.getItems().add(bottom11);
			}
			if (x < 800 || x > 2100) {
				Item bottom12 = new Item(tree, x, -575, 76, 100, false);
				game.getItems().add(bottom12);
			}
			if (x < 800 || x > 1200 && x < 1500 || x > 1650 && x < 1950 || x > 2100) {
				Item bottom13 = new Item(tree, x, -675, 76, 100, false);
				game.getItems().add(bottom13);
			}
			if (x < 800 || (x > 1200 && x < 1300) || x > 1900) {
				Item bottom14 = new Item(tree, x, -775, 76, 100, false);
				game.getItems().add(bottom14);
			}
			if (x < 1300 || x > 1900) {
				Item bottom15 = new Item(tree, x, -875, 76, 100, false);
				game.getItems().add(bottom15);
			}
			if (x < 1300 || x > 1900) {
				Item bottom16 = new Item(tree, x, -975, 76, 100, false);
				game.getItems().add(bottom16);
			}
			if (x < 1300 || x > 1900) {
				Item bottom17 = new Item(tree, x, -1075, 76, 100, false);
				game.getItems().add(bottom17);
			}
			if (x < 1300 || x > 1900) {
				Item bottom18 = new Item(tree, x, -1175, 76, 100, false);
				game.getItems().add(bottom18);
			}
			if (x < 1500 || x > 1650) {
				Item bottom19 = new Item(tree, x, -1275, 76, 100, false);
				game.getItems().add(bottom19);
			}
			if (x < 1500 || x > 1650) {
				Item bottom20 = new Item(tree, x, -1375, 76, 100, false);
				game.getItems().add(bottom20);
			}
			if (x < 1500 || x > 1650) {
				Item bottom21 = new Item(tree, x, -1475, 76, 100, false);
				game.getItems().add(bottom21);
			}

			Item bottom22 = new Item(tree, x, -1575, 76, 100, false);
			game.getItems().add(bottom22);
			Item bottom23 = new Item(tree, x, -1675, 76, 100, false);
			game.getItems().add(bottom23);

		}
		game.getPlayer().setX(960);
		game.getPlayer().setY(540);

		game.getItems().add(new Item(800, 640, 300, 40, 34));

		game.getItems().add(new Item(getImage("assets/sign2.png"), 870, 200, 48, 48, 6));
		game.setSignMessage("A nostalgic village surrounded by \ntrees and other scenery.");

	}

	public void addElite4PreRoom() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 48, 48, "scienceLab.txt", 600, 180));

		game.getPlayer().setX(980);
		game.getPlayer().setY(700);

		game.getItems().add(new Item(getImage("assets/carpet.png"), 975, 930, 64, 32, 39));

		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 1335, 180, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 180, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 878, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 1335, 878, 32, 70, false));

		game.getItems().add(new Item(getImage("assets/sprites/elite4/guard.png"), 1050, 180, 38, 54, false));
		game.getItems().add(new Item(1050, 180, 38, 94, 25));
		game.getItems().add(new Item(getImage("assets/doorway.png"), 980, 180, 64, 42, 30));
	}

	public void addStage30() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.setTileMap(new TileMap(game, 115, 282, "assets/route30.txt", 400, -3700));

		game.stopClip();
		game.play("assets/Music/route30.wav");
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("assets/tree1.png");
		Image tree = tk.getImage(url);
		url = Stages.class.getResource("assets/bush.png");
		Image bush = tk.getImage(url);
		url = Stages.class.getResource("assets/lakeTown.png");
		Image lake = tk.getImage(url);
		url = Stages.class.getResource("assets/lake.png");
		Image lake1 = tk.getImage(url);
		Item halfLake = new Item(lake, 1935, 600, 194, 212, false);
		game.getItems().add(halfLake);
		Item fullLake = new Item(lake1, 1500, -660, 179, 452, false);
		game.getItems().add(fullLake);
		for (int x = 400; x < 2200; x += 46) {
			if (x > 1050 && x < 1575) {
				Item bushBottom = new Item(bush, x, 450, true, true);
				game.getItems().add(bushBottom);
			}
			if (x > 1050 && x < 1575) {
				Item bushBottom1 = new Item(bush, x, 400, true, true);
				game.getItems().add(bushBottom1);
			}
			if (x > 1050 && x < 1575) {
				Item bushBottom2 = new Item(bush, x, 350, true, true);
				game.getItems().add(bushBottom2);
			}
			if (x > 1300 && x < 1575) {
				Item bushBottom3 = new Item(bush, x, 300, true, true);
				game.getItems().add(bushBottom3);
			}
			if (x > 1300 && x < 1675) {
				Item bushBottom4 = new Item(bush, x, 250, true, true);
				game.getItems().add(bushBottom4);
			}
			if (x > 1300 && x < 1625) {
				Item bushBottom5 = new Item(bush, x, 200, true, true);
				game.getItems().add(bushBottom5);
			}
			if (x > 1300 && x < 1625) {
				Item bushBottom6 = new Item(bush, x, 150, true, true);
				game.getItems().add(bushBottom6);
			}
			if (x > 1250 && x < 1550) {
				Item bushBottom7 = new Item(bush, x, 100, true, true);
				game.getItems().add(bushBottom7);
			}
			if (x > 1300 && x < 1450) {
				Item bushBottom8 = new Item(bush, x, -550, true, true);
				game.getItems().add(bushBottom8);
			}
			if (x > 1300 && x < 1450) {
				Item bushBottom9 = new Item(bush, x, -600, true, true);
				game.getItems().add(bushBottom9);
			}
			if (x > 1300 && x < 1450) {
				Item bushBottom10 = new Item(bush, x, -650, true, true);
				game.getItems().add(bushBottom10);
			}
			if (x > 1300 && x < 1550) {
				Item bushBottom11 = new Item(bush, x, -700, true, true);
				game.getItems().add(bushBottom11);
			}
			if (x > 1300 && x < 1550) {
				Item bushBottom12 = new Item(bush, x, -750, true, true);
				game.getItems().add(bushBottom12);
			}
			if (x > 1300 && x < 1550) {
				Item bushBottom13 = new Item(bush, x, -800, true, true);
				game.getItems().add(bushBottom13);
			}
			if (x > 1300 && x < 1550) {
				Item bushBottom14 = new Item(bush, x, -850, true, true);
				game.getItems().add(bushBottom14);
			}
			if (x > 1300 && x < 1550) {
				Item bushBottom15 = new Item(bush, x, -900, true, true);
				game.getItems().add(bushBottom15);
			}
			if (x > 1100 && x < 1550) {
				Item bushBottom16 = new Item(bush, x, -950, true, true);
				game.getItems().add(bushBottom16);
			}
			if (x > 1100 && x < 1550) {
				Item bushBottom16 = new Item(bush, x, -1000, true, true);
				game.getItems().add(bushBottom16);
			}
			if (x > 1100 && x < 1575) {
				Item bushBottom17 = new Item(bush, x, -1050, true, true);
				game.getItems().add(bushBottom17);
			}
			if (x > 1100 && x < 1575) {
				Item bushBottom18 = new Item(bush, x, -1100, true, true);
				game.getItems().add(bushBottom18);
			}
			if (x > 1350 && x < 1575) {
				Item bushBottom19 = new Item(bush, x, -1150, true, true);
				game.getItems().add(bushBottom19);
			}
			if (x > 1350 && x < 1575) {
				Item bushBottom20 = new Item(bush, x, -1200, true, true);
				game.getItems().add(bushBottom20);
			}
			if (x > 1350 && x < 1575) {
				Item bushBottom21 = new Item(bush, x, -1250, true, true);
				game.getItems().add(bushBottom21);
			}
			if (x > 1350 && x < 1575) {
				Item bushBottom22 = new Item(bush, x, -1300, true, true);
				game.getItems().add(bushBottom22);
			}
			if (x > 1350 && x < 1575) {
				Item bushBottom23 = new Item(bush, x, -1350, true, true);
				game.getItems().add(bushBottom23);
			}
			if (x > 1000 && x < 1200) {
				Item bushBottom24 = new Item(bush, x, -1650, true, true);
				game.getItems().add(bushBottom24);
			}
			if (x > 1000 && x < 1200) {
				Item bushBottom25 = new Item(bush, x, -1700, true, true);
				game.getItems().add(bushBottom25);
			}
			if (x > 1000 && x < 1100) {
				Item bushBottom26 = new Item(bush, x, -1750, true, true);
				game.getItems().add(bushBottom26);
			}
			if (x > 1000 && x < 1100) {
				Item bushBottom27 = new Item(bush, x, -1800, true, true);
				game.getItems().add(bushBottom27);
			}
			if (x > 1400 && x < 1700) {
				Item bushBottom28 = new Item(bush, x, -2050, true, true);
				game.getItems().add(bushBottom28);
			}
			if (x > 1400 && x < 1700) {
				Item bushBottom29 = new Item(bush, x, -2100, true, true);
				game.getItems().add(bushBottom29);
			}
			if (x > 1400 && x < 1700) {
				Item bushBottom30 = new Item(bush, x, -2150, true, true);
				game.getItems().add(bushBottom30);
			}
			if (x > 1400 && x < 1700) {
				Item bushBottom31 = new Item(bush, x, -2200, true, true);
				game.getItems().add(bushBottom31);
			}
			if (x > 1050 && x < 1350) {
				Item bushBottom32 = new Item(bush, x, -2400, true, true);
				game.getItems().add(bushBottom32);
			}
			if (x > 1050 && x < 1350) {
				Item bushBottom33 = new Item(bush, x, -2450, true, true);
				game.getItems().add(bushBottom33);
			}
			if (x > 1050 && x < 1350) {
				Item bushBottom34 = new Item(bush, x, -2500, true, true);
				game.getItems().add(bushBottom34);
			}
			if (x > 1050 && x < 1350 || (x > 1600 && x < 1900)) {
				Item bushBottom35 = new Item(bush, x, -2550, true, true);
				game.getItems().add(bushBottom35);
			}
			if (x > 1050 && x < 1350 || (x > 1600 && x < 1900)) {
				Item bushBottom36 = new Item(bush, x, -2600, true, true);
				game.getItems().add(bushBottom36);
			}
			if (x > 1050 && x < 1350 || (x > 1600 && x < 1900)) {
				Item bushBottom37 = new Item(bush, x, -2650, true, true);
				game.getItems().add(bushBottom37);
			}
			if (x > 1250 && x < 1350 || (x > 1600 && x < 1900)) {
				Item bushBottom38 = new Item(bush, x, -2700, true, true);
				game.getItems().add(bushBottom38);
			}
			if (x > 1600 && x < 1900) {
				Item bushBottom39 = new Item(bush, x, -2750, true, true);
				game.getItems().add(bushBottom39);
			}
			if (x > 1600 && x < 1900) {
				Item bushBottom40 = new Item(bush, x, -2800, true, true);
				game.getItems().add(bushBottom40);
			}
			if (x > 600 && x < 800 || (x > 1750 && x < 1900)) {
				Item bushBottom41 = new Item(bush, x, -2850, true, true);
				game.getItems().add(bushBottom41);
			}
			if (x > 600 && x < 800 || (x > 1750 && x < 1900)) {
				Item bushBottom42 = new Item(bush, x, -2900, true, true);
				game.getItems().add(bushBottom42);
			}
			if (x > 600 && x < 800 || (x > 1650 && x < 1900)) {
				Item bushBottom43 = new Item(bush, x, -2950, true, true);
				game.getItems().add(bushBottom43);
			}
			if (x > 600 && x < 800 || (x > 1650 && x < 1900)) {
				Item bushBottom44 = new Item(bush, x, -3000, true, true);
				game.getItems().add(bushBottom44);
			}
			if (x > 600 && x < 700 || (x > 1150 && x < 1250) || (x > 1600 && x < 1900)) {
				Item bushBottom45 = new Item(bush, x, -3050, true, true);
				game.getItems().add(bushBottom45);
			}
			if (x > 600 && x < 700 || (x > 1150 && x < 1350) || (x > 1600 && x < 1900)) {
				Item bushBottom46 = new Item(bush, x, -3100, true, true);
				game.getItems().add(bushBottom46);
			}
			if (x > 600 && x < 700 || (x > 1150 && x < 1350) || (x > 1500 && x < 1900)) {
				Item bushBottom47 = new Item(bush, x, -3150, true, true);
				game.getItems().add(bushBottom47);
			}
			if (x > 600 && x < 700 || (x > 1150 && x < 1350) || (x > 1500 && x < 1900)) {
				Item bushBottom48 = new Item(bush, x, -3200, true, true);
				game.getItems().add(bushBottom48);
			}
			if (x > 600 && x < 700 || (x > 1500 && x < 1800)) {
				Item bushBottom49 = new Item(bush, x, -3250, true, true);
				game.getItems().add(bushBottom49);
			}
			if (x > 600 && x < 700 || (x > 1500 && x < 1800)) {
				Item bushBottom50 = new Item(bush, x, -3300, true, true);
				game.getItems().add(bushBottom50);
			}
			if (x > 600 && x < 750) {
				Item bushBottom51 = new Item(bush, x, -3350, true, true);
				game.getItems().add(bushBottom51);
			}
			if (x > 600 && x < 750) {
				Item bushBottom52 = new Item(bush, x, -3400, true, true);
				game.getItems().add(bushBottom52);
			}
			if (x > 700 && x < 850) {
				Item bushBottom53 = new Item(bush, x, -3450, true, true);
				game.getItems().add(bushBottom53);
			}
			if (x > 700 && x < 850) {
				Item bushBottom54 = new Item(bush, x, -3500, true, true);
				game.getItems().add(bushBottom54);
			}

		}

		for (int x = 400; x < 2200; x += 76) {

			if (x < 800 || (x > 1050 && x < 1900) || (x > 2100 && x < 2200)) {
				Item treeBottom = new Item(tree, x, 700, 76, 100, false);
				game.getItems().add(treeBottom);
			}
			if (x < 800 || (x > 1050 && x < 1900) || (x > 2100 && x < 2200)) {
				Item treeBottom1 = new Item(tree, x, 600, 76, 100, false);
				game.getItems().add(treeBottom1);
			}
			if (x < 750 || x > 1050) {
				Item treeBottom2 = new Item(tree, x, 500, 76, 100, false);
				game.getItems().add(treeBottom2);
			}
			if (x < 700 || (x > 1600 && x < 1650) || (x > 1700)) {
				Item treeBottom3 = new Item(tree, x, 400, 76, 100, false);
				game.getItems().add(treeBottom3);
			}
			if (x < 600 || (x > 1600 && x < 1650) || (x > 1700)) {
				Item treeBottom4 = new Item(tree, x, 300, 76, 100, false);
				game.getItems().add(treeBottom4);
			}
			if (x < 550 || (x > 1700)) {
				Item treeBottom5 = new Item(tree, x, 200, 76, 100, false);
				game.getItems().add(treeBottom5);
			}
			if (x < 550 || (x > 1700)) {
				Item treeBottom6 = new Item(tree, x, 100, 76, 100, false);
				game.getItems().add(treeBottom6);
			}
			if (x < 550 || (x > 1600)) {
				Item treeBottom7 = new Item(tree, x, 0, 76, 100, false);
				game.getItems().add(treeBottom7);
			}
			if (x < 600 || (x > 1400)) {
				Item treeBottom8 = new Item(tree, x, -100, 76, 100, false);
				game.getItems().add(treeBottom8);
			}
			if (x < 600 || (x > 1400)) {
				Item treeBottom8 = new Item(tree, x, -200, 76, 100, false);
				game.getItems().add(treeBottom8);
			}
			if (x < 600 || (x > 1650)) {
				Item treeBottom9 = new Item(tree, x, -300, 76, 100, false);
				game.getItems().add(treeBottom9);
			}
			if (x < 600 || (x > 1650)) {
				Item treeBottom10 = new Item(tree, x, -400, 76, 100, false);
				game.getItems().add(treeBottom10);
			}
			if (x < 600 || (x > 1650)) {
				Item treeBottom11 = new Item(tree, x, -500, 76, 100, false);
				game.getItems().add(treeBottom11);
			}
			if (x < 600 || (x > 1000 && x < 1300) || (x > 1650)) {
				Item treeBottom12 = new Item(tree, x, -600, 76, 100, false);
				game.getItems().add(treeBottom12);
			}
			if (x < 600 || (x > 1000 && x < 1300) || (x > 1650)) {
				Item treeBottom13 = new Item(tree, x, -700, 76, 100, false);
				game.getItems().add(treeBottom13);
			}
			if (x < 600 || (x > 1000 && x < 1300) || (x > 1525)) {
				Item treeBottom14 = new Item(tree, x, -800, 76, 100, false);
				game.getItems().add(treeBottom14);
			}
			if (x < 600 || (x > 1000 && x < 1300) || (x > 1525)) {
				Item treeBottom15 = new Item(tree, x, -900, 76, 100, false);
				game.getItems().add(treeBottom15);
			}
			if (x < 600 || (x > 1000 && x < 1050) || (x > 1525)) {
				Item treeBottom16 = new Item(tree, x, -1000, 76, 100, false);
				game.getItems().add(treeBottom16);
			}
			if (x < 600 || (x > 1000 && x < 1050) || (x > 1600)) {
				Item treeBottom17 = new Item(tree, x, -1100, 76, 100, false);
				game.getItems().add(treeBottom17);
			}
			if (x < 600 || (x > 1600)) {
				Item treeBottom18 = new Item(tree, x, -1200, 76, 100, false);
				game.getItems().add(treeBottom18);
			}
			if (x < 600 || (x > 1600)) {
				Item treeBottom19 = new Item(tree, x, -1300, 76, 100, false);
				game.getItems().add(treeBottom19);
			}
			if (x < 600 || (x > 1700)) {
				Item treeBottom20 = new Item(tree, x, -1400, 76, 100, false);
				game.getItems().add(treeBottom20);
			}
			if (x < 600 || (x > 1700)) {
				Item treeBottom20 = new Item(tree, x, -1500, 76, 100, false);
				game.getItems().add(treeBottom20);
			}
			if (x < 600 || (x > 1700)) {
				Item treeBottom21 = new Item(tree, x, -1600, 76, 100, false);
				game.getItems().add(treeBottom21);
			}
			if (x < 700 || (x > 1200 && x < 1300) || (x > 1700)) {
				Item treeBottom21 = new Item(tree, x, -1700, 76, 100, false);
				game.getItems().add(treeBottom21);
			}
			if (x < 700 || (x > 1100 && x < 1300) || (x > 1700)) {
				Item treeBottom21 = new Item(tree, x, -1800, 76, 100, false);
				game.getItems().add(treeBottom21);
			}
			if (x < 700 || (x > 1050 && x < 1350) || (x > 1700)) {
				Item treeBottom22 = new Item(tree, x, -1900, 76, 100, false);
				game.getItems().add(treeBottom22);
			}
			if (x < 700 || (x > 1050 && x < 1500) || (x > 1700)) {
				Item treeBottom23 = new Item(tree, x, -2000, 76, 100, false);
				game.getItems().add(treeBottom23);
			}
			if (x < 700 || (x > 1050 && x < 1350) || (x > 1700)) {
				Item treeBottom25 = new Item(tree, x, -2100, 76, 100, false);
				game.getItems().add(treeBottom25);
			}
			if (x < 700 || (x > 1200 && x < 1300) || (x > 1700)) {
				Item treeBottom26 = new Item(tree, x, -2200, 76, 100, false);
				game.getItems().add(treeBottom26);
			}
			if (x < 700 || (x > 1700)) {
				Item treeBottom27 = new Item(tree, x, -2300, 76, 100, false);
				game.getItems().add(treeBottom27);
			}
			if (x < 700 || (x > 1350 && x < 1500) || (x > 1800)) {
				Item treeBottom28 = new Item(tree, x, -2400, 76, 100, false);
				game.getItems().add(treeBottom28);
			}
			if (x < 700 || (x > 1350 && x < 1500) || (x > 1800)) {
				Item treeBottom29 = new Item(tree, x, -2500, 76, 100, false);
				game.getItems().add(treeBottom29);
			}
			if (x < 700 || (x > 1350 && x < 1550) || (x > 1900)) {
				Item treeBottom30 = new Item(tree, x, -2600, 76, 100, false);
				game.getItems().add(treeBottom30);
			}
			if (x < 700 || (x > 1000 && x < 1050) || (x > 1350 && x < 1550) || (x > 1900)) {
				Item treeBottom31 = new Item(tree, x, -2700, 76, 100, false);
				game.getItems().add(treeBottom31);
			}
			if (x < 700 || (x > 1000 && x < 1050) || (x > 1450 && x < 1550) || (x > 1900)) {
				Item treeBottom32 = new Item(tree, x, -2800, 76, 100, false);
				game.getItems().add(treeBottom32);
			}
			if (x < 600 || (x > 1000 && x < 1150) || (x > 1450 && x < 1700) || (x > 1900)) {
				Item treeBottom33 = new Item(tree, x, -2900, 76, 100, false);
				game.getItems().add(treeBottom33);
			}
			if (x < 600 || (x > 1000 && x < 1150) || (x > 1450 && x < 1650) || (x > 1900)) {
				Item treeBottom34 = new Item(tree, x, -3000, 76, 100, false);
				game.getItems().add(treeBottom34);
			}
			if (x < 600 || (x > 1000 && x < 1150) || (x > 1450 && x < 1550) || (x > 1900)) {
				Item treeBottom35 = new Item(tree, x, -3100, 76, 100, false);
				game.getItems().add(treeBottom35);
			}
			if (x < 600 || (x > 1000 && x < 1150) || (x > 1900)) {
				Item treeBottom36 = new Item(tree, x, -3200, 76, 100, false);
				game.getItems().add(treeBottom36);
			}
			if (x < 600 || (x > 1000 && x < 1200) || (x > 1800)) {
				Item treeBottom37 = new Item(tree, x, -3300, 76, 100, false);
				game.getItems().add(treeBottom37);
			}
			if (x < 600 || (x > 1000 && x < 1050) || (x > 1100 && x < 1200) || (x > 1800)) {
				Item treeBottom38 = new Item(tree, x, -3400, 76, 100, false);
				game.getItems().add(treeBottom38);
			}
			if (x < 700 || (x > 1800)) {
				Item treeBottom39 = new Item(tree, x, -3500, 76, 100, false);
				game.getItems().add(treeBottom39);
			}
			if (x < 800 || (x > 1000 && x < 1200) || (x > 1800)) {
				Item treeBottom40 = new Item(tree, x, -3600, 76, 100, false);
				game.getItems().add(treeBottom40);
			}
			if (x < 800 || (x > 1000)) {
				Item treeBottom41 = new Item(tree, x, -3700, 76, 100, false);
				game.getItems().add(treeBottom41);
			}
		}

		game.getItems().add(new Item(900, -3675, 200, 30, 35));
		game.getItems().add(new Item(900, 700, 200, 30, 36));

		game.getItems().add(new Item(getImage("assets/sign2.png"), 600, 200, 48, 48, 6));
		game.setSignMessage("A road that tests trainers \naiming for the top.");
	}

	public void addPlayerHouse() {
		game.setTileMap(new TileMap(game, 32, 32, "house.txt", 600, 180));
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("assets/flower1.png");
		Image flower = tk.getImage(url);
		url = Stages.class.getResource("assets/bed.png");
		Image bedImage = tk.getImage(url);
		url = Stages.class.getResource("assets/bookshelf.png");
		Image bookshelfImage = tk.getImage(url);
		url = Stages.class.getResource("assets/staircaseDown.png");
		Image stairCaseImage = tk.getImage(url);

		Item bed = new Item(bedImage, 1000, 500, 64, 132, false);
		game.getItems().add(bed);

		Item stairCase = new Item(stairCaseImage, 800, 180, 65, 48, 2);
		Item bookShelf = new Item(bookshelfImage, 600, 180, 54, 108, false);
		Item bookShelf2 = new Item(bookshelfImage, 654, 180, 54, 108, false);
		Item bookShelf3 = new Item(bookshelfImage, 708, 180, 54, 108, false);

		game.getItems().add(new Item(getImage("assets/closet.png"), 984, 180, 64, 128, false));
		game.getItems().add(new Item(getImage("assets/closet.png"), 1048, 180, 64, 128, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant.png"), 600, 590, 42, 100, false));
		game.getItems().add(new Item(getImage("assets/table2.png"), 700, 400, 80, 102, false));
		game.getItems().add(new Item(getImage("assets/pillow.png"), 785, 430, 48, 44, true));
		game.getItems().add(new Item(getImage("assets/pillow.png"), 650, 430, 48, 44, true));

		game.getItems().add(stairCase);
		game.getItems().add(bookShelf);
		game.getItems().add(bookShelf2);
		game.getItems().add(bookShelf3);

		game.getPlayer().setX(960);
		game.getPlayer().setY(540);
	}

	public void addHouse() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "house.txt", 600, 180));

		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Stages.class.getResource("assets/flower1.png");

		url = Stages.class.getResource("assets/tv.png");
		Image tvImage = tk.getImage(url);
		Item tv = new Item(tvImage, 1000, 180, 80, 104, false);
		game.getItems().add(tv);

		url = Stages.class.getResource("assets/kitchenTools.png");
		Image kitchenToolsImage = tk.getImage(url);

		game.getItems().add(new Item(getImage("assets/couch.png"), 600, 400, 64, 180, false));
		game.getItems().add(new Item(getImage("assets/table.png"), 700, 430, 186, 130, false));

		Item kitchenTools = new Item(kitchenToolsImage, 600, 180, 192, 80, false);
		game.getItems().add(kitchenTools);

		url = Stages.class.getResource("assets/carpet.png");
		Image carpetImage = tk.getImage(url);

		url = Stages.class.getResource("assets/staircase.png");
		Image stairCaseDownImage = tk.getImage(url);

		Item stairCaseDown = new Item(stairCaseDownImage, 800, 180, 65, 48, 3);
		game.getItems().add(stairCaseDown);

		Item carpet = new Item(carpetImage, 850, 660, 64, 32, 4);
		game.getItems().add(carpet);

		game.getPlayer().setX(960);
		game.getPlayer().setY(440);

	}

	public void addScienceLab() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "assets/scienceLab.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/pokeMachine.png"), 705, 180, 88, 114, false));
		game.getItems().add(new Item(getImage("assets/pokeLabWall.png"), 1048, 400, 64, 92, false));
		game.getItems().add(new Item(getImage("assets/pokeLabWall.png"), 984, 400, 64, 92, false));
		game.getItems().add(new Item(getImage("assets/pokeLabWall.png"), 920, 400, 64, 92, false));

		game.getItems().add(new Item(getImage("assets/pokeLabWall.png"), 600, 400, 64, 92, false));
		game.getItems().add(new Item(getImage("assets/pokeLabWall.png"), 664, 400, 64, 92, false));

		game.getItems().add(new Item(getImage("assets/pokeBallHolder.png"), 637, 180, 64, 88, false));

		game.getItems().add(new Item(getImage("assets/carpet.png"), 800, 660, 64, 32, 42));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 1080, 180, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 180, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 600, 622, 32, 70, false));
		game.getItems().add(new Item(getImage("assets/pottedPlant2.png"), 1080, 622, 32, 70, false));

		game.getItems().add(new Item(getImage("assets/desktop.png"), 900, 180, 60, 60, false));
		game.getItems().add(new Item(getImage("assets/dataStorage.png"), 965, 180, 30, 62, false));
		game.getItems().add(new Item(getImage("assets/dataStorage2.png"), 1000, 180, 30, 62, false));
		game.getItems().add(new Item(getImage("assets/professorRainey.png"), 825, 200, 46, 52, false));
		game.getItems().add(new Item(825, 200, 46, 94, 44));

	}

	public void addChampionRoom() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "assets/championRoom.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/sprites/elite4/champion.png"), 850, 400, 34, 54, false));
		game.getItems().add(new Item(getImage("assets/sprites/elite4/champion.png"), 850, 400, 34, 100, 29));

		game.getPlayer().setX(860);
		game.getPlayer().setY(600);

		game.getEnemyPokemonList().clear();

		Pokemon pokemon1 = new Pokemon();
		pokemon1.cloneObject(game.getCompletePokemonList().get(48));
		pokemon1.setLevel(15);

		Pokemon pokemon2 = new Pokemon();
		pokemon2.cloneObject(game.getCompletePokemonList().get(27));
		pokemon2.setLevel(15);

		Pokemon pokemon3 = new Pokemon();
		pokemon3.cloneObject(game.getCompletePokemonList().get(10));
		pokemon3.setLevel(15);

		Pokemon pokemon4 = new Pokemon();
		pokemon4.cloneObject(game.getCompletePokemonList().get(22));
		pokemon4.setLevel(15);

		Pokemon pokemon5 = new Pokemon();
		pokemon5.cloneObject(game.getCompletePokemonList().get(49));
		pokemon5.setLevel(15);

		Pokemon pokemon6 = new Pokemon();
		pokemon6.cloneObject(game.getCompletePokemonList().get(12));
		pokemon6.setLevel(17);

		game.getEnemyPokemonList().add(pokemon1);
		game.getEnemyPokemonList().add(pokemon2);
		game.getEnemyPokemonList().add(pokemon3);
		game.getEnemyPokemonList().add(pokemon4);
		game.getEnemyPokemonList().add(pokemon5);
		game.getEnemyPokemonList().add(pokemon6);

	}

	public void addElite1() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "assets/elite1.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite1.png"), 850, 400, 40, 52, false));
		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite1.png"), 850, 400, 40, 100, 19));
		game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 660, 64, 32, true));

		game.getPlayer().setX(860);
		game.getPlayer().setY(600);

		game.getEnemyPokemonList().clear();

		Pokemon pokemon1 = new Pokemon();
		pokemon1.cloneObject(game.getCompletePokemonList().get(8));
		pokemon1.setLevel(11);

		Pokemon pokemon2 = new Pokemon();
		pokemon2.cloneObject(game.getCompletePokemonList().get(39));
		pokemon2.setLevel(11);

		Pokemon pokemon3 = new Pokemon();
		pokemon3.cloneObject(game.getCompletePokemonList().get(16));
		pokemon3.setLevel(11);

		Pokemon pokemon4 = new Pokemon();
		pokemon4.cloneObject(game.getCompletePokemonList().get(13));
		pokemon4.setLevel(13);

		game.getEnemyPokemonList().add(pokemon1);
		game.getEnemyPokemonList().add(pokemon2);
		game.getEnemyPokemonList().add(pokemon3);
		game.getEnemyPokemonList().add(pokemon4);

		game.setFightingElite4(true);
	}

	public void addElite2() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "assets/elite2.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite2.png"), 850, 400, 46, 46, false));
		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite2.png"), 850, 400, 46, 100, 26));
		game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 660, 64, 32, true));

		game.getPlayer().setX(860);
		game.getPlayer().setY(600);

		game.getEnemyPokemonList().clear();

		Pokemon pokemon1 = new Pokemon();
		pokemon1.cloneObject(game.getCompletePokemonList().get(20));
		pokemon1.setLevel(12);

		Pokemon pokemon2 = new Pokemon();
		pokemon2.cloneObject(game.getCompletePokemonList().get(23));
		pokemon2.setLevel(12);

		Pokemon pokemon3 = new Pokemon();
		pokemon3.cloneObject(game.getCompletePokemonList().get(33));
		pokemon3.setLevel(12);

		Pokemon pokemon4 = new Pokemon();
		pokemon4.cloneObject(game.getCompletePokemonList().get(43));
		pokemon4.setLevel(14);

		game.getEnemyPokemonList().add(pokemon1);
		game.getEnemyPokemonList().add(pokemon2);
		game.getEnemyPokemonList().add(pokemon3);
		game.getEnemyPokemonList().add(pokemon4);

		game.setFightingElite4(true);
	}

	public void addElite3() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "assets/elite3.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite3.png"), 850, 400, 38, 50, false));
		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite3.png"), 850, 400, 38, 100, 27));
		game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 660, 64, 32, true));

		game.getPlayer().setX(860);
		game.getPlayer().setY(600);

		game.getEnemyPokemonList().clear();

		Pokemon pokemon1 = new Pokemon();
		pokemon1.cloneObject(game.getCompletePokemonList().get(18));
		pokemon1.setLevel(13);

		Pokemon pokemon2 = new Pokemon();
		pokemon2.cloneObject(game.getCompletePokemonList().get(32));
		pokemon2.setLevel(13);

		Pokemon pokemon3 = new Pokemon();
		pokemon3.cloneObject(game.getCompletePokemonList().get(38));
		pokemon3.setLevel(13);

		Pokemon pokemon4 = new Pokemon();
		pokemon4.cloneObject(game.getCompletePokemonList().get(44));
		pokemon4.setLevel(15);

		game.getEnemyPokemonList().add(pokemon1);
		game.getEnemyPokemonList().add(pokemon2);
		game.getEnemyPokemonList().add(pokemon3);
		game.getEnemyPokemonList().add(pokemon4);

		game.setFightingElite4(true);
	}

	public void addElite4() {
		game.getItems().clear();
		game.getItemsOnScreen().clear();
		game.getEntities().clear();
		game.setTileMap(new TileMap(game, 32, 32, "assets/elite4.txt", 600, 180));

		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite4.png"), 850, 400, 34, 50, false));
		game.getItems().add(new Item(getImage("assets/sprites/elite4/elite4.png"), 850, 400, 34, 100, 28));
		game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 660, 64, 32, true));

		game.getPlayer().setX(860);
		game.getPlayer().setY(600);

		game.getEnemyPokemonList().clear();

		Pokemon pokemon1 = new Pokemon();
		pokemon1.cloneObject(game.getCompletePokemonList().get(30));
		pokemon1.setLevel(14);

		Pokemon pokemon2 = new Pokemon();
		pokemon2.cloneObject(game.getCompletePokemonList().get(14));
		pokemon2.setLevel(14);

		Pokemon pokemon3 = new Pokemon();
		pokemon3.cloneObject(game.getCompletePokemonList().get(25));
		pokemon3.setLevel(14);

		Pokemon pokemon4 = new Pokemon();
		pokemon4.cloneObject(game.getCompletePokemonList().get(47));
		pokemon4.setLevel(16);

		game.getEnemyPokemonList().add(pokemon1);
		game.getEnemyPokemonList().add(pokemon2);
		game.getEnemyPokemonList().add(pokemon3);
		game.getEnemyPokemonList().add(pokemon4);

	}

	public Image getImage(String fileLocation) {
		url = Stages.class.getResource(fileLocation);
		return tk.getImage(url);
	}

	public void update() {

	}

}