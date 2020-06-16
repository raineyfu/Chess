/****************************************************************************
* Name:        BattleItems
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     Class to implement the use of items during a battle
*****************************************************************************/

import java.awt.Graphics;

public class BattleItems {
	private Game game;
	private String itemName;
	private int healAmount;
	private int cost;
	private int amountOfItem = 0;
	private int catchPercentage;
	private Sprite sprite;
	private String description;

	public BattleItems(Game game, String name, String r, int healAmount, int catchPercentage, int cost,
			String description) {
		this.game = game;
		itemName = name;
		this.healAmount = healAmount;
		this.catchPercentage = catchPercentage;
		this.cost = cost;

		sprite = (SpriteStore.get()).getSprite(r);
		this.description = description;
	}

	public BattleItems() {

	}

	public void useHeal(Pokemon pokemon) {
		pokemon.minusHealth(-healAmount);
		if (pokemon.getHealth() > pokemon.getMaxHealth()) {
			pokemon.setHealth(pokemon.getMaxHealth());
		}
	}

	public void checkCatch(Pokemon enemy) {
		int randomGen = (int) Math.random() * 255 + 1;


		randomGen -= enemy.getHealth() / enemy.getMaxHealth();

	}

	public void statusItem(Pokemon pokemon) {

	}

	public void useBall(Pokemon pokemonToCatch) {
		int randomGen = (int) (Math.random() * 100 + 1);
		if (randomGen <= getCatchPercentage()) {
			game.getTextBox().addMessageText("You caught " + pokemonToCatch.getName() + "!\n");
			if (game.getPokemonList().size() < 6) {
				game.getPokemonList().add(pokemonToCatch);
			} else {
				game.getPCPokemonList().add(pokemonToCatch);
				game.getTextBox().addMessageText("Your party is full right now! \nIt has been added to your pc.");
			}
			game.setDrawBag(false);
			game.getBattleScreen().setCaughtPokemon(true);
			game.setDrawBattle(false);
			game.getEnemyPokemonList().clear();
		} else {
			game.getTextBox().addMessageText("You used " + itemName + " but it failed!\n");
		}
		removeOneFromAmount();
	}

	public Sprite getSprite() {
		return sprite;
	}

	public String getDescription() {
		return description;
	}

	public int getAmountOfItem() {
		return amountOfItem;
	}

	public String getItemName() {
		return itemName;
	}

	public int getHealthAmount() {
		return healAmount;
	}

	public int getCatchPercentage() {
		return catchPercentage;
	}

	public int getCost() {
		return cost;
	}

	public void addOneToAmount() {
		amountOfItem++;
	}
	
	public void removeOneFromAmount() {
		amountOfItem--;
	}

}
