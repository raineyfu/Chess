
public abstract class Piece {
	protected boolean isWhite;
	protected int x;
	protected int y;
	protected Game g;
	public Piece(boolean isWhite, int x, int y) {
		this.isWhite = isWhite;
		this.x = x;
		this.y = y;
	}
	
	public void getMoves(Tile [] [] board) {
		
	}

	public boolean checkMove(int releasedY, int releasedX) {
		// TODO Auto-generated method stub
		return false;
	}
	public Tile [][] copyBoard(Tile [] [] board) {
		Tile [] [] newArray = new Tile [8][8];
		for (int x = 0; x < newArray.length; x++) {
			for (int y = 0; y < newArray[x].length; y++) {
				Tile temp = new Tile();
				temp.copy(board[x][y]);
				newArray[x][y] = temp;
			}
		}
		return newArray;
	}

	public boolean checkMoveM(int x2, int y2, Tile [][] board) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	

}
