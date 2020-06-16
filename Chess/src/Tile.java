
public class Tile {
	private Piece piece;
	private int x; 
	private int y;
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Tile(Piece piece, int x, int y) {
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	public Tile() {
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public void copy(Tile tile) {
		this.x = tile.x;
		this.y = tile.y;
		this.piece = tile.piece;
	}
}
