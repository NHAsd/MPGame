package MPGame.MPGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
	private Rectangle shape = new Rectangle(50,50,Color.BLUE);
	private String name;
	private int speed;

	
	public Player(String name, double positionX, double positionY) {
		this.name = name;
		shape.setTranslateX(positionX);
		shape.setTranslateY(positionY);
		speed =5;
		
	}
	
	public void moveLeft() {
		shape.setTranslateX(shape.getTranslateX()-speed);
	}
	
	public void moveRight() {
		shape.setTranslateX(shape.getTranslateX()+speed);
	}
	
	public void moveUp() {
		shape.setTranslateY(shape.getTranslateY()-speed);
	}

	public void moveDown() {
		shape.setTranslateY(shape.getTranslateY()+speed);
	}

	public Rectangle getShape() {
		return shape;
	}
	
	
	

}
