import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Collections;
import java.util.List;

public class PC {
	private Game game;
	private Sprite menu;

	private Font font;

	private int selection = 0;
	private int selectionX = 1;
	private int arrowIndex;
	private int maxPages;

	private Sprite arrowLeftSelected;
	private Sprite arrowRightSelected;
	private Sprite arrowRightNormal;
	private Sprite arrowLeftNormal;
	private Sprite pokemonSelection;
	private Sprite pokemonSelectionHighlighted;
	private List<Pokemon> list;

	Stroke oldStroke;

	public PC(Game game) {
		this.game = game;
		font = game.getFont().deriveFont(game.getFont().getSize(), 16f);
		arrowLeftSelected = (SpriteStore.get()).getSprite("assets/arrowLeftSelected.png");
		arrowRightSelected = (SpriteStore.get()).getSprite("assets/arrowRightSelected.png");
		arrowRightNormal = (SpriteStore.get()).getSprite("assets/arrowRightNormal.png");
		arrowLeftNormal = (SpriteStore.get()).getSprite("assets/arrowLeftNormal.png");
		pokemonSelection = (SpriteStore.get()).getSprite("assets/pokemonMenuNormal.png");
		pokemonSelectionHighlighted = (SpriteStore.get()).getSprite("assets/pokemonMenuHighlighted.png");
		list = game.getPokemonList();

	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 1920, 1080);

		for (int i = 1; i <= 6; i++) {
			if (i == selection + 7) {
				pokemonSelectionHighlighted.draw(g, 100, 100 * i);
				continue;
			}
			pokemonSelection.draw(g, 100, 100 * i + 3);
		}

		for (int i = 1; i <= list.size(); i++) {
			game.getPokemonList().get(i - 1).getPokemonIcon().draw(g, 110, 100 * i + 10);
			g.setColor(Color.white);
			g.setFont(game.getFont().deriveFont(game.getFont().getSize(), 22f));
			g.drawString(list.get(i - 1).getName(), 200, i * 100 + 40);
			g.setFont(game.getFont().deriveFont(game.getFont().getSize(), 30f));
			g.drawString("" + list.get(i - 1).getLevel(), 300, i * 100 + 85);
			drawHealthBars(g, game.getPokemonList().get(i - 1), i);
		}

		int selectionX = this.selectionX - 1;

		menu = (SpriteStore.get()).getSprite("assets/shopMenu.png");
		menu.draw(g, 780, 180);

		g.setColor(Color.black);
		g.setFont(font);
		g.drawString("PC", 1245, 200);

		oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(4));

		int xLocation = 810;
		int yLocation = 275;
		g.setColor(Color.red);

		for (int x = selectionX * 84; x < 84 + (selectionX * 84); x++) {
			if (x < game.getPCPokemonList().size()) {
				game.getPCPokemonList().get(x).getPokemonIcon().draw(g, xLocation, yLocation);
				if (selection < 84) {
					if (selection == x - (selectionX * 84)) {
						g.drawRect(xLocation, yLocation, 75, 75);
					}
				}
				xLocation += 75;
				if (xLocation > 1675) {
					xLocation = 810;
					yLocation += 75;
				}
			}
		}

		maxPages = (int) Math.ceil((double) game.getPCPokemonList().size() / 84);
		if (game.getPCPokemonList().size() <= 84) {
			maxPages = 1;
		}
		
		if (this.selectionX == maxPages) {
			arrowIndex = game.getPCPokemonList().size() % 84;
		} else {
			arrowIndex = 84;
		}
		g.setStroke(oldStroke);

		g.drawString("Page " + (this.selectionX), 1215, 825);
		if (selection == arrowIndex) {
			arrowLeftSelected.draw(g, 1200, 850);
		} else {
			arrowLeftNormal.draw(g, 1200, 850);
		}

		if (selection == arrowIndex + 1) {
			arrowRightSelected.draw(g, 1300, 850);
		} else {
			arrowRightNormal.draw(g, 1300, 850);
		}
		if (selection == arrowIndex + 2) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		g.drawString("Exit", 1600, 850);
	}

	public void changeSelection(int change) {
		if (!(selection + change < -6) && !(selection + change > arrowIndex + 2)) {
			selection += change;
		} else if (selection + change < 0) {
			selection = -6;
		} else if (selection + change > arrowIndex + 1) {
			selection = arrowIndex + 1;
		}
	}

	public void checkSelection() {
		if (selection == arrowIndex || selection == arrowIndex + 1 || selection == arrowIndex + 2) {
			if (selectionX - 1 >= 1) {
				if (selection == arrowIndex) {
					if (selectionX >= 2) {
						selectionX--;
						selection = 0;
					}
				}
			} else if (selectionX + 1 <= maxPages) {
				if (selection == arrowIndex + 1) {
					selectionX++;
					selection = 0;
				}
			}

			if (selection == arrowIndex + 2) {
				game.setDrawPC(false);
			}
		} else if (selection < 0 && selection + 6 < game.getPokemonList().size()) {
			game.getPCPokemonList().add(game.getPokemonList().get(selection + 6));
			game.getPokemonList().remove(selection + 6);
		} else if (selection >= 0 && game.getPokemonList().size() < 6) {
			game.getPokemonList().add(game.getPCPokemonList().get(((selectionX - 1) * 84) + selection));
			game.getPCPokemonList().remove(((selectionX - 1) * 84) + selection);
		}
	}

	private void drawHealthBars(Graphics2D g, Pokemon pokemon, int index) {
		g.setColor(Color.GREEN);
		int pokemonHealthAsPercent = (int) (((double) pokemon.getHealth() / pokemon.getMaxHealth()) * 193);

		pokemonHealthAsPercent = pokemonHealthAsPercent > 0 ? pokemonHealthAsPercent : 0;

		if (pokemonHealthAsPercent <= 38.6) {
			g.setColor(Color.RED);
		} else if (pokemonHealthAsPercent <= 96.5) {
			g.setColor(Color.ORANGE);
		}
		g.fillRect(484, 100 * index + 34, pokemonHealthAsPercent, 15);
	}
}
