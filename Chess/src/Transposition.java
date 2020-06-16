
public class Transposition {
	private int evaluation;
	private int depth;
	private int type;
	
	public Transposition(int evaluation, int depth, int type) {
		this.evaluation = evaluation;
		this.depth = depth;
		this.type = type;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public int getType() {
		return type;
	}
	public int getDepth() {
		return depth;
	}
}
