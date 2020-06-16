/****************************************************************************
* Name:        Game
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     Object for initiating a game
*****************************************************************************/

import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas {
	private BufferStrategy strategy;
	private boolean aPressed = false; // whether a is pressed
	private boolean sPressed = false; // whether s is pressed
	private boolean dPressed = false; // whether d is pressed
	private boolean wPressed = false; // whether w is pressed
	private boolean bPressed = false;
	private boolean waitingForSpace = false; // if game needs space to continue
	private boolean enterPressed = false; // if enter is pressed
	private boolean drawTextBox = false; // if game is drawing text box
	private boolean gameRunning = true; // whether game is running
	private Player player; // player
	private List<Entity> entities = new ArrayList(); // stores entities
	private TileMap tileMap; // stores map in tiles
	private Camera camera;
	private TextBox textBox;
	private PC pc;
	private int direction = 2;
	private List<Item> itemsOnScreen = new ArrayList<Item>();
	private List<Rectangle> rectanglesOnScreen = new ArrayList<Rectangle>();

	private boolean drawSign = false;
	private boolean drawShop = false;
	private String signMessage = "";
	private boolean drawBattle = false;
	private List<Item> items = new ArrayList<Item>();
	private Pokemon evolvePokemonOriginal;
	private Pokemon evolvePokemonNew;
	private BattleScreen battleScreen;
	private Pokemon pokemon;
	private Cutscene cutscene;
	private boolean playTransition = true;
	private Stages stages;
	private boolean midTransition = false;
	private List<Pokemon> pokemonList = new ArrayList<Pokemon>();
	private List<Pokemon> enemyPokemonList = new ArrayList<Pokemon>();
	private List<Pokemon> completePokemonList = new ArrayList<Pokemon>();
	private List<Pokemon> pcPokemonList = new ArrayList<Pokemon>();
	private List<PokemonMoves> completeMoveList = new ArrayList<PokemonMoves>();
	private Font gameFont;
	private long timer;
	private boolean initBattle = false;
	private boolean trainerEncounter = false;
	private boolean drawEvolve = false;
	private boolean initEvolve = false;
	private boolean drawPC = false;
	private boolean fightingElite4 = false;
	private Bag bag;
	private boolean drawBag = false;
	private Clip clip;
	private int money = 500;
	private Sprite evolveBackground;
	private Shop shop;
	private Image sprite;
	private int worldX;
	private int worldY;
	private int membersBeaten = 0;
	private int teleportLocationShop;
	private int teleportLocationHeal;
	Graphics2D g;

	public static void main(String args[]) {
		new Game();
	}

	private Game() {
		JFrame container = new JFrame("Pokemon Phoenix Rising by Rainey and Kevin");

		// get hold the content of the frame
		JPanel panel = (JPanel) container.getContentPane();

		// set up the resolution of the game
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setLayout(null);

		// set up canvas size (this) and add to frame
		setBounds(0, 0, 1920, 1080);
		panel.add(this);

		// Tell AWT not to bother repainting canvas since that will
		// be done using graphics acceleration
		setIgnoreRepaint(true);

		// make the window visible
		container.setUndecorated(true);
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		container.setExtendedState(container.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// if user closes window, shutdown game and jre
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // windowClosing
		});

		// add key listener to this canvas
		addKeyListener(new KeyInputHandler());

		// request focus so key events are handled by this canvas
		requestFocus();

		// create buffer strategy to take advantage of accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		initiate();
		gameLoop();
	} // constructor

	// create the game
	private void initiate() {
		g = (Graphics2D) strategy.getDrawGraphics();
		try {
			InputStream is = TextBox.class.getResourceAsStream("assets/POKEMONGB.ttf");

			gameFont = (Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f));

		} catch (IOException | FontFormatException e) {
			// Handle exception
		} // catch
		
		getMoveList();
		initPokemonList(this);

		textBox = new TextBox(this);
		stages = new Stages(this);
		player = new Player(this, "sprites/char0.png", 960, 540);
		entities.add(player);
		stages.addPlayerHouse();
		pc = new PC(this);
		battleScreen = new BattleScreen();
		camera = new Camera();
		cutscene = new Cutscene(this);
		bag = new Bag(this);
		shop = new Shop(this);
		money = 500;
		
		evolveBackground = (SpriteStore.get()).getSprite("assets/sprites/pokemon/evolveBackground.jpg");
	}

	/**
	 * 
	 */
	private void gameLoop() {
		Rectangle screenRectangle = new Rectangle (0, 0, 1920, 1080);
		cutscene.intro = true;
		while (gameRunning) {
			g = (Graphics2D) strategy.getDrawGraphics();
			// if player is not in a cutscene
			if (!cutscene.checkCutscene(g)) {
				if (drawBag) {
					bag.drawBag(g);
				} else if (!drawBattle) {
					camera.update(player.getX() - (1920 / 2), player.getY() - (1080 / 2)); // update camera
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, 1920, 1080);

					worldX = -camera.getCameraX();
					worldY = -camera.getCameraY();

					// draw tiles
					for (int x = 0; x < tileMap.getWidth(); x++) {
						for (int y = 0; y < tileMap.getHeight(); y++) {

							g.drawImage(tileMap.tiles[x][y].getImage(), worldX + (x * 16) + tileMap.getExtraWidth(),
									worldY + (y * 16) + tileMap.getExtraHeight(), this);
						}
					}

					for (int x = 1; x < entities.size(); x++) {
						Entity entity = (Entity) entities.get(x);
						entity.move();
						entity.update(worldX, worldY);
						entity.doLogic();
						if (trainerEncounter) {
							if (entity.collidesWith(player)) {
								entity.setVerticalMovement(0);
								setDrawBattle(true);
							}
						}
						entity.draw(g);
					}

					itemsOnScreen.clear();
					rectanglesOnScreen.clear();
					for (int x = 0; x < items.size(); x++) {
						Item tempItem = items.get(x);
						tempItem.update(worldX, worldY);
						if (tempItem.getX() >= 0 || tempItem.getX() <= 1920 || tempItem.getY() >= 0
								|| tempItem.getY() <= 1080 || tempItem.getX() + tempItem.getSizeX() >= 0
								|| tempItem.getX() + tempItem.getSizeX() <= 1920
								|| tempItem.getY() + tempItem.getSizeY() >= 0
								|| tempItem.getY() + tempItem.getSizeY() <= 1080) {
							tempItem.draw(g);
						}
						if (tempItem.getX() >= 100 && tempItem.getX() <= 1820 && tempItem.getY() >= 100
								&& tempItem.getY() <= 980 && tempItem.getX() + tempItem.getSizeX() >= 100
								&& tempItem.getX() + tempItem.getSizeX() <= 1820
								&& tempItem.getY() + tempItem.getSizeY() >= 100
								&& tempItem.getY() + tempItem.getSizeY() <= 980) {
							itemsOnScreen.add(tempItem);
						}
					}

					for (int x = 0; x < itemsOnScreen.size(); x++) {
						Item tempItem = itemsOnScreen.get(x);

						Rectangle rectangle = new Rectangle(tempItem.getX(), tempItem.getY(), tempItem.getSizeX(),
								tempItem.getSizeY());
						rectanglesOnScreen.add(rectangle);
					}
				} else if (drawBattle) {
					if (!initBattle) {
						battleScreen = new BattleScreen(this, getPokemonList(), enemyPokemonList);
						initBattle = true;
					}
					if (playTransition) {
						battleScreen.playTransition(g);
					}
					if (midTransition) {
						battleScreen.drawBattle(g);
					}
				} 

				if (!drawShop && !getDrawBattle() && !drawBag) {

					if (aPressed && !wPressed && !dPressed && !sPressed && !waitingForSpace) {
						player.drawLeft(g);
					} else if (sPressed && !wPressed && !dPressed && !aPressed && !waitingForSpace) {
						player.drawDown(g);
					} else if (wPressed && !aPressed && !dPressed && !sPressed && !waitingForSpace) {
						player.drawUp(g);
					} else if (dPressed && !wPressed && !aPressed && !sPressed && !waitingForSpace) {
						player.drawRight(g);
					} else {
						player.drawStill(g, direction);
					}

				}

				g.setColor(Color.black);

				if (drawSign) {
					textBox.drawSign(signMessage, g);
				}

				if (drawShop) {
					shop.drawShop(g);
				}

				if (drawPC) {
					pc.draw(g);
				}
				if (drawEvolve) {
					if (initEvolve) {
						initEvolve();
					}
					drawEvolve(g);
				}

				if (textBox.checkSize() >= 2) {
					drawTextBox = true;
					waitingForSpace = true;
					textBox.drawText(g);
				}

			} else {
				cutscene.checkCutscene(g);
				if (textBox.checkSize() >= 2) {
					drawTextBox = true;
					waitingForSpace = true;
					textBox.drawText(g);
				}
			}

			// above dispose temporarily omve down liater
			if (!drawShop && !getDrawBattle() && !trainerEncounter && !drawPC) {
				if (aPressed && !wPressed && !dPressed && !sPressed && !waitingForSpace) {
					player.checkMoveHorizontal(-117, g);
				}
				if (dPressed && !wPressed && !aPressed && !sPressed && !waitingForSpace) {
					player.checkMoveHorizontal(117, g);
				}
				if (wPressed && !aPressed && !dPressed && !sPressed && !waitingForSpace) {
					player.checkMoveVertical(-117);
				}
				if (sPressed && !wPressed && !dPressed && !aPressed && !waitingForSpace) {
					player.checkMoveVertical(117);
				}
			}

			// g.drawImage(sprite, 0, 0, null);

			g.dispose();
			strategy.show();

			player.setHorizontalMovement(0);
			player.setVerticalMovement(0);

			checkClick();

			try {
				Thread.sleep(17);
			} catch (Exception e) {
			}

		}
	}

	public void checkClick() {
		if (!drawPC && !getDrawBattle() && !cutscene.checkCutscene() && !drawShop && !drawBag) {
			if (enterPressed) {
				player.checkSelection(g);
				enterPressed = false;
			}
		}
		if (drawShop) {
			if (wPressed) {
				shop.changeSelection(-2);
				wPressed = false;
			} // if
			if (sPressed) {
				shop.changeSelection(2);
				sPressed = false;
			} // if

			if (dPressed) {
				shop.changeSelection(1);
				dPressed = false;
			} // if
			if (aPressed) {
				shop.changeSelection(-1);
				aPressed = false;
			}
			if (enterPressed) {
				shop.checkSelection();
				enterPressed = false;
			}
		}
		if (drawBattle) {
			if (wPressed) {
				battleScreen.changeSelection(-2);
				wPressed = false;
			} // if

			if (sPressed) {
				battleScreen.changeSelection(2);
				sPressed = false;
			} // if

			if (dPressed) {
				battleScreen.changeSelection(1);
				dPressed = false;
			} // if
			if (aPressed) {
				battleScreen.changeSelection(-1);
				aPressed = false;
			}
			if (enterPressed) {
				battleScreen.checkSelection();
				enterPressed = false;
			}
		}
		if (cutscene.checkCutscene()) {
			if (dPressed) {
				cutscene.changeSelection(1);
				dPressed = false;
			} // if
			if (aPressed) {
				cutscene.changeSelection(-1);
				aPressed = false;
			}
			if (enterPressed) {
				cutscene.checkSelection();
				enterPressed = false;
			}
		}
		if (!drawBattle && !cutscene.checkCutscene() && !drawShop && !drawPC) {
			if (bPressed) {
				drawBag = !drawBag;
				bag.resetSelection();
				bPressed = false;
			}
		}
		if (drawBag && !drawBattle) {
			if (dPressed) {
				bag.changeSelectionX(1);
				dPressed = false;
			}
			if (aPressed) {
				bag.changeSelectionX(-1);
				aPressed = false;
			}
			if (wPressed) {
				bag.changeSelectionY(-1);
				wPressed = false;
			}
			if (sPressed) {
				bag.changeSelectionY(1);
				sPressed = false;
			}
			if (enterPressed) {
				bag.checkSelection();
				enterPressed = false;
			}
		}
		if (drawPC && !getDrawBattle() && !cutscene.checkCutscene() && !drawShop) {
			if (dPressed) {
				pc.changeSelection(1);
				dPressed = false;
			}
			if (aPressed) {
				pc.changeSelection(-1);
				aPressed = false;
			}
			if (enterPressed) {
				pc.checkSelection();
				enterPressed = false;
			}
			if (sPressed) {
				pc.changeSelection(12);
				sPressed = false;
			}
			if (wPressed) {
				pc.changeSelection(-12);
				wPressed = false;
			}
		}
	}

	/**
	 * 
	 */

	public class KeyInputHandler extends KeyAdapter {

		/*
		 * The following methods are required for any class that extends the abstract
		 * class KeyAdapter. They handle keyPressed, keyReleased and keyTyped events.
		 */

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				waitingForSpace = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
				direction = 0;
			} // if

			if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
				direction = 2;
			} // if

			if (e.getKeyCode() == KeyEvent.VK_D) {
				dPressed = true;
				direction = 1;
			} // if
			if (e.getKeyCode() == KeyEvent.VK_A) {
				aPressed = true;
				direction = 3;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_B) {
				bPressed = true;
			}
		} // keyPressed

		public void keyReleased(KeyEvent e) {
			// if waiting for keypress to start game, do nothing

			// respond to move left, right or fire
			if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = false;
			} // if

			if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = false;
			} // if

			if (e.getKeyCode() == KeyEvent.VK_D) {
				dPressed = false;
			} // if
			if (e.getKeyCode() == KeyEvent.VK_A) {
				aPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				waitingForSpace = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				enterPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_B) {
				bPressed = false;
			}

		} // keyReleased

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				if (drawTextBox && textBox.checkSize() >= 2) {
					textBox.removeMessage(1);
					textBox.removeMessage(0);
					textBox.reset();
				} // if
			} // if
				// if escape is pressed, end game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			} // if escape pressed

		} // keyTyped

	} // class KeyInputHandler

	/**
	 * 
	 */
	private void getMoveList() {

		String[] moveList = getFileContents("assets/moveList.txt");
		String[][] moveDetails = new String[moveList.length][6];

		for (int x = 0; x < moveList.length; x++) {
			moveDetails[x] = moveList[x].split(",");

			String moveName = moveDetails[x][0];
			int maxPP = Integer.parseInt(moveDetails[x][1]);
			int pp = Integer.parseInt(moveDetails[x][2]);
			int power = Integer.parseInt(moveDetails[x][3]);
			int accuracy = Integer.parseInt(moveDetails[x][4]);
			int type = Integer.parseInt(moveDetails[x][5]);

			PokemonMoves newMove = new PokemonMoves(moveName, maxPP, pp, power, accuracy, type);
			completeMoveList.add(newMove);
		}
	}

	/**
	 * 
	 */
	private void initPokemonList(Game game1) {
		Pokemon newPokemon;
		String[] pokemonList = getFileContents("pokemon/pokemonList.txt");
		String[][] pokemonDetails = new String[pokemonList.length][10];

		for (int x = 0; x < pokemonList.length; x++) {
			pokemonDetails[x] = pokemonList[x].split(" ");

			String pokemonName = pokemonDetails[x][0];
			String imageLocation = pokemonDetails[x][1];

			int id = Integer.parseInt(pokemonDetails[x][2]);
			int hp = Integer.parseInt(pokemonDetails[x][3]);
			int attack = Integer.parseInt(pokemonDetails[x][4]);
			int defense = Integer.parseInt(pokemonDetails[x][5]);
			int specialAttack = Integer.parseInt(pokemonDetails[x][6]);
			int specialDefense = Integer.parseInt(pokemonDetails[x][7]);
			int speed = Integer.parseInt(pokemonDetails[x][8]);
			int type = Integer.parseInt(pokemonDetails[x][9]);

			if (pokemonDetails[x].length == 12) {
				int evolvePokemonID = Integer.parseInt(pokemonDetails[x][10]);
				int evolveLevel = Integer.parseInt(pokemonDetails[x][11]);
				newPokemon = new Pokemon(game1, pokemonName, imageLocation, id, hp, attack, defense, specialAttack,
						specialDefense, speed, type, evolvePokemonID, evolveLevel);
			} else {
				newPokemon = new Pokemon(game1, pokemonName, imageLocation, id, hp, attack, defense, specialAttack,
						specialDefense, speed, type);
			}
			completePokemonList.add(newPokemon);
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	public String[] getFileContents(String fileName) {
		String[] contents = null;
		int length = 0;
		try {
			// input
			String folderName = "assets/"; // if the file is contained in the same folder as the .class file, make this
											// equal to the empty string
			String resource = fileName;

			// this is the path within the jar file
			InputStream input = TileMap.class.getResourceAsStream(folderName + resource);
			if (input == null) {
				// this is how we load file within editor (eg eclipse)
				input = TileMap.class.getClassLoader().getResourceAsStream(resource);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(input));

			in.mark(999999); // see api

			// count number of lines in file
			while (in.readLine() != null) {
				length++;
			}

			in.reset(); // rewind the reader to the start of file
			contents = new String[length]; // give size to contents array

			// read in contents of file and print to screen
			for (int i = 0; i < length; i++) {
				contents[i] = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			System.out.println("File Input Error");
		}

		return contents;

	} // getFileContents

	// plays the audio
	/**
	 * @param filename
	 */
	public void play(String filename) {
		try {
			this.clip = AudioSystem.getClip();
			InputStream url = getClass().getResourceAsStream(filename);
			InputStream bufferedIn = new BufferedInputStream(url);
			clip.open(AudioSystem.getAudioInputStream(bufferedIn));
			this.clip.start();
			this.clip.loop(clip.LOOP_CONTINUOUSLY);
			//add buffer for mark/reset support

		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	/**
	 * @param g
	 */
	private void drawEvolve(Graphics2D g) {
		evolveBackground.draw(g, 0, 0);
		if (textBox.checkSize() <= 2) {
			evolvePokemonNew.getSprite().draw(g, 700, 300);
		} else {
			evolvePokemonOriginal.getSprite().draw(g, 700, 300);
		}
		if (textBox.checkSize() < 1) {
			drawEvolve = false;
			initEvolve = true;
		}
	}

	/**
	 * 
	 */
	private void initEvolve() {
		textBox.addMessageText("What?\n" + evolvePokemonOriginal.getName() + " is evolving!");
		textBox.addMessageText("Your " + evolvePokemonOriginal.getName() + " has evolved \ninto a "+ evolvePokemonNew.getName() + "!");
		initEvolve = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#getFont()
	 */
	public Font getFont() {
		return gameFont;
	}

	/**
	 * @return
	 */
	public boolean getDrawBattle() {
		return drawBattle;
	}

	/**
	 * @param drawBattle
	 */
	public void setDrawBattle(boolean drawBattle) {
		this.drawBattle = drawBattle;
	}

	/**
	 * @return
	 */
	public List<Pokemon> getPokemonList() {
		return pokemonList;
	}

	/**
	 * @param drawBag
	 */
	public void setDrawBag(boolean drawBag) {
		this.drawBag = drawBag;
	}

	/**
	 * @return
	 */
	public BattleScreen getBattleScreen() {
		return battleScreen;
	}

	/**
	 * @return
	 */
	public boolean getTrainerEncounter() {
		return trainerEncounter;
	}

	/**
	 * @return
	 */
	public Bag getBag() {
		return bag;
	}

	/**
	 * @return
	 */
	public TextBox getTextBox() {
		return textBox;
	}

	/**
	 * @param trainerEncounter
	 */
	public void setTrainerEncounter(boolean trainerEncounter) {
		this.trainerEncounter = trainerEncounter;
	}

	/**
	 * @return
	 */
	public boolean getMidTransition() {
		return midTransition;
	}

	/**
	 * @param midTransition
	 */
	public void setMidTransition(boolean midTransition) {
		this.midTransition = midTransition;
	}

	/**
	 * @param playTransition
	 */
	public void setPlayTransition(boolean playTransition) {
		this.playTransition = playTransition;
	}

	/**
	 * @return
	 */
	public boolean getEnterPressed() {
		return enterPressed;
	}

	/**
	 * @return
	 */
	public Stages getStages() {
		return stages;
	}

	/**
	 * @return
	 */
	public List<Pokemon> getCompletePokemonList() {
		return completePokemonList;
	}

	/**
	 * @return
	 */
	public List<PokemonMoves> getCompleteMoveList() {
		return completeMoveList;
	}

	/**
	 * @return
	 */
	public Clip getClip() {
		return clip;
	}

	public void stopClip() {
		if (clip != null && clip.isRunning()) {
			this.clip.stop();
		}
	}

	/**
	 * @param clip
	 */
	public void setClip(Clip clip) {
		this.clip = clip;
	}

	/**
	 * @return
	 */
	public List<Rectangle> getRectanglesOnScreen() {
		return rectanglesOnScreen;
	}

	/**
	 * @return
	 */
	public List<Item> getItemsOnScreen() {
		return itemsOnScreen;
	}

	/**
	 * @param drawSign
	 */
	public void setDrawSign(boolean drawSign) {
		this.drawSign = drawSign;
	}

	/**
	 * @param signMessage
	 */
	public void setSignMessage(String signMessage) {
		this.signMessage = signMessage;
	}

	/**
	 * @param drawShop
	 */
	public void setDrawShop(boolean drawShop) {
		this.drawShop = drawShop;
	}

	/**
	 * @param initBattle
	 */
	public void setInitBattle(boolean initBattle) {
		this.initBattle = initBattle;
	}

	/**
	 * @return
	 */
	public List<Pokemon> getEnemyPokemonList() {
		return enemyPokemonList;
	}

	/**
	 * @return
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param drawEvolve
	 */
	public void setDrawEvolve(boolean drawEvolve) {
		this.drawEvolve = drawEvolve;
	}

	/**
	 * @param initEvolve
	 */
	public void setInitEvolve(boolean initEvolve) {
		this.initEvolve = initEvolve;
	}

	/**
	 * @param evolvePokemonOriginal
	 */
	public void setEvolvePokemonOriginal(Pokemon evolvePokemonOriginal) {
		this.evolvePokemonOriginal = evolvePokemonOriginal;
	}

	/**
	 * @param evolvePokemonNew
	 */
	public void setEvolvePokemonNew(Pokemon evolvePokemonNew) {
		this.evolvePokemonNew = evolvePokemonNew;
	}

	/**
	 * @param money
	 */
	public void setMoney(int money) {
		this.money += money;
	}

	/**
	 * @return
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @return
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @return
	 */
	public TileMap getTileMap() {
		return tileMap;
	}

	/**
	 * @param tileMap
	 */
	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	/**
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param timer
	 */
	public void setTimer(long timer) {
		this.timer = timer;
	}

	/**
	 * @param drawTextBox
	 */
	public void setDrawTextBox(boolean drawTextBox) {
		this.drawTextBox = drawTextBox;
	}

	/**
	 * @param waitingForSpace
	 */
	public void setWaitingForSpace(boolean waitingForSpace) {
		this.waitingForSpace = waitingForSpace;
	}

	/**
	 * @param drawPC
	 */
	public void setDrawPC(boolean drawPC) {
		this.drawPC = drawPC;
	}

	/**
	 * @return
	 */
	public List<Pokemon> getPCPokemonList() {
		return pcPokemonList;
	}

	public int getWorldX() {
		return worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public boolean getDrawBag() {
		return drawBag;
	}

	public void setFightingElite4(boolean fightingElite4) {
		this.fightingElite4 = fightingElite4;
	}

	public boolean getFightingElite4() {
		return fightingElite4;
	}

	public Cutscene getCutscene() {
		return cutscene;
	}

	public void setMembersBeaten(int membersBeaten) {
		this.membersBeaten = membersBeaten;
	}

	public int getMembersBeaten() {
		return membersBeaten;
	}

	public void setTeleportLocationShop(int teleportLocationShop) {
		this.teleportLocationShop = teleportLocationShop;
	}

	public void setTeleportLocationHeal(int teleportLocationHeal) {
		this.teleportLocationHeal = teleportLocationHeal;
	}

	public int getTeleportLocationShop() {
		return teleportLocationShop;
	}

	public int getTeleportLocationHeal() {
		return teleportLocationHeal;
	}

}