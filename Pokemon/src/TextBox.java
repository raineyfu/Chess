import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TextBox {
	private Game game;
	private String drawnMessage = "";
	private String drawnMessage2 = "";
	private int letterCounter;
	private int arrayCounter;
	private int timeForLetter = 115;
	private int firstLineY = 850;
	private int secondLineY = 900;
	private int selectedItem = 1;
	private boolean drawSecondLine = false;
	private long drawTimer;
	long timer;
	Image textBoxBackground;
	Image signBackground;
	Sprite nonSelected;
	Sprite selected;
	Image shopMenu;
	List<String> messagesToShow = new ArrayList<String>();

	public TextBox(Game game) {

		this.game = game;

		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = TextBox.class.getResource("assets/textBox1.png");
		textBoxBackground = tk.getImage(url);
		url = TextBox.class.getResource("assets/sign1.png");
		signBackground = tk.getImage(url);
		url = TextBox.class.getResource("assets/potion.png");
		nonSelected = (SpriteStore.get()).getSprite("assets/BattleItems/menu.png");
		url = TextBox.class.getResource("assets/selected.png");
		selected = (SpriteStore.get()).getSprite("assets/BattleItems/selected.png");
		url = TextBox.class.getResource("assets/shopMenu.png");
		shopMenu = tk.getImage(url);
		timer = System.currentTimeMillis();

	}

	public void drawText(Graphics2D g) {
		g.setFont(game.getFont());
		g.drawImage(textBoxBackground, 582, 800, null);
		g.setFont(game.getFont());
		g.setColor(Color.black);
		g.drawString(drawnMessage, 650, firstLineY);
		if (drawSecondLine) {
			g.drawString(drawnMessage2, 650, secondLineY);
		}
		if (arrayCounter != 2) {
			if (letterCounter < messagesToShow.get(arrayCounter).length()) {
				if (System.currentTimeMillis() - timeForLetter >= drawTimer) {
					if (arrayCounter == 0) {
						drawnMessage += messagesToShow.get(arrayCounter).charAt(letterCounter);
					} else {
						drawnMessage2 += messagesToShow.get(arrayCounter).charAt(letterCounter);
					}
					letterCounter++;

					if (letterCounter == messagesToShow.get(arrayCounter).length()) {
						letterCounter = 0;
						arrayCounter++;
						drawSecondLine = true;
					}
					drawTimer = System.currentTimeMillis();
				}
			}
		}
	}

	public void reset() {
		arrayCounter = 0;
		letterCounter = 0;
		drawSecondLine = false;
		drawTimer = System.currentTimeMillis();
		drawnMessage = "";
		drawnMessage2 = "";
	}

	public void initiateText(String text) {
		String[] message = text.split("\n", 2);
		messagesToShow.add(message[0]);
		messagesToShow.add(message[1]);

		game.setDrawTextBox(true);
		game.setWaitingForSpace(true);
		drawTimer = System.currentTimeMillis();
		letterCounter = 0;
	}

	public void drawSign(String message, Graphics2D g) {
		String[] fullMessage = message.split("\n");
		g.setFont(game.getFont());
		g.setColor(Color.white);
		g.drawImage(signBackground, 582, 800, null);
		g.drawString(fullMessage[0], 650, 850);
		g.drawString(fullMessage[1], 650, 900);

	}

	public void drawShop(Graphics2D g) {
		g.drawImage(shopMenu, 576, 348, null);
		int yLocation = 400;
		int xLocation = 720;
		for (int x = 1; x <= 8; x++) {
			nonSelected.draw(g, xLocation, yLocation);

			if (x == selectedItem) {
				selected.draw(g, xLocation, yLocation);
			}
			xLocation += 280;
			if (x % 2 == 0) {
				yLocation += 100;
				xLocation = 720;
			}
		}
	}

	public void changeSelection(int change) {
		if (!(selectedItem + change > 8) && !(selectedItem + change < 1)) {
			this.selectedItem += change;
		}
		System.out.println("selected" + selectedItem);
	}

	public void addMessageText(String text) {
		String[] message = text.split("\n", 2);
		messagesToShow.add(message[0]);
		messagesToShow.add(message[1]);
	}

	public void removeMessage(int index) {
		messagesToShow.remove(index);
	}

	public int checkSize() {
		if (messagesToShow != null) {
			return messagesToShow.size();
		} else {
			return 0;
		}
	}

}
