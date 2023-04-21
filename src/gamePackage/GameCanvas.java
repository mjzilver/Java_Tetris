package gamePackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GameCanvas extends JComponent {

	private static final long serialVersionUID = 9L;
	private Game gameModel;
	private Grid gameGrid;

	private static Font font = new Font("Arial", Font.BOLD, 18);

	public GameCanvas(Game newGameModel) {
		super();
		gameModel = newGameModel;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gameModel != null) {
			gameGrid = gameModel.getGrid();
			gameModel.blok.draw(g);
			gameGrid.draw(g);
		}

		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString("Score: " + gameModel.getScore(), 10, 50);
	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}
