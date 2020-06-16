
public class Bishop extends Piece{
	public Bishop(boolean isWhite, int x, int y) {
		super(isWhite, x, y);
	}
	
	public String toString() {
		if (isWhite) {
			return "B";
		} else {
			return "b";
		}
	}
	
	public void getMoves(Tile [] [] board) {
		Tile [] [] temp = copyBoard(board);
		int counter = 1;
		boolean [] validCorner = new boolean [4];
		
		while (counter < 8) {
			if (this.x - (counter * 1) < 0 || this.y - (counter * 1) < 0) {
				validCorner[0] = true;
			} else if (temp[this.x - (counter * 1)][this.y - (counter * 1)].getPiece() != null) {
				validCorner[0] = true;
			} 
			
			if (this.x - (counter * 1) < 0 || this.y + (counter * 1) > 7) {
				validCorner[1] = true;
			} else if (temp[this.x - (counter * 1)][this.y + (counter * 1)].getPiece() != null) {
				validCorner[1] = true;
			} 
			
			if (this.x + (counter * 1) > 7 || this.y - (counter * 1) < 0) {
				validCorner[2] = true;
			} else if (temp[this.x + (counter * 1)][this.y - (counter * 1)].getPiece() != null) {
				validCorner[2] = true;
			} 
			
			if (this.x + (counter * 1) > 7 || this.y + (counter * 1) > 7) {
				validCorner[3] = true;
			} else if (temp[this.x + (counter * 1)][this.y + (counter * 1)].getPiece() != null) {
				validCorner[3] = true;
			}
			if (validCorner[0] && validCorner[1] && validCorner[2] && validCorner[3]) {
				break;
			}
			
			if (!validCorner[0]) {
				temp[x - (counter * 1)][y - (counter * 1)].setPiece(new Bishop(isWhite, x - (counter * 1), y - (counter * 1)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			if (!validCorner[1]) {
				temp[x - (counter * 1)][y + (counter * 1)].setPiece(new Bishop(isWhite, x - (counter * 1), y + (counter * 1)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			if (!validCorner[2]) {
				temp[x + (counter * 1)][y - (counter * 1)].setPiece(new Bishop(isWhite, x + (counter * 1), y - (counter * 1)));
				temp[x][y].setPiece(null);
				Game.moveList.add(temp);
				temp = copyBoard(board);
			}
			
			if (!validCorner[3]) {
				temp[x + (counter * 1)][y + (counter * 1)].setPiece(new Bishop(isWhite, x + (counter * 1), y + (counter * 1)));
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
			boolean [] validCorner = new boolean [4];
			while (counter < 8) {
				if (this.x - (counter * 1) == x && this.y - (counter * 1) == y && !validCorner[0]) {
					return true;
				} else if (this.x - (counter * 1) == x && this.y + (counter * 1) == y && !validCorner[1]) {
					return true;
				} else if (this.x + (counter * 1) == x && this.y - (counter * 1) == y && !validCorner[2]) {
					return true;
				} else if (this.x + (counter * 1) == x && this.y + (counter * 1) == y && !validCorner[3]) {
					return true;
				}
				if (this.x - (counter * 1) < 0 || this.y - (counter * 1) < 0) {
					validCorner[0] = true;
				} else if (temp[this.x - (counter * 1)][this.y - (counter * 1)].getPiece() != null) {
					validCorner[0] = true;
				} 
				
				if (this.x - (counter * 1) < 0 || this.y + (counter * 1) > 7) {
					validCorner[1] = true;
				} else if (temp[this.x - (counter * 1)][this.y + (counter * 1)].getPiece() != null) {
					validCorner[1] = true;
				} 
				
				if (this.x + (counter * 1) > 7 || this.y - (counter * 1) < 0) {
					validCorner[2] = true;
				} else if (temp[this.x + (counter * 1)][this.y - (counter * 1)].getPiece() != null) {
					validCorner[2] = true;
				} 
				
				if (this.x + (counter * 1) > 7 || this.y + (counter * 1) > 7) {
					validCorner[3] = true;
				} else if (temp[this.x + (counter * 1)][this.y + (counter * 1)].getPiece() != null) {
					validCorner[3] = true;
				}
				if (validCorner[0] && validCorner[1] && validCorner[2] && validCorner[3]) {
					return false;
				}
				counter++;
			}
			return false;
		}
	}
	public boolean checkMove(int x, int y) {
		Tile [][] temp = Game.getBoard();
		
		if (temp[x][y].getPiece() != null && temp[x][y].getPiece().isWhite == this.isWhite) {
			return false;
		}
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		} else {
			int counter = 1;
			boolean [] validCorner = new boolean [4];
			while (counter < 8) {
				if (this.x - (counter * 1) == x && this.y - (counter * 1) == y && !validCorner[0]) {
					this.x = x;
					this.y = y;
					return true;
				} else if (this.x - (counter * 1) == x && this.y + (counter * 1) == y && !validCorner[1]) {
					this.x = x;
					this.y = y;
					return true;
				} else if (this.x + (counter * 1) == x && this.y - (counter * 1) == y && !validCorner[2]) {
					this.x = x;
					this.y = y;
					return true;
				} else if (this.x + (counter * 1) == x && this.y + (counter * 1) == y && !validCorner[3]) {
					this.x = x;
					this.y = y;
					return true;
				}
				if (this.x - (counter * 1) < 0 || this.y - (counter * 1) < 0) {
					validCorner[0] = true;
				} else if (temp[this.x - (counter * 1)][this.y - (counter * 1)].getPiece() != null) {
					validCorner[0] = true;
				} 
				
				if (this.x - (counter * 1) < 0 || this.y + (counter * 1) > 7) {
					validCorner[1] = true;
				} else if (temp[this.x - (counter * 1)][this.y + (counter * 1)].getPiece() != null) {
					validCorner[1] = true;
				} 
				
				if (this.x + (counter * 1) > 7 || this.y - (counter * 1) < 0) {
					validCorner[2] = true;
				} else if (temp[this.x + (counter * 1)][this.y - (counter * 1)].getPiece() != null) {
					validCorner[2] = true;
				} 
				
				if (this.x + (counter * 1) > 7 || this.y + (counter * 1) > 7) {
					validCorner[3] = true;
				} else if (temp[this.x + (counter * 1)][this.y + (counter * 1)].getPiece() != null) {
					validCorner[3] = true;
				}
				if (validCorner[0] && validCorner[1] && validCorner[2] && validCorner[3]) {
					return false;
				}
				counter++;
			}
			return false;
		}
		
	}

}
