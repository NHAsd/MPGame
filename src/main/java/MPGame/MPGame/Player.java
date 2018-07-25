package MPGame.MPGame;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {

	private Rectangle shape = new Rectangle(50,50);
	private String name;
    private int speed =0;
    private final int VMAX=30;
    private final int VMAXBACK =-10;
    private final int BREACK =2;
    private final int ACCELERATE =3;
	
	public Player(Position position, Color color) {
		name = position.getName();
		shape.setTranslateX(position.getX());
		shape.setTranslateY(position.getY());
		shape.setFill(color);
		speed =5;

	}
	
	public Player(String name, double positionX, double positionY, Color color) {
		this.name = name;
		shape.setTranslateX(positionX);
		shape.setTranslateY(positionY);
		shape.setFill(color);
		speed =5;

	}
	

	public void setPosition(Position position) {
		shape.setTranslateX(position.getX());
		shape.setTranslateY(position.getY());
		shape.setRotate(position.getDirection());
	}
	
	public Position getPosition() {
		return new Position((int)shape.getTranslateX(), (int) shape.getTranslateY(),shape.getRotate(), name);
	}
	
	public void moveLeft() {

		shape.setRotate(shape.getRotate() - speed*0.25);
	}
	
	public void moveRight() {

		shape.setRotate(shape.getRotate() + speed*0.25);
	}
	
	public void moveUp() {

    	if(speed<=VMAX) {
    		speed=speed+ACCELERATE;
      
    	}
	}

	public void moveDown() {

    	if(speed>=VMAXBACK) {
    		speed=speed-BREACK;
    	}
	}

	public Rectangle getShape() {
		return shape;
	}
	public String getName() {
		return name;
	}
	public void updatePosition(double x, double y) {
		shape.setTranslateX(x);
		shape.setTranslateY(y);
		
	}
	
	public double getPositionX() {
		return shape.getTranslateX();
	}
	public double getPositionY() {
		return shape.getTranslateY();
	}

	public void update() {
    	shape.setTranslateX(shape.getTranslateX() + 0.1*speed*Math.cos(Math.toRadians(shape.getRotate())));
        shape.setTranslateY(shape.getTranslateY() + 0.1*speed*Math.sin(Math.toRadians(shape.getRotate())));
		
	}
	

}
