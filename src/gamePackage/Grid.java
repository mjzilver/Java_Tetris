package gamePackage;

import java.awt.Graphics;

public class Grid {

	public static final int GRID_HEIGHT = 22;
	public static final int GRID_WIDTH = 10;

	private Tile[][] tiles = new Tile[GRID_HEIGHT][GRID_WIDTH];

	public Grid() {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				tiles[y][x] = new Tile(y, x);
			}
		}
	}

	public void draw(Graphics g) {
		for (Tile[] tiles : tiles) {
			for (Tile tile : tiles) {
				tile.draw(g);
			}
		}
	}

	public Tile[][] getSpawn() {
		Tile[][] spawn = new Tile[3][3];

		spawn[0][0] = tiles[0][4];
		spawn[0][1] = tiles[0][5];
		spawn[0][2] = tiles[0][6];

		spawn[1][0] = tiles[1][4];
		spawn[1][1] = tiles[1][5];
		spawn[1][2] = tiles[1][6];

		spawn[2][0] = tiles[2][4];
		spawn[2][1] = tiles[2][5];
		spawn[2][2] = tiles[2][6];

		return spawn;
	}

	public Tile getTile(int y, int x) {
		try {
			if (tiles[y][x].isSolid()) {
				return null;
			}
			return tiles[y][x];
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isTopRowEmpty() {
		// als er één blokje in de eerste rij gevuld is is het false
		for (int x = 0; x < GRID_WIDTH; x++) {
			if (tiles[0][x].isSolid()) {
				return false;
			}
		}

		return true;
	}

	public int isLineCompleted() {
		for (int y = 0; y < tiles.length; y++) {
			if (tiles[y][0].isSolid() && tiles[y][1].isSolid() && tiles[y][2].isSolid() && tiles[y][3].isSolid()
					&& tiles[y][4].isSolid() && tiles[y][5].isSolid() && tiles[y][6].isSolid() && tiles[y][7].isSolid()
					&& tiles[y][8].isSolid() && tiles[y][9].isSolid()) {
				emptyRow(y);
				return 10;
			}
		}

		return 0;
	}

	private void emptyRow(int rowNumber) {
		for (int x = 0; x < GRID_WIDTH; x++) {
			tiles[rowNumber][x].empty();
		}

		for (int y = rowNumber; y > 0; y--) {
			for (int x = tiles[y].length - 1; x >= 0; x--) {
				if (tiles[y - 1][x].isSolid() && !tiles[y][x].isSolid()) {
					tiles[y][x].solidify();
					tiles[y - 1][x].empty();
				}
			}
		}
	}

}
