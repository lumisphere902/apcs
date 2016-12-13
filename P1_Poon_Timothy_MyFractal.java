//Timothy Poon 10/1/2016
//Heighway Dragon

import java.awt.Color;
import gpdraw.*;

public class P1_Poon_Timothy_MyFractal {
	public static void main(String[] args) {
		P1_Poon_Timothy_Dragon dragon = new P1_Poon_Timothy_Dragon(400, 16);
		dragon.drawDragon();
	}
}
class P1_Poon_Timothy_Dragon {
	private SketchPad pad = new SketchPad(1000, 1000);
	private DrawingTool pen = new DrawingTool(pad);
	private double length;
	private int level;
	public P1_Poon_Timothy_Dragon(double length, int level) {
		this.length = length;
		this.level = level;
		pen.up();
		pen.move(-length*Math.sqrt(2)/2,0);
		pen.down();
	}
	public void drawDragon() {
		pen.setColor(Color.LIGHT_GRAY);
		pen.setDirection(45);
		draw(length, level);
		pen.up();
		pen.move(length*Math.sqrt(2)/2,0);
		pen.setDirection(135);
		pen.setColor(Color.BLACK);
		pen.down();
		draw(length, level);
	}
	public void draw(double length, int level) {
		if (level == 0) {
			pen.forward(length);
			pen.turnLeft(135);
			pen.forward(length/Math.sqrt(2));
			pen.turnLeft(90);
			pen.forward(length/Math.sqrt(2));
		}
		else {
			double tempDir = pen.getDirection();
			pen.turnLeft(45);
			double tempX = pen.getXPos();
			double tempY = pen.getYPos();
			draw(length/Math.sqrt(2), level - 1);
			pen.up();
			pen.move(tempX, tempY);
			pen.setDirection(tempDir);
			pen.move(length);
			pen.turnLeft(135);
			pen.down();
			draw(length/Math.sqrt(2), level - 1);
		}
	}
}
