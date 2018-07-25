package MPGame.MPGame;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet {
	private Circle bullet = new Circle(5, Color.BLACK);

    private final double direction;  // capslock?
    
    public Bullet(double direction, double x, double y) {		
    	this.direction = direction;
    	bullet.setTranslateX(x);
    	bullet.setTranslateY(y);
    }
    
    public Bullet(double direction, Position position) {		
    	this.direction = direction;
    	bullet.setTranslateX(position.getX());
    	bullet.setTranslateY(position.getY());
    }
    public Bullet(Position position) {		
    	this.direction = position.getDirection();
    	bullet.setTranslateX(position.getX());
    	bullet.setTranslateY(position.getY());
    }
        
    public Circle getShape() {
    	return bullet;
    }
    public boolean isColliding(Player player) {
        return getShape().getBoundsInParent().intersects(player.getShape().getBoundsInParent());
    }
    
    
	public void move() {
//    	if(direction == 0) {
//        	bullet.setTranslateY(bullet.getTranslateY() - 5);
//    	}else if(direction == 180) {
//        	bullet.setTranslateY(bullet.getTranslateY() + 5);
//    	}else if(direction == 90) {
//        	bullet.setTranslateX(bullet.getTranslateX() + 5);
//    	} else if(direction == 270) {
//        	bullet.setTranslateX(bullet.getTranslateX() - 5);
//    	}
    	bullet.setTranslateY(bullet.getTranslateY() - 5);
		
	}

}
