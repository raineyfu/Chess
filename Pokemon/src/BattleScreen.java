import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BattleScreen {
	private Game game;
	private Pokemon friendly;
	private Pokemon enemy;
	private Sprite friendlyImage;
	private Sprite enemyImage;
	private Image transition;
	private Image enemyFloor;
	private Image playerFloor;
	private Image background;
	private Image menu;
	private Image selected;
	private Image dataBox;
	private Image pokemonMenu;
	private Image cancelNormal;
	private Image cancelHighlighted;
	private Image pokemonSelectionMenu;
	private Image pokemonSelectionMenuHighlight;
	private Image pokemonSelectionMenuNormal;
	private Image [] typeSigns = new Image [18];
	private Sprite evolveBackground;
	private Sprite pokeBall;
	private boolean playerTurn = false;
	private boolean enemyTurn = false;
	private boolean displayMoveMenu = false;
	private boolean displayOptionMenu = true;
	private boolean displayBagMenu = false;
	private boolean displayPokemonMenu = false;
	private boolean trainerEncounter = false;
	private boolean drawAttackAnimation = false;
	private boolean computerTurn = false;
	private boolean drawThrow = false;
	private boolean drawIntro = true;
	private long timer;
	private int selectedOption = 1;
	private int selectedMenu = 0;
	private int enemyX = 0;
	private int friendlyX = 1920;
	private boolean playMusic = false;
	private int enemyListCounter = 0;
	private int friendListCounter = 0;
	private List<Pokemon> friendlyList;
	private List<Pokemon> enemyList;
	
	private boolean drawFriendlyMove = false;
	private boolean drawEnemyMove = false;
	private int attackAnimationDeltaFriendly = 10;
	private int attackAnimationDeltaEnemy = 10;
	
	private int ballLocationX = 40;
	private final int ballXSpeed = 16;
	private int ballLocationY = 200;
	private double ballYSpeed = -15;
	private boolean caughtPokemon = false;
	
	private int friendlyFloorX;
	private int enemyFloorX;
	
    Toolkit tk = Toolkit.getDefaultToolkit();
    
	public BattleScreen(Game game, List<Pokemon> friendly, List<Pokemon> enemy) {
		this.game = game;
		friendlyImage = friendly.get(0).getSprite();
		enemyImage = enemy.get(0).getSprite();
		this.friendly = friendly.get(0);
		this.enemy = enemy.get(0);
		friendlyList = friendly;
		enemyList = enemy;
		
		
		enemyFloor = getImage("assets/enemybaseEliteB.png");
		background = getImage("assets/battlebgEliteA.png");
		playerFloor = getImage("assets/playerBaseEliteA.png");
		menu = getImage("assets/overlay_fight.png");
		selected = getImage("assets/menuSelected.png");
		this.transition = getImage("assets/transition2.gif");
		dataBox = getImage("assets/databox_normal.png");
		pokemonMenu = getImage("assets/menu.png");
		pokemonSelectionMenu = getImage("assets/pokemonMenu.png");
		pokemonSelectionMenuHighlight = getImage("assets/pokemonMenuHighlighted.png");
		pokemonSelectionMenuNormal = getImage("assets/pokemonMenuNormal.png");
		cancelNormal = getImage("assets/menuCancelNormal.png");
		cancelHighlighted = getImage("assets/menuCancelHighlighted.png");
		
		evolveBackground = (SpriteStore.get()).getSprite("assets/sprites/pokemon/evolveBackground.jpg");
		
		game.setMidTransition(false);
		game.setPlayTransition(true);
		for (int x = 0; x < typeSigns.length; x++) {
			typeSigns[x] = getImage("assets/MoveTypes/" + x + ".png");
		} // for
		game.getBag().setPokemonToCatch(enemy.get(0));
		
		game.getBag().resetSelection();
	}
	
	public BattleScreen() {
		
	}
	
	public void drawBattle(Graphics2D g) {
		if ((enemyX < 1200 || friendlyX > 500) && drawIntro) {
			if (enemyX < 1200) {
				enemyX+=20;
				enemyFloorX = enemyX;
			} 
			if (friendlyX > 500) {
				friendlyX -= 20;
				friendlyFloorX = friendlyX;
			}
		} else {
			drawIntro = false;
		}
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(playerFloor, friendlyFloorX - 75, 656, null);
		g.drawImage(enemyFloor, enemyFloorX + 50, 350, null);
		
		if (drawFriendlyMove || drawEnemyMove) {
			drawAttackAnimation(g);
		} else {
			enemyImage.draw(g, enemyX, 25);
			friendlyImage.draw(g, friendlyX, 331);
		}
		
		g.setColor(Color.BLACK);
		
		
		if (friendlyX <= 500 || !drawIntro) {
			drawMenu(g);
			g.drawImage(menu, 0, 720, null);
			g.drawImage(dataBox, 1300, 500, null);
			g.drawImage(dataBox, 100, 50, null);
			drawHighlight(g);
			drawMenu(g);
			if (selectedMenu != 2) {
				drawLevels(g);
				drawHealthBars(g);
			}
			
			if (selectedMenu >= 1 && selectedOption != 5) {
				g.drawImage(cancelNormal, 1600, 970, null);
			}
			// g.drawImage([], 782, 892, null);
		}
		if (enemyList.size() < 1) {
		// 	System.out.println("you won");
		}
		
		
		if (drawThrow) {
			pokeBall.draw(g, ballLocationX, ballLocationY);
			ballLocationY += ballYSpeed;
			ballLocationX += ballXSpeed;
			ballYSpeed += 0.4;
			if (ballLocationX > 1500) {
				drawThrow = false;
				if (caughtPokemon) {
					game.setDrawBattle(false);
				}
				resetPokeBallThrow();
			}
			
		}
		
		if (computerTurn && game.getTextBox().checkSize() < 2) {
			computerMove();
		}
		

		
	}

	/*
	 *
	 */
	public void changeSelection(int change) {
		if ((!drawEnemyMove || !drawFriendlyMove)) {
			if (selectedMenu == 2) {
				if (change == -1 || change == 1) {
					change = (change > 0) ? 1 : -1;
					game.getBag().changeSelectionX(change);
				} else if (change == -2 || change == 2) {
					change = (change > 0) ? 1 : -1;
					game.getBag().changeSelectionY(change);
				}
			} else if (selectedMenu == 3) {
				game.getBag().changeSelectionY(change);
			} else if (!(selectedOption + change > 5) && !(selectedOption + change < 1) && selectedMenu >= 1) {
				this.selectedOption += change;
			} else if (!(selectedOption + change > 4) && !(selectedOption + change < 1)) {
				this.selectedOption += change;
			}
		}
	}

	private void drawHighlight(Graphics2D g) {
		switch (selectedOption) {
			case 1:
				g.drawImage(selected, 16, 742, null);
				break;
			case 2:
				g.drawImage(selected, 720, 742, null);
				break;
			case 3:
				g.drawImage(selected, 16, 900, null);
				break;
			case 4:
				g.drawImage(selected, 720, 900, null);
				break;
			case 5:
				g.drawImage(cancelHighlighted, 1600, 960, null);
				break;
		}
		if (selectedOption < 5 && selectedMenu == 1) {
			g.drawString("PP: " + friendly.moveList.get(selectedOption - 1).getPP() + " / " + friendly.moveList.get(selectedOption - 1).getMaxPP(), 1560, 850);
			g.drawImage(typeSigns[friendly.getMoveType(selectedOption - 1)], 1575, 880, null);
		}
	}
	
	private void drawHealthBars(Graphics2D g) {
		g.setColor(Color.GREEN);
		int playerHealthAsPercent = (int) (((double)friendly.getHealth() / friendly.getMaxHealth()) * 193);
		int enemyHealthAsPercent = (int) (((double)enemy.getHealth() / enemy.getMaxHealth()) * 193);
		
		playerHealthAsPercent = playerHealthAsPercent > 0 ? playerHealthAsPercent: 0;
		enemyHealthAsPercent = enemyHealthAsPercent > 0 ? enemyHealthAsPercent : 0;
		
		if (playerHealthAsPercent <= 38.6) {
			g.setColor(Color.RED);
		} else if (playerHealthAsPercent <= 96.5) {
			g.setColor(Color.ORANGE);
		}
		g.fillRect(1571, 578, playerHealthAsPercent, 15);
		
		g.setColor(Color.green);
		if (enemyHealthAsPercent <= 38.6) {
			g.setColor(Color.RED);
		} else if (enemyHealthAsPercent <= 96.5) {
			g.setColor(Color.ORANGE);
		}
		
		g.fillRect(371, 128, enemyHealthAsPercent, 15);
	}

	private void drawMenu(Graphics2D g) {
		g.setFont(game.getFont());
		switch (selectedMenu) {
        	case 1:
	            g.drawImage(menu, 0, 720, null);
	            g.drawImage(dataBox, 1300, 500, null);
	            drawHighlight(g);
	            g.drawString(friendly.getMoveName(0), 250, 840);
	            g.drawString(friendly.getMoveName(1), 910, 840);
	            g.drawString(friendly.getMoveName(2), 250, 1020);
	            g.drawString(friendly.getMoveName(3), 910, 1020);
	            break;
        	case 0:
	            g.drawImage(menu, 0, 720, null);
	            g.drawImage(dataBox, 1300, 500, null);
	            drawHighlight(g);
	            g.drawString("Fight", 250, 840);
	            g.drawString("Bag",  960, 840);
	            g.drawString("Pokemon", 250, 1020);
	            g.drawString("Run", 960, 1020);
	            break;
		} // switch
	} // drawMenu


	public void checkSelection() {
		if ((!drawEnemyMove || !drawFriendlyMove) && game.getTextBox().checkSize() <= 0) {
			if (game.getDrawBag()) {
				game.getBag().checkSelection();
				if (game.getBag().itemToUse.getCatchPercentage() > 0 && game.getBag().itemToUse.getAmountOfItem() > 0) {
					pokeBall = game.getBag().itemToUse.getSprite();
					drawThrow = true;
				}
			}
			else if (selectedMenu == 1) {
				if (selectedOption < 5) {
					playerTurn = true;
					useMove(selectedOption - 1, friendly, enemy);
				}
				selectedMenu = 0;
				selectedOption = 1;
			} else if (selectedMenu == 0) { // if on main menu
				viewOption();
			}
			if (game.getDrawBag()) {
				selectedMenu = 2;
			}
			if (game.getBag().getDrawPokemonMenu()) {
				selectedMenu = 3;
			}
		}
	}

	public void viewOption() {
		if (selectedOption == 1) {
			selectedMenu = 1;
		}
		if (selectedOption == 2) {
			selectedMenu = 2;
			selectedOption = 1;
			game.setDrawBag(true);
			game.getBag().setSelectionX(1);
		}
		if (selectedOption == 3) {
			selectedMenu = 3;
			game.setDrawBag(true);
			game.getBag().setSelectionX(3);
			game.getBag().setDrawPokemonMenu(true);
		}
		if (selectedOption == 4) {
			attemptFlee();
		}
		if (selectedOption == 5) {
			selectedMenu = 0;
		}
	}

	public void attemptFlee() {
		
		if (!game.getTrainerEncounter()) {
			game.getTextBox().addMessageText("You ran away from the battle!\n");
			game.setDrawBattle(false);
			game.getEnemyPokemonList().clear();
			game.stopClip();
		} else {
			game.getTextBox().addMessageText("You cannot run away!\n");
		}
		
		
	}
	public void useMove(int moveSelected, Pokemon user, Pokemon target) {
		String efficency = "";
		PokemonMoves move = user.moveList.get(moveSelected);
		
		if (move.getPP() < 1) {
			game.getTextBox().addMessageText(user.getName() + " does not have enough pp left! \n");
		} else {
			
			int accuracyRandomGen = (int) (Math.random() * 100 + 1);
			if (accuracyRandomGen <= move.getAccuracy()) {
				
				double modifier = getModifier(move, target);
				
				if (modifier == 0.5) {
					efficency = "It was not very effective";
				} else if (modifier == 2.0) {
					efficency = "It was super effective";
				}
				
		
				int damage = (int) Math.round((((((((((double)7 * user.getLevel()) / 3) + 2) * move.getPower()) * user.getStrength()/target.getDefense()) / 50) + 2) * modifier));
				target.minusHealth(damage);
				
				game.getTextBox().addMessageText(user.getName() + " used " + user.getMoveName(moveSelected) + "\n" + efficency + "!");
				if (target.getHealth() <= 0) {
					if (playerTurn) {
						enemyListCounter++;
						if (enemyListCounter == enemyList.size()) {
							if (game.getTrainerEncounter()) {
								game.getTextBox().addMessageText("The enemy pokemon fainted!\n");
							} else {
								game.getTextBox().addMessageText("Your opponent is out of pokemon!\n");
							}
							game.setDrawBattle(false);
							game.setTrainerEncounter(false);
							game.stopClip();
							game.getEnemyPokemonList().clear();
							elite4Logic();
							game.setMoney(150);
						} else {
							enemy = enemyList.get(enemyListCounter);
							enemyImage = enemy.getSprite();
							game.getTextBox().addMessageText("Your opponent fainted, he sent out \n" + enemy.getName());
						}
						user.levelUp();
					}
					if (computerTurn) {
						friendListCounter++;
						if (friendListCounter == friendlyList.size()) {
							game.getTextBox().addMessageText("You are out of Pokemon! \n");
							game.getTextBox().addMessageText("You blacked out!\n");
							game.setDrawBattle(false);
							game.setTrainerEncounter(false);
							game.setDrawBag(false);
							game.stopClip();
							game.setMembersBeaten(0);
							game.getStages().addPlayerHouse();
							game.getPlayer().setX(960);
							game.getPlayer().setY(540);
							game.getEnemyPokemonList().clear();
							for (int i = 0; i < friendlyList.size(); i++) {
								friendlyList.get(i).setHealth(friendlyList.get(i).getMaxHealth());
								for (int k = 0; k < friendlyList.get(i).moveList.size(); k++) {
									friendlyList.get(i).moveList.get(k).setPP(friendlyList.get(i).moveList.get(k).getMaxPP());
								}
							}
						} else {
							friendly = friendlyList.get(friendListCounter);
							friendlyImage = friendly.getSprite();
							game.getTextBox().addMessageText("Your pokemon fainted! \nYou sent out " + friendly.getName());
						}
					}
				}
				
				move.setPP(move.getPP() - 1);
				
			} else {
				game.getTextBox().addMessageText(user.getName() + " missed its attack! \n");
			}
			if (computerTurn) {
				drawEnemyMove = true;
				computerTurn = false;
			}
			if (playerTurn) {
				playerTurn = false;
				drawFriendlyMove = true;
				computerTurn = true;
			}
		}
	}


	public void playTransition(Graphics2D g) {
		if (!playMusic) {
			game.getClip().stop();
			if (game.getTrainerEncounter()) {
				game.play("assets/Music/battle.wav");
			} else {
				game.play("assets/Music/normalBattle.wav");
			}
			playMusic = true;
			this.timer = System.currentTimeMillis();
			game.setMidTransition(false);
			
		}
		
		
		if (System.currentTimeMillis() - 2550 < this.timer) {
			g.drawImage(this.transition, 0, 0, 1920, 1080, null);
		} else {
			game.setPlayTransition(false);
		}
		
		if (System.currentTimeMillis() - 2450 > this.timer) {
			game.setMidTransition(true);
		} else {
			game.setMidTransition(false);
		}
		
	}

	public double getModifier(PokemonMoves move, Pokemon defender) {
		return move.typeCounters[move.getType()][defender.getType()];
	}
	
	
	public Image getImage(String fileName) {
		URL url = Game.class.getResource(fileName);
		return tk.getImage(url);
	}

	public void endBattle() {
		game.setDrawBattle(false);
	}

	public void computerMove() {
		double [] damage = new double [4];
		int moveToUse = -1;
		
		for (int i = 0; i < 4; i++) {
			damage[i] = getModifier(enemy.moveList.get(0), friendly);
			if (damage[i] == 2.0 && enemy.moveList.get(i).getPP() > 0) {
				moveToUse = i;
			}
		}
		
		while (moveToUse == -1) {
			moveToUse = (int) (Math.random() * 4);
			if (enemy.moveList.get(moveToUse).getPP() <= 0) {
				moveToUse = -1;
			}
		}
		
		computerTurn = true;
		useMove(moveToUse, enemy, friendly);
		

	}
	
	public void drawLevels(Graphics2D g) {
		g.drawString(friendly.getName(), 1400, 550);
		g.drawString("Level " + friendly.getLevel(), 1400, 630);
		
		g.drawString(enemy.getName(), 200, 100);
		g.drawString("Level " + enemy.getLevel(), 200, 180);
	}
	
	public void setSelectedMenu(int selectedMenu) {
		this.selectedMenu = selectedMenu;
		this.selectedOption = 1;
	}
	
	public void setCaughtPokemon(boolean caughtPokemon) {
		this.caughtPokemon = caughtPokemon;
	}
	
	public void setTimer(long timer) {
		this.timer = timer;
	}
	
	public void setComputerMove(boolean computerTurn) {
		this.computerTurn = computerTurn;
	}
	
	public void elite4Logic() {
		if (game.getFightingElite4()) {
			int newMembersBeaten = game.getMembersBeaten() + 1;
			game.setMembersBeaten(newMembersBeaten);
		}
		switch (game.getMembersBeaten()) {
			case 1:
				game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 180, 64, 32, 15));
				break;
			case 2:
				game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 180, 64, 32, 16));
				break;
			case 3:
				game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 180, 64, 32, 17));
				break;
			case 4:
				game.getItems().add(new Item(getImage("assets/carpet.png"), 850, 180, 64, 32, 18));
				break;
			case 5:
				game.getCutscene().setPlayCredits(true);
				break;
		}
	}
	
		public void drawAttackAnimation(Graphics2D g) {
			if (drawFriendlyMove) {
				friendlyX += attackAnimationDeltaFriendly;
				if (friendlyX >= 1000) {
					attackAnimationDeltaFriendly = -10;
				}
				if (attackAnimationDeltaFriendly == -10 && friendlyX <= 500) {
					attackAnimationDeltaFriendly = 10;
					drawFriendlyMove = false;
				}
			}
			if (drawEnemyMove) {
				enemyX -= attackAnimationDeltaEnemy;
				if (enemyX <= 700) {
					attackAnimationDeltaEnemy = -10;
				}
				if (attackAnimationDeltaEnemy == -10 && enemyX >= 1200) {
					attackAnimationDeltaEnemy = 10;
					drawEnemyMove = false;
				}
			}
			friendlyImage.draw(g, friendlyX, 331);
			enemyImage.draw(g, enemyX, 25);
		}
		
		public void setFriendly(Pokemon friendly) {
			this.friendly = friendly;
			this.friendlyImage = friendly.getSprite();
		}
		
		public void resetPokeBallThrow() {
			ballLocationX = 40;
			ballLocationY = 200;
			ballYSpeed = -15;
		}
	
	
	
}
