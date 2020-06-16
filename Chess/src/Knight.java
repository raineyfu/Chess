
public class Knight extends Piece{
	public Knight(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}
	public String toString() {
		if (isWhite) {
			return "N";
		} else {
			return "n";
		}
	}
	
	public void getMoves(Tile [] [] board) {
		checkMoveValid(this.x - 2, y + 1, board);
		checkMoveValid(this.x - 1, y + 2, board);
		checkMoveValid(this.x + 1, y + 2, board);
		checkMoveValid(this.x + 2, y + 1, board);
		checkMoveValid(this.x + 2, y - 1, board);
		checkMoveValid(this.x + 1, y - 2, board);
		checkMoveValid(this.x - 1, y - 2, board);
		checkMoveValid(this.x - 2, y - 1, board);
	}
	
	private void checkMoveValid(int x, int y, Tile [] [] board) {
		Tile [] [] temp = copyBoard(board);
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return;
		}
		if (temp[x][y].getPiece() != null) {
			if (temp[x][y].getPiece().isWhite != this.isWhite) {
				temp[x][y].setPiece(new Knight(isWhite, x, y));
				temp[this.x][this.y].setPiece(null);
				Game.moveList.add(temp);
			}
		} else {
			temp[x][y].setPiece(new Knight(isWhite, x, y));
			temp[this.x][this.y].setPiece(null);
			Game.moveList.add(temp);
		}
		
	}
	public boolean checkMoveM(int x, int y, Tile [][] board) {
		Tile [][] temp = board.clone();
		if (x > 7 || x < 0|| y > 7 || y < 0) {
			return false;
		} else {
			if ((x == this.x - 2 && y == this.y + 1) || (x == this.x - 1 && y == this.y + 2) || (x == this.x + 1 && y == this.y + 2) ||
				(x == this.x + 2 && y == this.y + 1) || (x == this.x + 2 && y == this.y - 1) || (x == this.x + 1 && y == this.y - 2) ||
				(x == this.x - 1 && y == this.y - 2) || (x == this.x - 2 && y == this.y - 1)) {
				return true;
			} else {
				return false;
			}
		}
	}
	public boolean checkMove(int x, int y) {
		Tile [] [] temp = Game.getBoard();
		if (temp[x][y].getPiece() != null && temp[x][y].getPiece().isWhite == this.isWhite) {
			return false;
		}
		if (x > 7 || x < 0|| y > 7 || y < 0) {
			return false;
		} else {
			if ((x == this.x - 2 && y == this.y + 1) || (x == this.x - 1 && y == this.y + 2) || (x == this.x + 1 && y == this.y + 2) ||
				(x == this.x + 2 && y == this.y + 1) || (x == this.x + 2 && y == this.y - 1) || (x == this.x + 1 && y == this.y - 2) ||
				(x == this.x - 1 && y == this.y - 2) || (x == this.x - 2 && y == this.y - 1)) {
				this.x = x;
				this.y = y;
				return true;
			} else {
				return false;
			}
		}
	}
}
