/****************************************************************************
* Name:        Cutscene
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     Class for playing introduction and credits
*****************************************************************************/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Cutscene {
	private Game game;
	public boolean intro = false;
	long sleepTimer;
	private long songTimer;
	long currentTimer;
	private boolean newTimer = false;
	private boolean playMusic = false;
	private boolean playTitle = false;
	private boolean playTitleMusic = false;
	private boolean playIntroduction = false;
	private boolean hasIntroBeenAdded = false;
	private boolean waitingForSpace = false;
	public boolean playCredits = false;
	private boolean initTimer = false;
	private Image introVid;
	private Image title;
	private Image professor;
	private Image credits;
	private int professorX = 1920;
	private int selection = 1;

	public Cutscene(Game game) {
		this.game = game;
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Cutscene.class.getResource("sprites/intro.gif");
		introVid = tk.getImage(url);

		url = Cutscene.class.getResource("assets/title.gif");
		title = tk.getImage(url);

		url = Cutscene.class.getResource("assets/professor.png");
		professor = tk.getImage(url);

		songTimer = System.currentTimeMillis();

		url = Cutscene.class.getResource("assets/credits.gif");
		credits = tk.getImage(url);

	}

	public boolean checkCutscene(Graphics2D g) {
		if (intro) {
			playIntro(g);
			return true;
		}
		if (playTitle) {
			playTitle(g);
			return true;
		}
		if (playIntroduction) {
			playIntroduction(g);
			return true;
		}
		if (playCredits) {
			playCredits(g);
			return true;
		}
		return false;
	}

	public boolean checkCutscene() {
		if (intro) {
			return true;
		}
		if (playTitle) {
			return true;
		}
		if (playIntroduction) {
			return true;
		}
		if (playCredits) {
			return true;
		}
		return false;
	}

	public void playIntro(Graphics2D g) {

		if (!playMusic) {
			game.play("/assets/Music/intro.wav");
			playMusic = true;
			sleepTimer = System.currentTimeMillis();
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, 1920, 1080);
		if (System.currentTimeMillis() - 4600 > sleepTimer) {
			if (!newTimer) {
				currentTimer = System.currentTimeMillis();
				newTimer = true;
			}
			if (System.currentTimeMillis() - 15840 > currentTimer) {
				intro = false;
				playTitle = true;

			}
			if (System.currentTimeMillis() - 15840 <= currentTimer) {
				g.drawImage(introVid, 0, 0, 1920, 1080, null);
			}
		}

	}

	public void playTitle(Graphics2D g) {
		g.drawImage(title, 0, 0, null);

		if (!playTitleMusic) {
			game.stopClip();
			game.play("assets/Music/titleMusic.wav");
			playTitleMusic = true;
		}

		if (game.getEnterPressed()) {
			playTitle = false;
			game.getStages().addPlayerHouse();
			playIntroduction = true;
		}
	}

	public void playIntroduction(Graphics2D g) {
		g.fillRect(0, 0, 1920, 1080);
		g.drawImage(professor, professorX, 50, null);
		if (professorX > 500) {
			professorX -= 20;
		}
		if (!hasIntroBeenAdded) {
			addIntroduction();
			hasIntroBeenAdded = true;
		}
		choosePokemon(g);
	}

	public void choosePokemon(Graphics2D g) {
		if (game.getTextBox().checkSize() < 2) {
			waitingForSpace = true;
			switch (selection) {
			case 1:
				game.getCompletePokemonList().get(0).getSprite().draw(g, 1000, 500);
				break;
			case 2:
				game.getCompletePokemonList().get(3).getSprite().draw(g, 1000, 500);
				break;
			case 3:
				game.getCompletePokemonList().get(6).getSprite().draw(g, 1000, 500);
				break;
			}
		}
	}

	public void addIntroduction() {
		game.getTextBox().initiateText("Hmm... Interesting... \n... (Click space)");
		game.getTextBox().addMessageText("Huh? Oh! Excuse me, sorry! \nI was just reading this book here.");
		game.getTextBox().addMessageText("Sorry to keep you waiting. \n");
		game.getTextBox().addMessageText("Welcome to the world of Pokemon! \n");
		game.getTextBox().addMessageText("My name is Professor Rainey. \n");
		game.getTextBox().addMessageText("But everyone calls me the \nPokemon Professor.");
		game.getTextBox().addMessageText("Before we go any further \nI'd like to tell you a few things.");
		game.getTextBox().addMessageText("This world is inhabited by \ncreatures known as Pokemon!");
		game.getTextBox().addMessageText("We humans live alongside Pokemon \nas friends");
		game.getTextBox().addMessageText("At times we work together \nand at others we play together.");
		game.getTextBox().addMessageText("Some people use their Pokemon to \nbattle.");
		game.getTextBox().addMessageText("Most people try to \nbecome the Pokemon Champion.");
		game.getTextBox().addMessageText("If you encounter dangerous Pokemon \nyou can run away.");
		game.getTextBox().addMessageText("However, you can't run from \nbattles with other trainers.");
		game.getTextBox().addMessageText("You can use A W S D to \nmove around.");
		game.getTextBox().addMessageText("You may also use enter \nto interact with the world.");
		game.getTextBox().addMessageText("You may click B to \naccess your bag.");
		game.getTextBox().addMessageText("Your bag can contain \nvarious useful items.");
		game.getTextBox().addMessageText("You can buy items at the \nPokeShop, a blue building.");
		game.getTextBox().addMessageText("And you can obtain money \nby defeating Pokemon.");
		game.getTextBox().addMessageText("You may heal Pokemon at the \nPokeCenter, a red building.");
		game.getTextBox().addMessageText("Now that is enough for now you'll \nlearn the rest on your journey.");
		game.getTextBox().addMessageText("I will allow you to choose a \nPokemon.");
		game.getTextBox().addMessageText("Use W A S D and enter \nto choose. Pick wisely.");
		
	}

	public void changeSelection(int change) {
		if (!(selection + change > 3) && !(selection + change < 1)) {
			selection += change;
		}
	}

	public void checkSelection() {
		if (playIntroduction && waitingForSpace) {
			if (selection == 1) {
				Pokemon pokemon = new Pokemon();
				pokemon.cloneObject(game.getCompletePokemonList().get(0));
				pokemon.setLevel(5);
				game.getPokemonList().add(pokemon);
			}
			if (selection == 2) {
				Pokemon pokemon = new Pokemon();
				pokemon.cloneObject(game.getCompletePokemonList().get(3));
				pokemon.setLevel(5);
				game.getPokemonList().add(pokemon);
			}
			if (selection == 3) {
				Pokemon pokemon = new Pokemon();
				pokemon.cloneObject(game.getCompletePokemonList().get(6));
				pokemon.setLevel(5);
				game.getPokemonList().add(pokemon);
			}
			playIntroduction = false;
			game.getTextBox().addMessageText("Remember to come visit me after\nyour journey!");
		}
	}

	public void playCredits(Graphics2D g) {
		if (!initTimer) {
			currentTimer = System.currentTimeMillis();
			initTimer = true;
		}
		if (currentTimer + 10100 >= System.currentTimeMillis()) {
			g.drawImage(credits, 0, 0, 1920, 1080, null);
		} else {
			System.exit(0);
		}
	}

	public void setPlayCredits(boolean playCredits) {
		this.playCredits = playCredits;
	}
}
