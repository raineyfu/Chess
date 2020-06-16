
public class Rook extends Piece{
	private boolean moved = false;
	public Rook(boolean isWhite, int x, int y, boolean moved) {
		super(isWhite, x , y);
		this.moved = moved;
	}
	public String toString() {
		if (isWhite) {
			return "R";
		} else {
			return "r";
		}
	}
	
	public boolean getMoved() {
		return moved;
	}
	
	public void getMoves(Tile [] [] board) {
		Tile [] [] temp = copyBoard(board);
		int counter = 1;
		boolean [] validLine = new boolean [4];
		while (counter < 8) {
			if (this.x - (counter * 1) < 0) {
				validLine[0] = true;
			} else if (temp[this.x - (counter * 1)][this.y].getPiece() != null) {
				validLine[0] = true;
			} 
			
			if (this.x + (counter * 1) > 7) {
				validLine[1] = true;
			} else if (temp[this.x + (counter * 1)][this.y].getPiece() != null) {
				validLine[1] = true;
			} 
			
			if (this.y - (counter * 1) < 0) {
				validLine[2] = true;
			} else if (temp[this.x][this.y - (counter * 1)].getPiece() != null) {
				validLine[2] = true;
			} 
			
			if (this.y + (counter * 1) > 7) {
				validLine[3] = true;
			} else if (temp[this.x][this.y + (counter * 1)].getPiece() != null) {
				validLine[3] = true;
			}
			if (validLine[0] && validLine[1] && validLine[2] && validLine[3]) {
				break;
			}
			if (!validLine[0]) {
				temp[x - (counter * 1)][y].setPiece((new Rook(isWhite, x - (counter * 1), y, true)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			if (!validLine[1]) {
				temp[x + (counter * 1)][y].setPiece((new Rook(isWhite, x + (counter * 1), y, true)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			if (!validLine[2]) {
				temp[x][y - (counter * 1)].setPiece((new Rook(isWhite, x, y - (counter * 1), true)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			if (!validLine[3]) {
				temp[x][y + (counter * 1)].setPiece((new Rook(isWhite, x, y + (counter * 1), true)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			counter++;
		}
	}
	public boolean checkMoveM(int x, int y, Tile [][] board) {
		Tile [][] temp = board.clone();
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		} else {
			int counter = 1;
			boolean [] validLine = new boolean [4];
			while (counter < 8) {
				if (this.x - (counter * 1) == x && this.y == y && !validLine[0]) {
					return true;
				} else if (this.x + (counter * 1) == x && this.y == y && !validLine[1]) {
					return true;
				} else if (this.x == x && this.y - (counter * 1) == y && !validLine[2]) {
					return true;
				} else if (this.x == x && this.y + (counter * 1) == y && !validLine[3]) {
					return true;
				}
				if (this.x - (counter * 1) < 0) {
					validLine[0] = true;
				} else if (temp[this.x - (counter * 1)][this.y].getPiece() != null) {
					validLine[0] = true;
				} 
				
				if (this.x + (counter * 1) > 7) {
					validLine[1] = true;
				} else if (temp[this.x + (counter * 1)][this.y].getPiece() != null) {
					validLine[1] = true;
				} 
				
				if (this.y - (counter * 1) < 0) {
					validLine[2] = true;
				} else if (temp[this.x][this.y - (counter * 1)].getPiece() != null) {
					validLine[2] = true;
				} 
				
				if (this.y + (counter * 1) > 7) {
					validLine[3] = true;
				} else if (temp[this.x][this.y + (counter * 1)].getPiece() != null) {
					validLine[3] = true;
				}
				if (validLine[0] && validLine[1] && validLine[2] && validLine[3]) {
					return false;
				}
				counter++;
			}
			return false;
		}
	}
	public void checkCastle() {
		if (!moved) {
			if (isWhite) {
				if (this.x == 7 && this.y == 0) {
					Game.whiteQCastle = false;
				}
				if (this.x == 7 && this.y == 7) {
					Game.whiteKCastle = false;
				}
			} else {
				if (this.x == 0 && this.y == 0) {
					Game.blackQCastle = false;
				}
				if (this.x == 0 && this.y == 7) {
					Game.blackKCastle = false;
				}
			}
		}
		moved = true;
	}
	public boolean checkMove(int x, int y) {
		Tile [] [] temp = Game.getBoard();
		if (temp[x][y].getPiece() != null && temp[x][y].getPiece().isWhite == this.isWhite) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		} else {
			int counter = 1;
			boolean [] validLine = new boolean [4];
			while (counter < 8) {
				if (this.x - (counter * 1) == x && this.y == y && !validLine[0]) {
					checkCastle();
					this.x = x;
					this.y = y;
					return true;
				} else if (this.x + (counter * 1) == x && this.y == y && !validLine[1]) {
					checkCastle();
					this.x = x;
					this.y = y;
					return true;
				} else if (this.x == x && this.y - (counter * 1) == y && !validLine[2]) {
					checkCastle();
					this.x = x;
					this.y = y;
					return true;
				} else if (this.x == x && this.y + (counter * 1) == y && !validLine[3]) {
					checkCastle();
					this.x = x;
					this.y = y;
					return true;
				}
				if (this.x - (counter * 1) < 0) {
					validLine[0] = true;
				} else if (temp[this.x - (counter * 1)][this.y].getPiece() != null) {
					validLine[0] = true;
				} 
				
				if (this.x + (counter * 1) > 7) {
					validLine[1] = true;
				} else if (temp[this.x + (counter * 1)][this.y].getPiece() != null) {
					validLine[1] = true;
				} 
				
				if (this.y - (counter * 1) < 0) {
					validLine[2] = true;
				} else if (temp[this.x][this.y - (counter * 1)].getPiece() != null) {
					validLine[2] = true;
				} 
				
				if (this.y + (counter * 1) > 7) {
					validLine[3] = true;
				} else if (temp[this.x][this.y + (counter * 1)].getPiece() != null) {
					validLine[3] = true;
				}
				if (validLine[0] && validLine[1] && validLine[2] && validLine[3]) {
					return false;
				}
				counter++;
			}
			return false;
		}
	}
}
