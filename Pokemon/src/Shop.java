/****************************************************************************
* Name:        Shop
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     Object to set up the shop
*****************************************************************************/


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Shop {
	private Game game;
	private int selection;
	private int selectionX;
	private int maxSelection;
	private Sprite shopMenu;
	private Sprite nonSelected;
	private Sprite selectedItem;
	private Sprite selected;
	private Sprite arrowLeftSelected;
	private Sprite arrowRightSelected;
	private Sprite arrowRightNormal;
	private Sprite arrowLeftNormal;
	private Sprite exitNormal;
	private Sprite exitHighlighted;
	private Font font;
	private BattleItems[] itemList;
	private BattleItems[] ballList;
	private BattleItems itemToBuy;
	
	public Shop (Game game) {
		this.game = game;
		shopMenu = (SpriteStore.get()).getSprite("assets/shopMenu.png");
		nonSelected = (SpriteStore.get()).getSprite("assets/BattleItems/menu.png");
		selected = (SpriteStore.get()).getSprite("assets/BattleItems/selected.png");
		
		arrowLeftSelected = (SpriteStore.get()).getSprite("assets/arrowLeftSelected.png");
		arrowRightSelected = (SpriteStore.get()).getSprite("assets/arrowRightSelected.png");
		arrowRightNormal = (SpriteStore.get()).getSprite("assets/arrowRightNormal.png");
		arrowLeftNormal = (SpriteStore.get()).getSprite("assets/arrowLeftNormal.png");
		exitNormal = (SpriteStore.get()).getSprite("assets/exit.png");
		exitHighlighted = (SpriteStore.get()).getSprite("assets/exitHighlighted.png");
		
		font = game.getFont();
		
		maxSelection = 14;
		selection = 3;
		selectionX = 1;
		itemList = game.getBag().getItemList();
		ballList = game.getBag().getBallList();
	}
	
	/**
	 * @param change
	 */
	public void changeSelection(int change) {
		if (!(selection + change <= 0) && !(selection + change >= maxSelection)) {
			this.selection += change;
		}
	}
	
	
	/**
	 * 
	 */
	public void drawShop(Graphics2D g) {

		shopMenu.draw(g, 480, 180);
		int yLocation = 324;
		int xLocation = 680;
		
		g.setFont(font);
		if (selection == 2) {
			arrowLeftSelected.draw(g, xLocation, 200);
			arrowRightNormal.draw(g, xLocation + 470, 200);
		} else if (selection == 3) {
			arrowRightSelected.draw(g, xLocation + 470, 200);
			arrowLeftNormal.draw(g, xLocation, 200);
		} else {
			arrowLeftNormal.draw(g, xLocation, 200);
			arrowRightNormal.draw(g, xLocation + 470, 200);
		}
		g.setColor(Color.black);
		exitNormal.draw(g, 500, 155);
		if (selection == 1) {
			exitHighlighted.draw(g, 500, 155);
		}
		switch (selectionX) {
			case 1:
				g.drawString("Healing Items", xLocation + 125, 230);
				break;
			case 2: 
				g.drawString("MicroBalls", xLocation + 125, 230);
				break;
			default:

		}
		g.setFont(font.deriveFont(this.font.getSize(), 14f));
		if (selectionX == 1) {
			for (int x = 1; x <= 10; x++) {
				nonSelected.draw(g, xLocation, yLocation);
				
				if (x == selection - 3) {
					selected.draw(g, xLocation, yLocation);
					g.drawString(itemList[x - 1].getDescription(), 550, 300);
				}
				itemList[x - 1].getSprite().draw(g, xLocation, yLocation);
				g.drawString("$" + itemList[x - 1].getCost(), xLocation + 100, yLocation + 50);
				xLocation += 280;
				if (x % 2 == 0) {
					yLocation += 100; 
					xLocation = 680;
				}
			}
		} else if (selectionX == 2) {
			yLocation = 304;
			xLocation = 680;
			for (int x = 1; x <= 4; x++) {
				nonSelected.draw(g, xLocation, yLocation);
				
				if (x == selection - 3) {
					selected.draw(g,  xLocation, yLocation);
					g.drawString(ballList[x - 1].getDescription(), 550, 300);
				}
				ballList[x - 1].getSprite().draw(g, xLocation, yLocation);
				g.drawString("$" + ballList[x - 1].getCost(), xLocation + 100, yLocation + 50);
				xLocation += 280;
				if (x % 2 == 0) {
					yLocation += 100; 
					xLocation = 680;
				}
			}
		}
		g.drawString("Cash $" + game.getMoney(), 550, 875);
	}
	
	/**
	 * 
	 */
	public void checkSelection() {
		if (selection == 1) {
			game.setDrawShop(false);
		}
		if (selection == 2 && selectionX - 1 >= 1) {
			selectionX--;
		} else if (selection == 3 && selectionX + 1 <= 2) {
			selectionX++;
		} else if (selection != 2 && selection != 1) {
			if (selectionX == 1) {
				itemToBuy = itemList[selection - 4];
			} else if (selectionX == 2) {
				itemToBuy = ballList[selection - 4];
			}
			itemToBuy.addOneToAmount();
			if (game.getMoney() >= itemToBuy.getCost()) {
				game.setMoney(-itemToBuy.getCost());
			} else {
				game.getTextBox().addMessageText("You don't have enough \nmoney for this item!");
			}
		}
		
	}
}