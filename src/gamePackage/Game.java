package gamePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game {

	public static final int GAME_LOGIC_DELAY = 750; // 1000 = 1 sec
	public static final int DRAW_DELAY = 1000 / 60;

	private Grid gameGrid;
	public Block blok; // zou eigenlijk private moeten zijn maar voor het testen is het public

	private JFrame gameFrame;
	private GameCanvas gameCanvas;

	private int score;

	private Timer gameTimer;
	private Timer drawTimer;

	public static void main(String[] args) {
		Game game = new Game();
		game.initialize();
		game.initializeScreen();

		game.run();
	}

	private void initialize() {
		score = 0;

		gameGrid = new Grid();
		blok = new Block(gameGrid);
	}

	private void initializeScreen() {
		gameCanvas = new GameCanvas(this);

		gameFrame = new JFrame("Tetris");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().add(gameCanvas);
		gameFrame.setResizable(false);
		gameFrame.pack();
		gameFrame.setVisible(true);

		gameFrame.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					blok.moveRight();
				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					blok.moveLeft();
				}

				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					blok.rotate();
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					blok.update();
				}

				if (e.getKeyCode() == KeyEvent.VK_Q) {
					initialize();
					gameTimer.start();
				}
			}
		});
	}

	private void run() {
		gameTimer = new Timer(GAME_LOGIC_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				update();
				score += gameGrid.isLineCompleted();
			}
		});

		drawTimer = new Timer(DRAW_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				gameFrame.repaint();
			}
		});

		drawTimer.setRepeats(true);
		drawTimer.start();

		gameTimer.setRepeats(true);
		gameTimer.start();
	}

	private void update() {
		if (gameGrid.isTopRowEmpty()) {
			if (blok.isSolid()) {
				blok = new Block(gameGrid);
			} else {
				blok.update();
			}
		} else {
			System.err.println("Game Over");
			gameTimer.stop();
		}
	}

	public Grid getGrid() {
		return gameGrid;
	}

	public int getScore() {
		return score;
	}

	public Block getCurrentBlock() {
		return blok;
	}

}
