package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Block {
	private Tile[][] shape = new Tile[3][3];

	private Grid gameGrid;

	public static final int STANDARD_YDIR = 1;
	public static final int STANDARD_XDIR = 0;

	private boolean solid;

	private Random rand = new Random();

	private static Font font = new Font("Arial", Font.PLAIN, 10);

	private int seed = rand.nextInt(7) + 1;
	private int orientation = rand.nextInt(4) + 1;

	public Block(Grid gameGrid) {
		this.gameGrid = gameGrid;
		shape = gameGrid.getSpawn();

		solid = false;

		fillShape();
	}

	private void fillO() {
		shape[0][0].setFilled(true);
		shape[0][1].setFilled(true);
		shape[1][0].setFilled(true);
		shape[1][1].setFilled(true);
	}

	private void fillI() {
		if (orientation == 1 || orientation == 3) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
		} else {
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
	}

	private void fillL() {
		if (orientation == 1) {
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
			shape[0][2].setFilled(true);
		}
		if (orientation == 2) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
			shape[2][2].setFilled(true);
		}
		if (orientation == 3) {
			shape[2][0].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
		if (orientation == 4) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
			shape[0][0].setFilled(true);
		}
	}

	private void fillJ() {
		if (orientation == 1) {
			shape[0][0].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
		if (orientation == 2) {
			shape[0][2].setFilled(true);
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
		}
		if (orientation == 3) {
			shape[2][2].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
		if (orientation == 4) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
			shape[2][0].setFilled(true);
		}
	}

	private void fillZ() {
		if (orientation == 1) {
			shape[0][0].setFilled(true);
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
		if (orientation == 2) {
			shape[0][2].setFilled(true);
			shape[1][2].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
		}
		if (orientation == 3) {
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
			shape[2][2].setFilled(true);
		}
		if (orientation == 4) {
			shape[0][1].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][0].setFilled(true);
		}
	}

	private void fillS() {
		if (orientation == 1) {
			shape[0][1].setFilled(true);
			shape[0][2].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][0].setFilled(true);
		}
		if (orientation == 2) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
			shape[2][2].setFilled(true);
		}
		if (orientation == 3) {
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
			shape[2][0].setFilled(true);
			shape[2][1].setFilled(true);
		}
		if (orientation == 4) {
			shape[0][0].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
		}
	}

	private void fillT() {
		if (orientation == 1) {
			shape[0][1].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
		if (orientation == 2) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
			shape[1][0].setFilled(true);
		}
		if (orientation == 3) {
			shape[2][1].setFilled(true);
			shape[1][0].setFilled(true);
			shape[1][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
		if (orientation == 4) {
			shape[0][1].setFilled(true);
			shape[1][1].setFilled(true);
			shape[2][1].setFilled(true);
			shape[1][2].setFilled(true);
		}
	}

	public void update() {
		update(STANDARD_XDIR, STANDARD_YDIR);
	}

	public void update(int xDir, int yDir) {
		if (canMove(xDir, yDir)) {
			for (int y = shape.length - 1; y >= 0; y--) {
				for (int x = shape.length - 1; x >= 0; x--) {
					if (!solid) {
						Tile nextTile = gameGrid.getTile(shape[y][x].getY() + yDir, shape[y][x].getX() + xDir);

						if (nextTile != null)
						{
							shape[y][x].setFilled(false);
							shape[y][x] = nextTile;
							fillShape();
						}
					}
				}
			}
		} else {
			// check of het de muur of de bodem is of een ander blokje
			if (xDir != 0) {
				update();
			} else {
				solidify();
			}
		}
	}

	private boolean canMove(int xDir, int yDir) {
		for (int y = shape.length - 1; y >= 0; y--) {
			for (int x = shape.length - 1; x >= 0; x--) {
				if (shape[y][x].isFilled() && !solid) {
					Tile nextTile = gameGrid.getTile(shape[y][x].getY() + yDir, shape[y][x].getX() + xDir);

					if (nextTile == null || nextTile.isSolid()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private void solidify() {
		solid = true;

		// maat alle tiles solide
		for (Tile[] tiles : shape) {
			for (Tile tile : tiles) {
				if (tile.isFilled()) {
					if (!tile.isSolid()) {
						tile.solidify();
						tile.setFilled(false);
					}
				}
			}
		}
	}

	public void fillShape() {
		switch (seed) {
		case 1:
			fillT();
			break;
			
		case 2:
			fillS();
			break;
			
		case 3:
			fillZ();
			break;
			
		case 4:
			fillJ();
			break;
			
		case 5:
			fillL();
			break;
			
		case 6:
			fillI();
			break;
			
		case 7:
			fillO();
			break;
		}
	}

	public boolean isSolid() {
		return solid;
	}

	public void moveRight() {
		update(1, 0);
	}

	public void moveLeft() {
		update(-1, 0);
	}

	public void rotate() {
		if (canRotate()) {
			shape[0][0] = gameGrid.getTile(shape[1][1].getY() - 1, shape[1][1].getX() - 1);
			shape[0][1] = gameGrid.getTile(shape[1][1].getY() - 1, shape[1][1].getX());
			shape[0][2] = gameGrid.getTile(shape[1][1].getY() - 1, shape[1][1].getX() + 1);

			shape[1][0] = gameGrid.getTile(shape[1][1].getY(), shape[1][1].getX() - 1);
			shape[1][1] = gameGrid.getTile(shape[1][1].getY(), shape[1][1].getX());
			shape[1][2] = gameGrid.getTile(shape[1][1].getY(), shape[1][1].getX() + 1);

			shape[2][0] = gameGrid.getTile(shape[1][1].getY() + 1, shape[1][1].getX() - 1);
			shape[2][1] = gameGrid.getTile(shape[1][1].getY() + 1, shape[1][1].getX());
			shape[2][2] = gameGrid.getTile(shape[1][1].getY() + 1, shape[1][1].getX() + 1);

			if (orientation < 4) {
				orientation++;
			} else {
				orientation = 1;
			}
		}
	}

	private boolean canRotate() {
		if (gameGrid.getTile(shape[1][1].getY() - 1, shape[1][1].getX() - 1) == null
				|| gameGrid.getTile(shape[1][1].getY() - 1, shape[1][1].getX()) == null
				|| gameGrid.getTile(shape[1][1].getY() - 1, shape[1][1].getX() + 1) == null

				|| gameGrid.getTile(shape[1][1].getY(), shape[1][1].getX() - 1) == null
				|| gameGrid.getTile(shape[1][1].getY(), shape[1][1].getX()) == null
				|| gameGrid.getTile(shape[1][1].getY(), shape[1][1].getX() + 1) == null

				|| gameGrid.getTile(shape[1][1].getY() + 1, shape[1][1].getX() - 1) == null
				|| gameGrid.getTile(shape[1][1].getY() + 1, shape[1][1].getX()) == null
				|| gameGrid.getTile(shape[1][1].getY() + 1, shape[1][1].getX() + 1) == null) {
			return false;
		}
		return true;
	}

	public void draw(Graphics g) {
		for (int y = shape.length - 1; y >= 0; y--) {
			for (int x = shape.length - 1; x >= 0; x--) {
				Tile tile = shape[y][x];

				// kiest een kleur voor het blokje
				if (tile.isSolid()) {
					g.setColor(Color.BLUE);
				} else {
					if (tile.isFilled()) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.GRAY);
					}
				}

				// Het blokje zelf
				g.fillRect(tile.getX() * Tile.WIDTH + Tile.SCREENBORDER_LEFT + 250,
						tile.getY() * Tile.HEIGHT + Tile.SCREENBORDER_TOP, Tile.WIDTH, Tile.HEIGHT);
				
				// De randjes 
				g.setColor(Color.BLACK);
				g.drawRect(tile.getX() * Tile.WIDTH + Tile.SCREENBORDER_LEFT + 250,
						tile.getY() * Tile.HEIGHT + Tile.SCREENBORDER_TOP, Tile.WIDTH, Tile.HEIGHT);

				// Cijfertjes voor het debug blok
				g.setFont(font);
				g.drawString(tile.getX() + "-" + tile.getY(),
						tile.getX() * Tile.WIDTH + 2 + Tile.SCREENBORDER_LEFT + 250,
						tile.getY() * Tile.HEIGHT + 10 + Tile.SCREENBORDER_TOP);
			}
		}
	}
}
