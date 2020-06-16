/****************************************************************************
* Name:        Pokemon
* Author:      Rainey Fu and Kevin Song
* Date:        April 16, 2019
* Purpose:     An object used to store pokemon and their information
*****************************************************************************/
import java.awt.Graphics2D;
import java.awt.Image; 
import java.awt.Toolkit;
import java.net.URL;
import java.util.*;

public class Pokemon {
	private Game game; // the game that the pokemon is in
	public Sprite image; // pokemons image
	private Sprite pokemonIcon; // pokemons smaller image for pc
	private String name; // name of pokemon
	private int level = 1; // what level it is
	private int id; // what the id as number is
	private int health; // health value
	private int strength; // strength value
	private int defense; // defense value
	private int specialAttack; // sepecial attack value
	private int specialDefense; // special defense vlaue
	private int speed; // speed value
	private int type; // what type it is as int
	private int maxHealth; // maximum health for pokemon 
	private int evolvePokemonID; // the id of pokemon that this pokemon evolves to
	private int evolveLevel; // level neeeded to evolve
	
	public List<PokemonMoves> moveList = new ArrayList<PokemonMoves>(); // list of moves this pokemon has
	
	// Pokemon constructor when pokemon doesn't have evolution
	public Pokemon(Game game, String name, String imageLocation, int id, int health, int strength, int defense, int specialAttack, int specialDefense, int speed, int type) {
		this.game = game; // set game
		this.name = name; // set name
		this.id = id; // set id
		this.health = health; // set health
		this.maxHealth = health; // set max health
		this.strength = strength; // set strength
		this.defense = defense; // set defense
		this.specialAttack = specialAttack; // set special attack
		this.specialDefense = specialDefense; // set special defense
		this.speed = speed; // set speed
		this.type = type; // set type
		
		evolvePokemonID = -1; // set evolve id to -1 because this pokemon has no evolution
		
		String [] location = imageLocation.split("/");
		this.pokemonIcon = (SpriteStore.get()).getSprite(location[0] + "/" + "tinyIcons" + "/" + location[1]); // set tiny pokemonicon

		image = (SpriteStore.get()).getSprite(imageLocation); // get pokemon image
		
		// set pokemon moves based on what type the pokemon is
		// so that moves are the same type as the pokemon
		switch (type) {
			case 0: 
				addMove((int)(Math.random()*15));
				addMove((int)(Math.random()*15));
				addMove((int)(Math.random()*15));
				addMove((int)(Math.random()*15));
				break;
		
			case 1:
				addMove((int)(Math.random()*17) + 15);
				addMove((int)(Math.random()*17) + 15);
				addMove((int)(Math.random()*17) + 15);
				addMove((int)(Math.random()*17) + 15);
				break;
			case 2:
				addMove((int)(Math.random()*13) + 32);
				addMove((int)(Math.random()*13) + 32);
				addMove((int)(Math.random()*13) + 32);
				addMove((int)(Math.random()*13) + 32);
				break;
			case 3: 
				addMove((int)(Math.random()*20) + 45);
				addMove((int)(Math.random()*20) + 45);
				addMove((int)(Math.random()*20) + 45);
				addMove((int)(Math.random()*20) + 45);
				break;
			case 4: 
				addMove((int)(Math.random()*8) + 65);
				addMove((int)(Math.random()*8) + 65);
				addMove((int)(Math.random()*8) + 65);
				addMove((int)(Math.random()*8) + 65);
				break;	
			case 5:
				addMove((int)(Math.random()*29) + 73);
				addMove((int)(Math.random()*29) + 73);
				addMove((int)(Math.random()*29) + 73);
				addMove((int)(Math.random()*29) + 73);
				break;
			case 6:
				addMove((int)(Math.random()*15) + 102);
				addMove((int)(Math.random()*15) + 102);
				addMove((int)(Math.random()*15) + 102);
				addMove((int)(Math.random()*15) + 102);
				break;
			case 7:
				addMove((int)(Math.random()*19) + 117);
				addMove((int)(Math.random()*19) + 117);
				addMove((int)(Math.random()*19) + 117);
				addMove((int)(Math.random()*19) + 117);
				break;
			case 8:
				addMove((int)(Math.random()*12) + 136);
				addMove((int)(Math.random()*12) + 136);
				addMove((int)(Math.random()*12) + 136);
				addMove((int)(Math.random()*12) + 136);
				break;
			case 9:
				addMove((int)(Math.random()*26) + 148);
				addMove((int)(Math.random()*26) + 148);
				addMove((int)(Math.random()*26) + 148);
				addMove((int)(Math.random()*26) + 148);
				break;
			case 10:
				addMove((int)(Math.random()*11) + 174);
				addMove((int)(Math.random()*11) + 174);
				addMove((int)(Math.random()*11) + 174);
				addMove((int)(Math.random()*11) + 174);
				break;
			case 11:
				addMove((int)(Math.random()*19) + 185);
				addMove((int)(Math.random()*19) + 185);
				addMove((int)(Math.random()*19) + 185);
				addMove((int)(Math.random()*19) + 185);
				break;
			case 12:
				addMove((int)(Math.random()*22) + 204);
				addMove((int)(Math.random()*22) + 204);
				addMove((int)(Math.random()*22) + 204);
				addMove((int)(Math.random()*22) + 204);
				break;
			case 13:
				addMove((int)(Math.random()*13) + 226);
				addMove((int)(Math.random()*13) + 226);
				addMove((int)(Math.random()*13) + 226);
				addMove((int)(Math.random()*13) + 226);
				break;
			case 14:
				addMove((int)(Math.random()*10) + 239);
				addMove((int)(Math.random()*10) + 239);
				addMove((int)(Math.random()*10) + 239);
				addMove((int)(Math.random()*10) + 239);
				break;
			case 15:
				addMove((int)(Math.random()*22) + 249);
				addMove((int)(Math.random()*22) + 249);
				addMove((int)(Math.random()*22) + 249);
				addMove((int)(Math.random()*22) + 249);
				break;
			case 16:
				addMove((int)(Math.random()*12) + 261);
				addMove((int)(Math.random()*12) + 261);
				addMove((int)(Math.random()*12) + 261);
				addMove((int)(Math.random()*12) + 261);
				break;
			case 17:
				addMove((int)(Math.random()*16) + 273);
				addMove((int)(Math.random()*16) + 273);
				addMove((int)(Math.random()*16) + 273);
				addMove((int)(Math.random()*16) + 273);
		} // switch
		

	} // Pokemon
	
