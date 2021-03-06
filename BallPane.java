import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.*;
import java.util.Random;

public class BallPane extends Pane {
	// random radius
	Random random = new Random();
	public final double radius = random.nextInt(75)+1;
	private double dx = 1, dy = 1; 
	private double x = radius, y = radius;
	
	private Circle circle = new Circle(x, y, radius);
	private Timeline animation;

	private double trackX = 0.0;
	private double trackY = 0.0;

	public BallPane() {
		// random color
		circle.setFill(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256))); // Set ball color
		getChildren().add(circle); // Place a ball into this pane

		// Create an animation for moving the ball
		animation = new Timeline(
		new KeyFrame(Duration.millis(50), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
	}

	/* new a circle to record the track */
	public void newBall(){
		
		Circle ballObj = new Circle(x, y, radius);
		ballObj.setFill(Color.web("rgba(255,255,255,0)"));	// alpha = 0
		ballObj.setStroke(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256))); // Set ball color
		getChildren().add(ballObj); // Place a ball into this pane	
		
		if(trackX!=0.0 && trackY!=0.0){
			Line line = new Line(trackX,trackY,x,y);
			getChildren().add(line); // Place a track line into this pane	
		}
		trackX = x;
		trackY = y;
	}
	public void play() {
		animation.play();
	}

	public void pause() {
		animation.pause();
	}

	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}

	public void decreaseSpeed() {
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}

	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}
  
	protected void moveBall() {
		// Check boundaries
		if (x < radius || x > getWidth() - radius) {
		dx *= -1; // Change ball move direction
		}
		if (y < radius || y > getHeight() - radius) {
		dy *= -1; // Change ball move direction
		}

		// Adjust ball position
		x += dx;
		y += dy;
		circle.setCenterX(x);
		circle.setCenterY(y);
	}
}