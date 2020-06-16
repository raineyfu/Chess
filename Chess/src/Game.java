import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.net.URL;

import javax.swing.*;


import java.util.*;
import java.awt.*;

public class Game extends Canvas{
	private BufferStrategy strategy;
	private Graphics2D g;
	private static Tile [] [] board = new Tile[8][8];
	private static Tile [] [] evalBoard = new Tile[8][8];
	private int evalScore = 0;
	public static ArrayList<Tile [][]> moveList = new ArrayList<Tile [] []>();
	private static boolean isWhiteTurn = true;
	private Image boardImage;
	private Image [] whiteImages = new Image [6];
	private Image [] blackImages = new Image [6];
	private static int fullMoveCount = 0;
	 static boolean whiteQCastle = true;
	 static boolean whiteKCastle = true;
	 static boolean blackQCastle = true;
	 static boolean blackKCastle = true;
	
	private Evaluation evaluation = new Evaluation();
	
	
	static Piece clickedPiece;
	private int clickedX;
	private int clickedY;
	private int currentX;
	private int currentY;
	
	public static void main (String args[]) {
		new Game();
	}
	private Game() {
		JFrame container = new JFrame("");

		// get hold the content of the frame
		JPanel panel = (JPanel) container.getContentPane();

		// set up the resolution of the game
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setLayout(null);

		// set up canvas size (this) and add to frame
		setBounds(0, 0, 1920, 1080);
		panel.add(this);

		// Tell AWT not to bother repainting canvas since that will
		// be done using graphics acceleration
		setIgnoreRepaint(true);

		// make the window visible
		//container.setUndecorated(true);
		container.pack();
		container.setResizable(true);
		container.setVisible(true);
		container.setExtendedState(container.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// if user closes window, shutdown game and jre
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // windowClosing
		});

		// add key listener to this canvas
		//addKeyListener(new KeyInputHandler());
		addMouseListener(new MouseClick());
		addMouseMotionListener(new MouseMove());
		// request focus so key events are handled by this canvas
		requestFocus();

