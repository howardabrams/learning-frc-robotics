
public class MyRobot {
	double x;
	double y;
	int direction;

	void move(int amount) {
		x = x + Math.cos(direction) * amount;
		y = y + Math.sin(direction) * amount;
	}

	void turn(int amount) {
		direction += amount;
	}
	
	void step() {
		move(3);
		turn(3);
	}
}
