package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Tile {

	private boolean filled;
	private boolean solid;

	private int x, y;

	final static int WIDTH = 25;
	final static int HEIGHT = 25;
	final static int SCREENBORDER_LEFT = 100;
	final static int SCREENBORDER_TOP = 20;

	final static boolean debug = false;

	private static Font font = new Font("Arial", Font.PLAIN, 10);

	public Tile(int newY, int newX) {
		setFilled(false);
		setX(newX);
		setY(newY);
	}

	public void draw(Graphics g) {
		if (isSolid()) {
			g.setColor(Color.BLUE);
		} else {
			if (isFilled()) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.GRAY);
			}
		}

		g.fillRect(x * WIDTH + SCREENBORDER_LEFT, y * HEIGHT + SCREENBORDER_TOP, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(x * WIDTH + SCREENBORDER_LEFT, y * HEIGHT + SCREENBORDER_TOP, WIDTH, HEIGHT);

		if (debug) {
			g.setColor(Color.BLACK);
			g.setFont(font);

			g.drawString(x + "-" + y, x * WIDTH + 2 + SCREENBORDER_LEFT, y * HEIGHT + 10 + SCREENBORDER_TOP);
		}
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void goDown() {
		y -= HEIGHT;
	}

	public boolean isSolid() {
		return solid;
	}

	public void solidify() {
		this.solid = true;
	}

	@Override
	public String toString() {
		if (isFilled()) {
			return "X";
		}
		if (isSolid()) {
			return "O";
		}
		return "_";
	}

	public void empty() {
		setFilled(false);
		solid = false;
	}

}
