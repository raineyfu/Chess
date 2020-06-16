
public class King extends Piece{
	private boolean moved = false;
	public King(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}
	
	public String toString() {
		if (isWhite) {
			return "K";
		} else {
			return "k";
		}
	}
	
	public void getMoves(Tile [] [] board) {
		checkMoveValid(x - 1, y, board);
		checkMoveValid(x - 1, y + 1, board);
		checkMoveValid(x, y + 1, board);
		checkMoveValid(x + 1, y + 1, board);
		checkMoveValid(x + 1, y, board);
		checkMoveValid(x + 1, y - 1, board);
		checkMoveValid(x, y - 1, board);
		checkMoveValid(x - 1, y - 1, board);
	}
	private void checkMoveValid(int x, int y, Tile [] [] board) {
		Tile [] [] temp = copyBoard(board);
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return;
		}
		System.out.println(temp[x][y].getPiece());
		if (temp[x][y].getPiece() != null) {
			if (temp[x][y].getPiece().isWhite != this.isWhite) {
				temp[x][y].setPiece(new King(isWhite, x, y));
				temp[this.x][this.y].setPiece(null);
				Game.moveList.add(temp);
			}
		} else {
			temp[x][y].setPiece(new King(isWhite, x, y));
			temp[this.x][this.y].setPiece(null);
			Game.moveList.add(temp);
		}
		
	}
	public boolean checkMove(int x, int y) {
		Tile [] [] temp = Game.getBoard();
		if (temp[x][y].getPiece() != null && temp[x][y].getPiece().isWhite == this.isWhite) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		if ((((this.x - 1== x) || (this.x + 1 == x)) && ((this.y - 1 == y) || this.y + 1 == y)) || (((this.x - 1 == x) || this.x + 1 == x) && (this.y == y))
			|| (((this.y - 1 == y) || (this.y + 1 == y)) && (this.x == x))) {
			this.x = x;
			this.y = y;
			return true;
		} else if ((isWhite && this.x == 7 && this.y == 4 && x == 7 && y == 6 && temp[7][7].getPiece() instanceof Rook && 
				!((Rook) temp[7][7].getPiece()).getMoved() && temp[7][5].getPiece() == null && temp[7][6].getPiece() == null)) {
			this.x = x;
			this.y = y;
			Game.wRCastle();
			return true;
		} else if (isWhite && this.x == 7 && this.y == 4 && x == 7 && y == 2 && temp[7][0].getPiece() instanceof Rook && 
				!((Rook) temp[7][0].getPiece()).getMoved() && temp[7][1].getPiece() == null && temp[7][2].getPiece() == null 
				&& temp[7][3].getPiece() == null) {
			this.x = x;
			this.y = y;
			Game.wQCastle();
			return true;
		} else if ((!isWhite && x == 0 && y == 6 && this.x == 0 && this.y == 4 && temp[0][7].getPiece() instanceof Rook && 
				!((Rook) temp[0][7].getPiece()).getMoved() && temp[0][6].getPiece() == null && temp[0][5].getPiece() == null)) {	
			this.x = x;
			this.y = y;
			Game.bRCastle();
			return true;
		} else if (!isWhite && x == 0 && y == 2 && this.x == 0 && this.y == 4 && temp[0][0].getPiece() instanceof Rook &&
				!((Rook) temp[0][7].getPiece()).getMoved() && temp[0][1].getPiece() == null && temp[0][2].getPiece() == null
				&& temp[0][3].getPiece() == null) {
			this.x = x;
			this.y = y;
			Game.bQCastle();
			return true;
		} else {
			return false;
		}
	}
}
