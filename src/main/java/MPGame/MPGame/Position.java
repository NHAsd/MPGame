package MPGame.MPGame;

import java.io.Serializable;

public class Position implements Serializable {
	private double direction;
	private int x;
	private int y;
	private String name;

	
	public Position() {}
	
	public Position(String name) {
		this.name = name;
	}
	
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;

	}
	
	public Position(int x, int y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public Position(int x, int y, double direction, String name) {
		super();
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.name = name;

	}
	
	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