	// Pokemon constructor when pokemon has evolution
	public Pokemon(Game game, String name, String imageLocation, int id, int health, int strength, int defense, int specialAttack, int specialDefense, int speed, int type, int evolvePokemonID, int evolveLevel) {
		this.game = game; // set game
		this.id = id; // set id
		this.name = name; // set name
		this.health = health; // set health
		this.maxHealth = health; // set max health
		this.strength = strength; // set strength
		this.defense = defense; // set defense 
		this.specialAttack = specialAttack; // set special attack 
		this.specialDefense = specialDefense;// set special defense
		this.speed = speed; // set speed
		this.type = type; // set type
		this.evolvePokemonID = evolvePokemonID; // set evolve pokemon id
		this.evolveLevel = evolveLevel; // set level needed to evolve
		
		String [] location = imageLocation.split("/");
		this.pokemonIcon = (SpriteStore.get()).getSprite(location[0] + "/" + "tinyIcons" + "/" + location[1]); // set icon
		
		image = (SpriteStore.get()).getSprite(imageLocation); // set image
		
		// set pokemon moves based on what type the pokemon is
		// so that moves are the same type as the pokemon
		switch (type) {
			case 0:
				addMove((int)(Math.random()*15));
				addMove((int)(Math.random()*15));
				addMove((int)(Math.random()*15));
				addMove((int)(Math.random()*15));
				break;
		
			case 1:
				addMove((int)(Math.random()*17) + 15);
				addMove((int)(Math.random()*17) + 15);
				addMove((int)(Math.random()*17) + 15);
				addMove((int)(Math.random()*17) + 15);
				break;
			case 2:
				addMove((int)(Math.random()*13) + 32);
				addMove((int)(Math.random()*13) + 32);
				addMove((int)(Math.random()*13) + 32);
				addMove((int)(Math.random()*13) + 32);
				break;
			case 3: 
				addMove((int)(Math.random()*20) + 45);
				addMove((int)(Math.random()*20) + 45);
				addMove((int)(Math.random()*20) + 45);
				addMove((int)(Math.random()*20) + 45);
				break;
			case 4: 
				addMove((int)(Math.random()*8) + 65);
				addMove((int)(Math.random()*8) + 65);
				addMove((int)(Math.random()*8) + 65);
				addMove((int)(Math.random()*8) + 65);
				break;	
			case 5:
				addMove((int)(Math.random()*29) + 73);
				addMove((int)(Math.random()*29) + 73);
				addMove((int)(Math.random()*29) + 73);
				addMove((int)(Math.random()*29) + 73);
				break;
			case 6:
				addMove((int)(Math.random()*15) + 102);
				addMove((int)(Math.random()*15) + 102);
				addMove((int)(Math.random()*15) + 102);
				addMove((int)(Math.random()*15) + 102);
				break;
			case 7:
				addMove((int)(Math.random()*19) + 117);
				addMove((int)(Math.random()*19) + 117);
				addMove((int)(Math.random()*19) + 117);
				addMove((int)(Math.random()*19) + 117);
				break;
			case 8:
				addMove((int)(Math.random()*12) + 136);
				addMove((int)(Math.random()*12) + 136);
				addMove((int)(Math.random()*12) + 136);
				addMove((int)(Math.random()*12) + 136);
				break;
			case 9:
				addMove((int)(Math.random()*26) + 148);
				addMove((int)(Math.random()*26) + 148);
				addMove((int)(Math.random()*26) + 148);
				addMove((int)(Math.random()*26) + 148);
				break;
			case 10:
				addMove((int)(Math.random()*11) + 174);
				addMove((int)(Math.random()*11) + 174);
				addMove((int)(Math.random()*11) + 174);
				addMove((int)(Math.random()*11) + 174);
				break;
			case 11:
				addMove((int)(Math.random()*19) + 185);
				addMove((int)(Math.random()*19) + 185);
				addMove((int)(Math.random()*19) + 185);
				addMove((int)(Math.random()*19) + 185);
				break;
			case 12:
				addMove((int)(Math.random()*22) + 204);
				addMove((int)(Math.random()*22) + 204);
				addMove((int)(Math.random()*22) + 204);
				addMove((int)(Math.random()*22) + 204);
				break;
			case 13:
				addMove((int)(Math.random()*13) + 226);
				addMove((int)(Math.random()*13) + 226);
				addMove((int)(Math.random()*13) + 226);
				addMove((int)(Math.random()*13) + 226);
				break;
			case 14:
				addMove((int)(Math.random()*10) + 239);
				addMove((int)(Math.random()*10) + 239);
				addMove((int)(Math.random()*10) + 239);
				addMove((int)(Math.random()*10) + 239);
				break;
			case 15:
				addMove((int)(Math.random()*22) + 249);
				addMove((int)(Math.random()*22) + 249);
				addMove((int)(Math.random()*22) + 249);
				addMove((int)(Math.random()*22) + 249);
				break;
			case 16:
				addMove((int)(Math.random()*12) + 261);
				addMove((int)(Math.random()*12) + 261);
				addMove((int)(Math.random()*12) + 261);
				addMove((int)(Math.random()*12) + 261);
				break;
			case 17:
				addMove((int)(Math.random()*16) + 273);
				addMove((int)(Math.random()*16) + 273);
				addMove((int)(Math.random()*16) + 273);
				addMove((int)(Math.random()*16) + 273);
		} // switch
		
		
		
	} // Pokemon
	
	
	// default constructor to allow to object cloning
	public Pokemon () {
	
	} // Pokemon
	
