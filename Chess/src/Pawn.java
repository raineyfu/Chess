import java.util.Arrays;

public class Pawn extends Piece{
	public Pawn(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}
	public String toString() {
		if (isWhite) {
			return "P";
		} else {
			return "p";
		}
	}
	
	public void moveForwardPawn() {
		if (isWhite) {
			Game.getBoard()[x][y].setPiece(null);
			Game.getBoard()[x - 1][y].setPiece(this);
		} else {
			Game.getBoard()[x][y].setPiece(null);
			Game.getBoard()[x + 1][y].setPiece(this);
		}
	}
	
	public void getMoves(Tile [] [] board) {
		Tile [] [] temp = copyBoard(board);
		if (isWhite) {
			if (x - 1 >= 0 && temp[x -1][y].getPiece() == null) {
				if (x - 1 == 0) {
					temp[x -1][y].setPiece(new Queen(isWhite, x -1, y));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
					} else {
					temp[x -1][y].setPiece(new Pawn(isWhite, x -1 , y));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
				
				if (x - 2 >= 0 && temp[x - 2][y].getPiece() == null && x == 6) {
					temp[x -2][y].setPiece(new Pawn(isWhite, x - 2, y));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
			}
			if (x - 1 >= 0 && y - 1 >= 0 && temp[x - 1][y - 1].getPiece() != null && temp[x - 1][y -1].getPiece().isWhite != this.isWhite) {
				if (x - 1 == 0) {
					temp[x -1][y -1 ].setPiece(new Queen(isWhite, x -1, y-1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				} else {
					temp[x -1][y -1].setPiece(new Pawn(isWhite, x - 1, y - 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
			}
			if (x - 1 >= 0 && y + 1 <= 7 && temp[x - 1][y + 1].getPiece() != null && temp[x - 1][y +1].getPiece().isWhite != this.isWhite) {
				if (x - 1 == 0) {
					temp[x -1][y + 1].setPiece(new Queen(isWhite, x -1, y + 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				} else {
					temp[x -1][y + 1].setPiece(new Pawn(isWhite, x - 1, y + 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
			}
		} else {
			if (x + 1 <= 7 && temp[x + 1][y].getPiece() == null) {
				if (x + 1 == 7) {
					temp[x + 1][y].setPiece(new Queen(isWhite, x + 1, y));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				} else {
					temp[x + 1][y].setPiece(new Pawn(isWhite, x + 1 , y));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
				
				if (x + 2 <= 7 && temp[x + 2][y].getPiece() == null && x == 1) {
					temp[x + 2][y].setPiece(new Pawn(isWhite, x + 2, y));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
			}
			if (x + 1 <= 7 && y - 1 >= 0 && temp[x + 1][y - 1].getPiece() != null && temp[x + 1][y -1].getPiece().isWhite != this.isWhite) {
				if (x + 1 == 7) {
					temp[x + 1][y - 1].setPiece(new Queen(isWhite, x + 1, y - 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				} else {
					temp[x + 1][y - 1].setPiece(new Pawn(isWhite, x + 1, y - 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
			}
			if (x + 1 <= 7 && y + 1 <= 7 && temp[x + 1][y + 1].getPiece() != null && temp[x + 1][y +1].getPiece().isWhite != this.isWhite) {
				if (x + 1 == 7) {
					temp[x + 1][y + 1].setPiece(new Queen(isWhite, x + 1, y + 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				} else {
					temp[x + 1][y + 1].setPiece(new Pawn(isWhite, x + 1, y + 1));
					temp[x][y].setPiece(null);
					Game.moveList.add(temp);
					temp = copyBoard(board);
				}
			}
		}
	}
	
	public boolean checkMove(int newX, int newY) {
		Tile [] [] temp = Game.getBoard();
		if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
			return false;
		}
		if (isWhite) {
			if (newY == y && newX == x - 1 && temp[newX][newY].getPiece() == null) {
				if (newX == 0) {
					Game.clickedPiece = new Queen(isWhite, newX, newY);
				}
				this.x = newX;
				this.y = newY;
				return true;
			} else if (y == newY && newX == x - 2 && temp[newX][newY].getPiece() == null && temp[x - 1][newY].getPiece() == null && this.x == 6) {
				this.x = newX;
				this.y = newY;
				return true;
			} 
			if (((newX == x - 1 && newY == y - 1) && temp[x-1][y-1].getPiece() != null && temp[x-1][y-1].getPiece().isWhite != isWhite)
					|| ((newX == x - 1 && newY == y + 1) && temp[x-1][y+1].getPiece() != null && temp[x-1][y+1].getPiece().isWhite != isWhite)) {
				if (newX == 0) {
					Game.clickedPiece = new Queen(isWhite, newX, newY);
				}
				this.x = newX;
				this.y = newY;
				return true;
			}
			return false;
		} else {
			if (newY == y && newX == x + 1 && temp[newX][newY].getPiece() == null) {
				if (newX == 7) {
					Game.clickedPiece = new Queen(isWhite, newX, newY);
				}
				this.x = newX;
				this.y = newY;
				return true;
			} else if (y == newY && newX == x + 2 && temp[newX][newY].getPiece() == null && temp[x + 1][newY].getPiece() == null && this.x == 1) {
				this.x = newX;
				this.y = newY;
				return true;
			} 
			if (((newX == x + 1 && newY == y - 1) && temp[x + 1][y-1].getPiece() != null && temp[x + 1][y-1].getPiece().isWhite != isWhite)
					|| ((newX == x + 1 && newY == y + 1) && temp[x + 1][y+1].getPiece() != null && temp[x + 1][y+1].getPiece().isWhite != isWhite)) {
				if (newX == 7) {
					Game.clickedPiece = new Queen(isWhite, newX, newY);
				}
				this.x = newX;
				this.y = newY;
				return true;
			}
			return false;
		}
	}
	
}
