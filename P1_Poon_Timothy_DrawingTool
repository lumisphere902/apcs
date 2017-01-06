//Timothy Poon P1 11/11/2016

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import gpdraw.DrawingTool;
import gpdraw.SketchPad;
import gpdraw.SketchPadPanel;

public class P1_Poon_Timothy_DrawingTool extends DrawingTool implements MouseListener{
	private boolean XTurn = false;
	private boolean gameStarted = false;
	private int[][] boardState = new int[3][3];
	private int boxSize = 0;
	private int turns = 0;
	private boolean gameEnded = false;
	public P1_Poon_Timothy_DrawingTool(SketchPad pad) {
		super(pad);
		getPadPanel().addMouseListener(this);
		boxSize = (int) (getPadPanel().getHeight()/3.4);
		drawString("LEFT CLICK TO START GAME");
	}
	public P1_Poon_Timothy_DrawingTool(SketchPad pad, int boxSize) {
		super(pad);
		getPadPanel().addMouseListener(this);
		this.boxSize = boxSize;
		drawString("LEFT CLICK TO START GAME");
	}
	public void drawGrid() {
		initialize(getPadPanel());
		setColor(Color.WHITE);
		fillRect(getPadPanel().getWidth(), getPadPanel().getHeight());
		setColor(Color.BLACK);
		drawRect(boxSize*3, boxSize);
		drawRect(boxSize, boxSize*3);
		drawRect(boxSize*3, boxSize*3);
		move(-(getPadPanel().getWidth()/2)+10, getPadPanel().getHeight()/2-15);
		if (!gameStarted) {
			drawString("Left click to start game. Right click to play.");
		}
		else {
			drawString("Right click to play.");
		}
	}
	private void playTurn(Point pos) {
		Point near = nearestSquare(pos);
		int[] position = toGameState(near);
		if (boardState[position[0]][position[1]] == 0) { 
			move(near.getX(),near.getY());
			if (XTurn) {
				boardState[position[0]][position[1]] = 1;
				drawX();
			}
			else {
				boardState[position[0]][position[1]] = 2;
				drawO();
			}
			turns ++;
			XTurn = !XTurn;
			if (!checkVictory()) {
				System.out.printf("It is now %s's turn%n", XTurn ? "X" : "O");
			}
		}
	}
	private boolean checkVictory() {
		for (int i = 0; i < 3; i++) {
			if (boardState[i][0] == boardState[i][1] && boardState[i][1] == boardState[i][2]) {
				if (boardState[i][0] != 0) {
					victory(boardState[i][0]);
					return true;
				}
			}
			else if (boardState[0][i] == boardState[1][i] && boardState[1][i] == boardState[2][i]) {
				if (boardState[0][i] != 0) {
					victory(boardState[0][i]);
					return true;
				}
			}
		}
		if (boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2] ||
				boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]) {
			if (boardState[1][1] != 0) {
				victory(boardState[1][1]);
				return true;
			}
		}
		if (turns == 9) {
			initialize(getPadPanel());
			drawString("IT IS A TIE");
			gameEnded = true;
			new Timer().schedule(new TimerTask() {
				  @Override
				  public void run() {
					  System.exit(0);
				  }
				}, 1500);
		}
		return false;
	}
	private void victory(int i) {
		gameStarted = false;
		initialize(getPadPanel());
		String msg = "THE WINNER IS " + (i == 1 ? "X" : "O");
		drawString(msg);
		System.out.println(msg);
		gameEnded  = true;
		new Timer().schedule(new TimerTask() {
			  @Override
			  public void run() {
				  System.exit(0);
			  }
			}, 1500);
	}
	private void drawO() {
		drawOval(boxSize*.6, boxSize*.75);
	}
	private void drawX() {
		setDirection(45);
		forward(boxSize/1.5);
		turn(180);
		forward(boxSize/.75);
		turn(180);
		forward(boxSize/1.5);
		setDirection(-45);
		forward(boxSize/1.5);
		turn(180);
		forward(boxSize/.75);
		turn(180);
		forward(boxSize/1.5);
	}
	private int[] toGameState(Point pos) {
		int[] ans = new int[2];
		ans[0] = ((int)pos.getX()+boxSize)/boxSize;
		ans[1] = -((int)pos.getY()-boxSize)/boxSize;
		return ans;
	}
	private Point nearestSquare(Point pos) {
		Point best = null;
		for (double i = -boxSize; i <= boxSize;  i += boxSize) {
			for (double j = -boxSize; j <= boxSize;  j += boxSize) {
				Point temp = new Point((int) i,(int) j);
				if (best == null || temp.distance(pos) < best.distance(pos)) {
					best = temp;
				}
			}
		}
		return best;
	}
	private Point convert(Point pos) {
		int y = (int) (getPadPanel().getHeight()/2 - pos.getY());
		int x = (int) -(getPadPanel().getWidth()/2 - pos.getX());
		return new Point(x,y);
	}
	public int getBoxSize() {
		return boxSize;
	}
	public void setBoxSize(int boxSize) {
		this.boxSize = boxSize;
		gameStarted = false;
		drawGrid();
	}
	@Override
	protected void initialize (SketchPadPanel spadPanel) {
		super.initialize(spadPanel);
		setColor(Color.WHITE);
		fillRect(getPadPanel().getWidth(),getPadPanel().getHeight());
		setColor(Color.BLACK);
	}
	@Override
	public void move(double x, double y) {
		up();
		super.move(x, y);
		down();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!gameStarted && !gameEnded) {
				gameStarted = true;
				drawGrid();
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			if (gameStarted) {
				playTurn(convert(e.getPoint()));
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON2) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(boardState[j][i] + " ");
				}
				System.out.println();
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
