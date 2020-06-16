import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokemonMoves {
	private String moveName;
	private int type;
	private int pp;
	private int power;
	private int accuracy;
	private int maxPP;
	private int statusMove;
	private long animationTime;

	public double[][] typeCounters = { { 1, 2, 1, 1, 0.5, 0.5, 0.5, 0.5, 0.5, 2, 1, 1, 0.5, 2, 1, 0.5, 1, 0.5 }, // bug
			{ 1, 1, 1, 1, 0.5, 0.5, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, }, // dark
			{ 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, }, // dragon
			{ 1, 1, 0.5, 1, 1, 1, 1, 2, 1, 0.5, 0, 1, 1, 1, 1, 1, 1, 2 }, // electric
			{ 1, 2, 2, 1, 1, 2, 0.5, 1, 1, 1, 1, 1, 1, 0.5, 1, 1, 0.5, 1 }, // fairy
			{ 0.5, 2, 1, 1, 0.5, 1, 1, 0.5, 0, 1, 1, 2, 2, 0.5, 0.5, 2, 2, 1 }, // fighting
			{ 2, 1, 0.5, 1, 1, 1, 0.5, 1, 1, 2, 1, 2, 1, 1, 1, 0.5, 2, 0.5 }, // fire
			{ 2, 1, 1, 0.5, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0.5, 0.5, 1 }, // flying
			{ 1, 0.5, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 1, 2, 1, 1, 1 }, // ghost
			{ 0.5, 1, 0.5, 1, 0.5, 1, 1, 0.5, 1, 0.5, 2, 1, 1, 0.5, 1, 2, 0.5, 2 }, // grass // fixed
			{ 0.5, 1, 1, 2, 1, 1, 2, 0, 1, 0.5, 1, 1, 1, 2, 1, 2, 2, 1 }, // ground // fixed
			{ 1, 1, 2, 1, 1, 1, 0.5, 2, 1, 2, 2, 0.5, 1, 1, 1, 1, 0.5, 0.5 }, // ice
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0.5, 0.5, 1 }, // normal // fixed
			{ 1, 1, 1, 1, 2, 1, 1, 1, 0.5, 2, 0.5, 1, 1, 0.5, 1, 0.5, 2, 1 }, // poison fixed
			{ 1, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 0.5, 1, 0.5, 1 }, // physic fixed
			{ 2, 1, 1, 1, 1, 0.5, 2, 2, 1, 1, 0.5, 2, 1, 1, 1, 1, 0.5, 1 }, // rock fixed
			{ 1, 1, 1, 0.5, 2, 1, 0.5, 1, 1, 1, 1, 2, 1, 1, 1, 2, 0.5, 0.5 }, // steel
			{ 1, 1, 0.5, 1, 1, 1, 2, 1, 1, 0.5, 2, 1, 1, 1, 1, 2, 1, 0.5 } // water
	}; // 16

	public PokemonMoves(String moveName, int maxPP, int pp, int power, int accuracy, int type) {
		this.moveName = moveName;
		this.type = type;
		this.maxPP = maxPP;
		this.pp = pp;
		this.power = power;
		this.accuracy = accuracy;
	}

	public PokemonMoves() {

	}

	public void statusMove() {
	}

	public String getString() {
		return moveName;
	}

	public int getType() {
		return type;
	}

	public int getPower() {
		return power;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getStatusMove() {
		return statusMove;
	}

	public long getAnimationTime() {
		return animationTime;
	}

	public int getPP() {
		return pp;
	}

	public int getMaxPP() {
		return maxPP;
	}

	public void setPP(int newPP) {
		this.pp = newPP;
	}

	public void clone(PokemonMoves moveToClone) {
		this.moveName = moveToClone.moveName;
		this.type = moveToClone.type;
		this.maxPP = moveToClone.maxPP;
		this.pp = moveToClone.pp;
		this.power = moveToClone.power;
		this.accuracy = moveToClone.accuracy;
	}

}