	/*
	 * Increase pokemons level by one and increase stats randomly
	 */
	public void levelUp() {
		if (level < 100) {
			level++; // level

			maxHealth += (int) Math.random() * 3 + 1;
			strength += (int) Math.random() * 3 + 1;
			defense += (int) Math.random() * 3 + 1;
			specialAttack += (int) Math.random() * 3 + 1;
			specialDefense += (int) Math.random() * 3 + 1;
			speed += (int) Math.random() * 3 + 1;
			
		} // if
		checkEvolve();
	} // levelup
	
	/*
	 * change this pokemons level to a set value while increase its stats
	 */
	public void setLevel(int level) {
		while (this.level < level) {
			this.level++; // level
			
			maxHealth += (int) Math.random() * 3 + 1;
			health = maxHealth;
			strength += (int) Math.random() * 3 + 1;
			defense += (int) Math.random() * 3 + 1;
			specialAttack += (int) Math.random() * 3 + 1;
			specialDefense += (int) Math.random() * 3 + 1;
			speed += (int) Math.random() * 3 + 1;
		} // while
	} // setLevel

	/*
	 * Get speed for this pokemon
	 * @return the speed value of this pokemon
	 */
	public int getSpeed() {
		return speed;
	} // getSpeed
	
	/*
	 * Get the sprite for this pokemon
	 * @return the sprite for this pokemon
	 */
	public Sprite getSprite() {
		return image;
	} // getSprite
	
	/*
	 * Add a specific move for this pokemon
	 */
	public void addMove(int index) {
		PokemonMoves newMove = new PokemonMoves();
		
		newMove.clone(game.getCompleteMoveList().get(index));
		moveList.add(newMove);
	} // add move
	
	/*
	 * Get the move name for this pokemon
	 * @param the index for the specified move
	 * @return the move name from index
	 */
	public String getMoveName(int moveNumber) {
		return moveList.get(moveNumber).getString();
	} // getMoveName
	
	/*
	 * Get the type of the specified move
	 * @param the index for the move to get type for
	 * @return the int type value of the move
	 */
	public int getMoveType(int selected) {
		return moveList.get(selected).getType();
	} // getMoveType
	