		// create buffer strategy to take advantage of accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		initBoard();
		printBoard(board);
		
		
		evalBoard = evaluation.evaluate(isWhiteTurn);
		printBoard(evalBoard);
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				if (board[x][y].getPiece() != null) {
					System.out.print(board[x][y].getPiece().x + "-" + board[x][y].getPiece().y + " ");
				} else {
					System.out.print("0");
				}
			}
			System.out.println("");
		}
		gameLoop();
		
	} // constructor
	static ArrayList<Tile[][]> getMoveList(Tile [] [] board, boolean isWhiteTurn) {
		moveList.clear();
		if (isWhiteTurn) {
			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board[0].length; y++) {
					if (board[x][y].getPiece() != null && board[x][y].getPiece().isWhite) {
						board[x][y].getPiece().getMoves(board);
					}
				}
			}
		} else {
			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board[0].length; y++) {
					if (board[x][y].getPiece() != null && !board[x][y].getPiece().isWhite) {
						board[x][y].getPiece().getMoves(board);
					}
				}
			}
		}
		return moveList;
	}
	
	public ArrayList<Tile [] []> returnMoveList() {
		return moveList;
	}
	private void gameLoop() {
		while (true) {
			
			g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect	(0, 0, 1920, 1080);
			
			
			g.drawImage(boardImage, 0, 0, null);
			
			for (int x = 0; x < board.length; ++x) {
				for (int y = 0; y < board[0].length; ++y) {
					if (board[x][y].getPiece() == clickedPiece) {
						continue;
					}
					if (board[x][y].getPiece() != null) {
						if (board[x][y].getPiece().isWhite) {
							if (board[x][y].getPiece() instanceof King) {
								g.drawImage(whiteImages[0], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Queen) {
								g.drawImage(whiteImages[1], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Bishop) {
								g.drawImage(whiteImages[2], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Knight) {
								g.drawImage(whiteImages[3], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Rook) {
								g.drawImage(whiteImages[4], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Pawn) {
								g.drawImage(whiteImages[5], y * 100, x * 100, null);
							}
						} else {
							if (board[x][y].getPiece() instanceof King) {
								g.drawImage(blackImages[0], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Queen) {
								g.drawImage(blackImages[1], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Bishop) {
								g.drawImage(blackImages[2], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Knight) {
								g.drawImage(blackImages[3], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Rook) {
								g.drawImage(blackImages[4], y * 100, x * 100, null);
							} else if (board[x][y].getPiece() instanceof Pawn) {
								g.drawImage(blackImages[5], y * 100, x * 100, null);
							}
						}
					}
				}
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
			g.drawString("Best Move: ", 1000, 50);
			g.drawString(Integer.toString(evalScore), 1200, 50);
			g.drawImage(boardImage, 1000, 100, 400, 400, null);
			
			for (int x = 0; x < evalBoard.length; ++x) {
				for (int y = 0; y < evalBoard[0].length; ++y) {
					if (evalBoard[x][y].getPiece() != null) {
						if (evalBoard[x][y].getPiece().isWhite) {
							if (evalBoard[x][y].getPiece() instanceof King) {
								g.drawImage(whiteImages[0], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Queen) {
								g.drawImage(whiteImages[1], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Bishop) {
								g.drawImage(whiteImages[2], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Knight) {
								g.drawImage(whiteImages[3], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Rook) {
								g.drawImage(whiteImages[4], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Pawn) {
								g.drawImage(whiteImages[5], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							}
						} else {
							if (evalBoard[x][y].getPiece() instanceof King) {
								g.drawImage(blackImages[0], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Queen) {
								g.drawImage(blackImages[1], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Bishop) {
								g.drawImage(blackImages[2], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Knight) {
								g.drawImage(blackImages[3], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Rook) {
								g.drawImage(blackImages[4], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							} else if (evalBoard[x][y].getPiece() instanceof Pawn) {
								g.drawImage(blackImages[5], y * 50 + 1000, x * 50 + 100, 50, 50, null);
							}
						}
					}
				}
			}
			
			if (clickedPiece != null) {
				Image temp = null;
				if (clickedPiece.isWhite) {
					if (clickedPiece instanceof King) {
						temp = whiteImages[0];
					}
					if (clickedPiece instanceof Queen) {
						temp = whiteImages[1];
					}
					if (clickedPiece instanceof Bishop) {
						temp = whiteImages[2];
					}
					if (clickedPiece instanceof Knight) {
						temp = whiteImages[3];
					}
					if (clickedPiece instanceof Rook) {
						temp = whiteImages[4];
					}
					if (clickedPiece instanceof Pawn) {
						temp = whiteImages[5];
					}
				} else {
					if (clickedPiece instanceof King) {
						temp = blackImages[0];
					}
					if (clickedPiece instanceof Queen) {
						temp = blackImages[1];
					}
					if (clickedPiece instanceof Bishop) {
						temp = blackImages[2];
					}
					if (clickedPiece instanceof Knight) {
						temp = blackImages[3];
					}
					if (clickedPiece instanceof Rook) {
						temp = blackImages[4];
					}
					if (clickedPiece instanceof Pawn) {
						temp = blackImages[5];
					}
				}
				g.drawImage(temp, currentX - (clickedX%100), currentY - (clickedY%100), null);
			}
			
			g.dispose();
			strategy.show();
			
			try {
				Thread.sleep(17);
			} catch (Exception e) {
			}
		}
	}
	public static void printBoard(Tile [] [] temp) {
		for (int x = 0; x < temp.length; x++) {
			for (int y = 0; y < temp[x].length; y++) {
				if (temp[x][y].getPiece() != null) {
					System.out.print(temp[x][y].getPiece() + " ");
				} else {
					System.out.print("0" + " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
	private void initBoard() {
		board[0][0] = new Tile(new Rook(false, 0, 0, false), 0, 0);
		board[0][1] = new Tile(new Knight(false, 0, 1), 0, 1);
		board[0][2] = new Tile(new Bishop(false, 0, 2), 0, 2);
		board[0][3] = new Tile(new Queen(false, 0, 3), 0, 3);
		board[0][4] = new Tile(new King(false, 0, 4), 0, 4);
		board[0][5] = new Tile(new Bishop(false, 0, 5), 0, 5);
		board[0][6] = new Tile(new Knight(false, 0, 6), 0, 6);
		board[0][7] = new Tile(new Rook(false, 0, 7, false), 0, 7);
		
		for (int x = 0; x < 8; x++) {
			board[1][x] = new Tile(new Pawn(false, 1, x), 1, x);
		}
		for (int y = 2; y < 6; y++) {
			for (int x = 0; x < 8; x++) {
				board[y][x] = new Tile( y, x);
			}
		}
		for (int x = 0; x < 8; x++) {
			board[6][x] = new Tile(new Pawn(true, 6, x), 6, x);
		}
		
		board[7][0] = new Tile(new Rook(true, 7, 0, false), 7, 0);
		board[7][1] = new Tile(new Knight(true, 7, 1), 7, 1);
		board[7][2] = new Tile(new Bishop(true, 7, 2), 7, 2);
		board[7][3] = new Tile(new Queen(true, 7, 3), 7, 3);
		board[7][4] = new Tile(new King(true, 7, 4), 7, 4);
		board[7][5] = new Tile(new Bishop(true, 7, 5), 7, 5);
		board[7][6] = new Tile(new Knight(true, 7, 6), 7, 6);
		board[7][7] = new Tile(new Rook(true, 7, 7, false), 7, 7);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = Game.class.getResource("assets/board.jpg");
		boardImage = tk.getImage(url);
		
		url = Game.class.getResource("assets/wKing.png");
		whiteImages[0] = tk.getImage(url);
		url = Game.class.getResource("assets/wQueen.png");
		whiteImages[1] = tk.getImage(url);
		url = Game.class.getResource("assets/wBishop.png");
		whiteImages[2] = tk.getImage(url);
		url = Game.class.getResource("assets/wKnight.png");
		whiteImages[3] = tk.getImage(url);
		url = Game.class.getResource("assets/wRook.png");
		whiteImages[4] = tk.getImage(url);
		url = Game.class.getResource("assets/wPawn.png");
		whiteImages[5] = tk.getImage(url);
		
		url = Game.class.getResource("assets/bKing.png");
		blackImages[0] = tk.getImage(url);
		url = Game.class.getResource("assets/bQueen.png");
		blackImages[1] = tk.getImage(url);
		url = Game.class.getResource("assets/bBishop.png");
		blackImages[2] = tk.getImage(url);
		url = Game.class.getResource("assets/bKnight.png");
		blackImages[3] = tk.getImage(url);
		url = Game.class.getResource("assets/bRook.png");
		blackImages[4] = tk.getImage(url);
		url = Game.class.getResource("assets/bPawn.png");
		blackImages[5] = tk.getImage(url);


	}
	public static void wRCastle() {
		Piece temp = board[7][4].getPiece();
		board[7][6].setPiece(temp);
		board[7][4].setPiece(null);
		board[7][5].setPiece(new Rook(true, 7, 5, true));
		board[7][7].setPiece(null);
		whiteQCastle = false;
		whiteKCastle = false;
	}
	public static void bRCastle() {
		Piece temp = board[0][4].getPiece();
		board[0][6].setPiece(temp);
		board[0][4].setPiece(null);
		board[0][5].setPiece(new Rook(false, 0, 5, true));
		board[0][7].setPiece(null);
		blackQCastle = false;
		blackKCastle = false;
	}
	
	public static void wQCastle() {
		
	}
	public static void bQCastle() {
		
	}
	
	public static Tile[][] getBoard() {
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
	
	class MouseClick implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			clickedX = e.getX(); 
			clickedY = e.getY();
			if (board[e.getY()/100][e.getX()/100].getPiece().isWhite == isWhiteTurn) {
				clickedPiece = board[e.getY()/100][e.getX()/100].getPiece();	
			} else {
				clickedPiece = null;
			}
			
		}
		
		public void mouseReleased(MouseEvent e) {
			if (clickedPiece != null) {
				int releasedX = e.getX() / 100; 
				int releasedY = e.getY() / 100;
				
				if (clickedPiece.checkMove(releasedY, releasedX)) {
					board[releasedY][releasedX].setPiece(clickedPiece);
					board[clickedY / 100][clickedX / 100].setPiece(null);
					
					isWhiteTurn = !isWhiteTurn;
					evalBoard = evaluation.evaluate(isWhiteTurn);
					evalScore = evaluation.mainEvaluation(evalBoard);
					System.out.println("nibinsin");
					
					
					System.out.println("boards.put(\"" + evaluation.boardToString(board) + "\", );");
				} else {
					System.out.println("CANT MAKE MOVE" + releasedY + " " + releasedX + clickedPiece.x + " " + clickedPiece.y);
				}
				for (int x= 0; x < board.length; x++) {
					for (int y= 0; y < board.length; y++) {
						if (board[x][y].getPiece() != null) {
							System.out.print(board[x][y].getPiece().x + "-" + board[x][y].getPiece().y + " ");
						} else {
							System.out.print("0");
						}
					}
					System.out.println("");
				}
				clickedPiece = null;
				clickedX = -100;
				clickedY = -100;
				
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("NEW");
				printBoard(board);
				evaluation.generateMoves(boardToFEN(board, isWhiteTurn));
			}
			
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
		
	}
	public static String boardToFEN(Tile[][] board, boolean isWhiteTurn) {
		String output = "";
		
		for (int x = 0; x < board.length; x++) {
			int temp = 0;
			for (int y = 0; y < board[0].length; y++) {
				if (board[x][y].getPiece() != null) {
					if (temp != 0) {
						output += temp;
					}
					temp = 0;
					output += board[x][y].getPiece().toString();
				} else {
					temp++;
				}
			}
			if (temp != 0) {
				output += temp;
			}
			if (x != board.length - 1) {
				output += "/";
			}
		}
		
		if (isWhiteTurn) {
			fullMoveCount++;
			output += " w ";
		} else {
			output += " b ";
		}
		
		if (whiteKCastle || whiteQCastle || blackKCastle || blackQCastle) {
			if (whiteKCastle) {
				output += "K";
			}
			if (whiteQCastle) {
				output += "Q";
			}
			if (blackKCastle) {
				output += "k";
			}
			if (blackQCastle) {
				output += "q";
			}
			output += " ";
		} else {
			output += "- ";
		}
		output += "-";
		output += " 0 " + fullMoveCount;
		return output;
	}

	class MouseMove implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
			currentX = e.getX();
			currentY = e.getY();
		}

		public void mouseMoved(MouseEvent e) {
			currentX = e.getX();
			currentY = e.getY();
		}
		
	}
}
