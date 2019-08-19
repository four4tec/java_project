package final_project;

import javax.swing.JLabel;
import java.util.Random;

public class Slime {
	public int hp;
	public int[] position;
	public int[] direction;
	public int size;
	public JLabel pic;
	public int now;
	public int max;
	public int speed;

	public Slime(int[] position, int speed) {
		hp = 3;
		this.position = position;
		size = 3;
		this.direction = new int[] { 0, 0 };
		this.now = 0;
		this.max = 100;
		this.speed = speed;

		this.change_direction();
	}

	public void forward() {
		this.position[1] = this.position[1] - 1;
	}

	public void backword() {
		this.position[1] = this.position[1] + 1;
	}

	public void right() {
		this.position[0] = this.position[0] + 1;
	}

	public void left() {
		this.position[0] = this.position[0] - 1;
	}

	public int loss_hp(int loss) {
		if (this.hp - loss <= 0) {
			hp = 0;
			return -1;
		} else {
			this.hp -= loss;
			return 0;
		}
	}

	public void set_direction(int[] direction) {
		this.direction = direction;
	}

	public void set_position(int[] position) {
		this.position = position;
	}

	public int[] get_position() {
		return this.position;
	}

	public int[] get_direction() {
		return this.direction;
	}

	public void move() {
		this.position[0] += this.direction[0] * speed;
		this.position[1] += this.direction[1] * speed;
	}

	public void change_direction() {
		Random ran = new Random();
		int ran_direction = ran.nextInt(4);
		switch (ran_direction) {
		case 0:// forward
			this.direction[0] = 1;
			this.direction[1] = -1;
			break;
		case 1:// backward
			this.direction[0] = 1;
			this.direction[1] = 1;
			break;
		case 2:// right
			this.direction[0] = 0;
			this.direction[1] = 1;
			break;
		case 3:// left
			this.direction[0] = 0;
			this.direction[1] = -1;
			break;
		}
	}
}