	/*
	 * Get the health value for this pokemon
	 * @return the health value
	 */
	public int getHealth() {
		return health;
	} // getHealth

	/*
	 * Get the levelValue for this pokemon
	 * @return the level value
	 */
	public int getLevel() {
		return level;
	} // getLevel
	
	/*
	 * Get the defense value for this pokemon
	 * @return the defense value
	 */
	public int getDefense() {
		return defense;
	} // getDefense

	/*
	 * Get the strength value for this pokemon
	 * @return the strength value
	 */
	public int getStrength() {
		return strength;
	} // getStrength

	/*
	 * Get the type value for this pokemon
	 * @return the type value
	 */
	public int getType() {
		return type;
	} // getType

	/*
	 * Subtract health based on damage given
	 * @param the amount to subtract
	 */
	public void minusHealth(int damage) {
		this.health -= damage;
	} // minusHealth

	/*
	 * Get the max health value for this pokemon
	 * @return the max health value
	 */
	public int getMaxHealth() {
		return maxHealth;
	} // getMaxHealth
	
	/*
	 * Get the special attack value for this pokemon
	 * @return the special attack value
	 */
	public int getSpecialAttack() {
		return specialAttack;
	} // getSpecialAttack
	
	/*
	 * Get the special defense value for this pokemon
	 * @return the special defense value
	 */
	public int getSpecialDefense() {
		return specialDefense;
	} // getSpecialDefense

	/*
	 * Get the name for this pokemon
	 * @return the name
	 */
	public String getName() {
		return name;
	} // getName
	
	/*
	 * Set a new health value based on amount given
	 * @param the new health amount
	 */
	public void setHealth(int newHealth) {
		this.health = newHealth;
	} // setHealth
	
	/*
	 * Clone an pokemon object in is entirety 
	 * @param pokemon to clone from
	 */
	public void cloneObject(Pokemon pokemonToClone) {
		this.game = pokemonToClone.game;
		this.id = pokemonToClone.id;
		this.name = pokemonToClone.name;
		this.health = pokemonToClone.health;
		this.maxHealth = pokemonToClone.health;
		this.strength = pokemonToClone.strength;
		this.defense = pokemonToClone.defense;
		this.specialAttack = pokemonToClone.specialAttack;
		this.specialDefense = pokemonToClone.specialDefense;
		this.speed = pokemonToClone.speed;
		this.type = pokemonToClone.type;
		this.image = pokemonToClone.image;
		this.moveList = pokemonToClone.moveList;
		this.evolvePokemonID = pokemonToClone.evolvePokemonID;
		this.evolveLevel = pokemonToClone.evolveLevel;
		this.pokemonIcon = pokemonToClone.pokemonIcon;
	} // cloneObject
	
	/*
	 * Check if this pokemon should evolve
	 */
	public void checkEvolve() {
		if (evolvePokemonID != -1) { // if pokemon can evolve
			Pokemon evolvePokemon = game.getCompletePokemonList().get(evolvePokemonID - 1); // set this evolve pokemon
			Pokemon originalPokemon = game.getCompletePokemonList().get(this.id - 1); // set original pokemon
			
			// if level is sufficent
			if (this.level >= evolveLevel) {
				
				this.name = evolvePokemon.name; // change name to evolved name
				this.health = evolvePokemon.health + this.health - originalPokemon.health; // change health to evolved stats
				this.maxHealth = evolvePokemon.maxHealth + this.maxHealth - originalPokemon.health; // change health to evolved stats
				this.strength = evolvePokemon.strength + this.strength - originalPokemon.strength; // change strength to evolved stats
				this.defense = evolvePokemon.defense + this.defense - originalPokemon.defense; // change defense to evolved stats
				this.specialAttack = evolvePokemon.specialAttack + this.specialAttack - originalPokemon.specialAttack; // change special attack to evolved stats
				this.specialDefense = evolvePokemon.specialDefense + this.specialDefense - originalPokemon.specialDefense; // change special defense to evolved stats
				this.speed = evolvePokemon.speed + this.speed - originalPokemon.speed; // change speed to evolved stats
				this.type = evolvePokemon.type; // update type
				this.image = evolvePokemon.image; // update image
				this.moveList = evolvePokemon.moveList; // update move list
				
				game.setDrawEvolve(true); // draw evolve
				game.setInitEvolve(true); // init evolve
				game.setEvolvePokemonOriginal(originalPokemon); // set original pokemon
				game.setEvolvePokemonNew(evolvePokemon); // set evolve pokemon
			} // if
		} // if
	} // checkEvolve
	
	/*
	 * Get the pokemon icon for this pokemon
	 * @return the pokemon icon
	 */
	public Sprite getPokemonIcon() {
		return pokemonIcon;
	}

}
