import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Bag {
	private Game game;
	private int selectionX = 1;
	private int selectionY = 1;
	private int selectionYMax;
	private int menu;
	private Image itemBackground;
	private Image backpack;
	private Image selected;
	private BattleItems [] itemList = new BattleItems[10];
	private BattleItems [] ballList = new BattleItems[4];
	private BattleItems [] TMHMList = new BattleItems[10];
	private boolean drawPokemonMenu = false;
	private boolean useItem = false;
	private Sprite pokemonMenuImage;
	private Sprite pokemonSelection;
	private Sprite pokemonSelectionHighlighted;
	private int swapSelectionOne = -1;
	private int swapSelectionTwo = -1;
	public BattleItems itemToUse = new BattleItems();
    Toolkit tk = Toolkit.getDefaultToolkit();
	URL url;
	private Pokemon pokemon;
	private Font font;
	
	public Bag(Game game) {
		this.game = game;
		font = game.getFont().deriveFont(game.getFont().getSize(), 16f);
		selectionYMax = 10;
		initItems();
		
		for (int x = 0; x < 5; x++) {
			ballList[0].addOneToAmount();
		}
		
		url = Bag.class.getResource("assets/backpack.png");
		backpack = tk.getImage(url);
		
		url = Bag.class.getResource("assets/BattleItems/menu.png");
		itemBackground = tk.getImage(url);
		
		url = Bag.class.getResource("assets/BattleItems/selected.png");
		selected = tk.getImage(url);
		
		pokemonMenuImage = (SpriteStore.get()).getSprite("assets/pokemonMenu.png");
		pokemonSelection = (SpriteStore.get()).getSprite("assets/pokemonMenuNormal.png");
		pokemonSelectionHighlighted = (SpriteStore.get()).getSprite("assets/pokemonMenuHighlighted.png");
	}
	
	public void drawBag(Graphics2D g) {
		if (drawPokemonMenu) {
			drawPokemonMenu(g);
		} else {
			g.setColor(Color.black);
			g.fillRect(0, 0, 1920, 1080);
			
			g.drawImage(backpack, -100, 0, null);
			
			g.setColor(Color.white);
			g.setFont(font);
			switch (selectionX) {
				case 1:
					g.drawString("Items", 400, 500);
					drawItem(g);
					break;
				case 2:
					g.drawString("Balls", 500, 500);
					drawBalls(g);
					break;
				case 3:
					drawPokemonMenu = true;
					break;
				case 4: 
					g.drawString("TM/HM", 500, 500);
					drawTMHM(g);
					break;
			}
		}
	}
	
	public void changeSelectionX(int change) {
		if (selectionX + change >= 1 && selectionX + change <= 4) {
			if (selectionX + change <= 2 && game.getDrawBattle()) {
			selectionX += change;
			} else if (!game.getDrawBattle()) {
				selectionX += change;
			}
			selectionY = 1;
		}
		if (selectionX == 1) {
			selectionYMax = 10;
		} else if (selectionX == 2) {
			selectionYMax = 4;
		} else if (selectionX == 3) {
			selectionYMax = game.getPokemonList().size();
		}
		
		if (selectionX != 3) {
			drawPokemonMenu = false;
		}
	}
	
	public void changeSelectionY(int change) {
		if (selectionY + change >= 1 && selectionY + change <= selectionYMax) {
			selectionY += change;
		}
	}
	
	public void resetSelection() {
		selectionY = 1;
		selectionX = 1;
	}
	
	public void checkSelection() {
		if (drawPokemonMenu && !useItem) {
			if (swapSelectionOne != -1) {
				swapSelectionTwo = selectionY - 1;
				if (game.getPokemonList().get(swapSelectionTwo).getHealth() <= 0) {
					game.getTextBox().addMessageText("Cannot swap this pokemon!\n It has no health!.");
					swapSelectionOne = -1;
					swapSelectionTwo = -1;
				}
			} else {
				swapSelectionOne = selectionY - 1;
			}
			if (swapSelectionOne != -1 && swapSelectionTwo != -1) {
				Collections.swap(game.getPokemonList(), swapSelectionOne, swapSelectionTwo);
				if (game.getDrawBattle()) {
					game.getBattleScreen().setFriendly(game.getPokemonList().get(0));
					game.getBattleScreen().setComputerMove(true);
					drawPokemonMenu = false;
					game.setDrawBag(false);
					game.getBattleScreen().setSelectedMenu(0);
				}
				swapSelectionOne = -1;
				swapSelectionTwo = -1;
			}
		}
		if (drawPokemonMenu && useItem) {
			useItemHeal(itemToUse, game.getPokemonList().get(selectionY - 1));
			itemToUse.removeOneFromAmount();
			game.getTextBox().addMessageText("You used " + itemToUse.getItemName() + " on " + game.getPokemonList().get(selectionY -1 ).getName() + "\nIt restored " + itemToUse.getHealthAmount() + " health!");
			drawPokemonMenu = false;
			selectionX = 1;
			useItem = false;
			if (game.getDrawBattle()) {
				game.getBattleScreen().setSelectedMenu(0);
				game.setDrawBag(false);
			}
		} else
		if (selectionX == 1) {
			if (itemList[selectionY - 1].getAmountOfItem() > 0) {
				itemToUse = new BattleItems();
				itemToUse = itemList[selectionY - 1];
				useItem = true;
				drawPokemonMenu = true;
				selectionY = 1;
			} else {
				game.getTextBox().addMessageText("You don't have this item!\n");
				if (game.getDrawBattle()) {
					game.getBattleScreen().setSelectedMenu(1);
					game.setDrawBag(false);
				}
			}
			if (game.getDrawBattle()) {
				game.getBattleScreen().setSelectedMenu(1);
			}
		}
		if (selectionX == 2) {
			if (!game.getDrawBattle()) {
				game.getTextBox().addMessageText("There are no pokemon to catch right now!\n");
			}
			if (!game.getTrainerEncounter() && game.getDrawBattle()) {
				itemToUse = ballList[selectionY - 1];
				if (itemToUse.getAmountOfItem() == 0) {
					game.getTextBox().addMessageText("You don't have this item!\n");
				} else {
					itemToUse.useBall(pokemon);
				}
				game.getBattleScreen().setSelectedMenu(0);
			} 
			if (game.getTrainerEncounter()) {
				game.getTextBox().addMessageText("You cannot catch another \ntrainer's Pokemon!");
				game.getBattleScreen().setSelectedMenu(0);
			}
			game.setDrawBag(false);
			game.getBattleScreen().setSelectedMenu(0);
		}
	}
	
	public void drawItem(Graphics2D g) {
		int xLocation = 600;
		int yLocation = 100;
		g.setColor(Color.BLACK);
		for (int i = 1; i <= itemList.length; i++) {
			g.drawImage(itemBackground, xLocation,yLocation + 25, null);
			if (i == selectionY) {
				g.drawImage(selected, xLocation, yLocation + 25, null);
			}
			itemList[i - 1].getSprite().draw(g, xLocation, yLocation + 30);
			g.drawString("x" + itemList[i - 1].getAmountOfItem(), xLocation + 150, yLocation + 75);
			xLocation += 400;
			if (i % 2 == 0) {
				xLocation = 600;
				yLocation += 100;
			}
		}
		g.setColor(Color.white);
		g.drawString(itemList[selectionY - 1].getDescription(), 700, 100);
	}
	
	public void drawBalls(Graphics2D g) {
		int xLocation = 600;
		int yLocation = 100;
		g.setColor(Color.BLACK);
		for (int i = 1; i <= ballList.length; i++) {
			g.drawImage(itemBackground, xLocation, yLocation + 25, null);
			
			if (i == selectionY) {
				g.drawImage(selected, xLocation, yLocation + 25, null);
			}
			ballList[i - 1].getSprite().draw(g, xLocation, yLocation + 30);
			g.drawString("x" + ballList[i - 1].getAmountOfItem(), xLocation + 150, yLocation + 75);
			xLocation += 400;
			if (i % 2 == 0) {
				xLocation = 600;
				yLocation += 100;
			}
		}
		g.setColor(Color.white);
		g.drawString(ballList[selectionY - 1].getDescription(), 700, 100);
		// g.drawString(ballList[selectionY - 1].getDescription(), 700, 100);
	}
	
	public void drawTMHM(Graphics2D g) {
		for (int i = 0; i < TMHMList.length; i++) {
			TMHMList[i].getSprite().draw(g, 800, i * 100);
		}
	}
	
	public void initItems() {
		String [] battleItemList = game.getFileContents("assets/BattleItems/battleItemList.txt");
		String [][] battleItemDetails = new String [battleItemList.length][6];
		
		for (int x = 0; x < battleItemList.length; x++) {
			battleItemDetails[x] = battleItemList[x].split("\"");
			
			String itemName = battleItemDetails[x][0];
			String imageLocation = battleItemDetails[x][1];
			String itemDescription = battleItemDetails[x][6];
			
			int healAmount = Integer.parseInt(battleItemDetails[x][2]);
			int catchPercentage = Integer.parseInt(battleItemDetails[x][3]);
			int removeStatus = Integer.parseInt(battleItemDetails[x][4]);
			int cost = Integer.parseInt(battleItemDetails[x][5]);
			
			boolean removeStatusBoolean = (removeStatus == 1) ? true : false;
			
			if (healAmount > 0) {
				itemList[x] = new BattleItems(game, itemName, imageLocation, healAmount, catchPercentage, cost, itemDescription);
			} else if (catchPercentage > 0) {
				ballList[x - 10] = new BattleItems(game, itemName, imageLocation, healAmount, catchPercentage, cost, itemDescription);
			}
		}
	}
	
	public void drawPokemonMenu(Graphics2D g) {
		List<Pokemon> list = game.getPokemonList();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 1920, 1080);
		
		// pokemonMenuImage.draw(g, 0, 0);
		for (int i = 1; i <= 5; i++) {
			if (i == selectionY) {
				pokemonSelectionHighlighted.draw(g, 900, 100 * selectionY);
				continue;
			}
			pokemonSelection.draw(g, 900, 100 * i + 3);
		}
		for (int i = 1; i <= list.size(); i++) {
			game.getPokemonList().get(i - 1).getPokemonIcon().draw(g, 910, 100 * i + 10);
			g.setColor(Color.white);
			g.setFont(game.getFont().deriveFont(game.getFont().getSize(), 22f));
			g.drawString(list.get(i - 1).getName(), 1000, i * 100 + 40);
			g.setFont(game.getFont().deriveFont(game.getFont().getSize(), 30f));
			g.drawString("" + list.get(i - 1).getLevel(), 1100, i * 100 + 85);
			drawHealthBars(g, game.getPokemonList().get(i - 1), i);
		}
	}
	
	private void drawHealthBars(Graphics2D g, Pokemon pokemon, int index) {
		g.setColor(Color.GREEN);
		int pokemonHealthAsPercent = (int) (((double)pokemon.getHealth() / pokemon.getMaxHealth()) * 193);
		
		pokemonHealthAsPercent = pokemonHealthAsPercent > 0 ? pokemonHealthAsPercent: 0;
		
		if (pokemonHealthAsPercent <= 38.6) {
			g.setColor(Color.RED); 
		} else if (pokemonHealthAsPercent <= 96.5) {
			g.setColor(Color.ORANGE);
		}
		g.fillRect(1284, 100 * index + 34, pokemonHealthAsPercent, 15);
	}
	
	public void useItemHeal(BattleItems itemUse, Pokemon pokemon) {
		itemUse.useHeal(pokemon);
	}
	
	public void setPokemonToCatch(Pokemon pokemonToCatch) {
		this.pokemon = pokemonToCatch;
	}
	
	public BattleItems [] getItemList() {
		return itemList;
	}
	
	public BattleItems[] getBallList() {
		return ballList;
	}
	
	public void setDrawPokemonMenu(boolean drawPokemonMenu) {
		this.drawPokemonMenu = drawPokemonMenu;
	}
	
	public void setSelectionX(int selectionX) {
		this.selectionX = selectionX;
		if (selectionX == 1) {
			selectionYMax = 10;
		} else if (selectionX == 2) {
			selectionYMax = 4;
		} else if (selectionX == 3) {
			selectionYMax = game.getPokemonList().size();
		}
	}
	
	public boolean getDrawPokemonMenu() {
		return drawPokemonMenu;
	}
	
	

}
